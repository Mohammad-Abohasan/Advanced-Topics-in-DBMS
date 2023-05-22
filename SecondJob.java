import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SecondJob {

  public static class UserRatingMapper
    extends Mapper<LongWritable, Text, Text, Text> {

    private Text movieID = new Text(), rating = new Text();

    public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
      String v = value.toString();
      String[] tokens = v.split(", ");
      movieID.set(tokens[0].substring(2).trim());
      rating.set("Rating, " + tokens[1]);
      context.write(movieID, rating);
    }
  }

  public static class MovieMapper
    extends Mapper<LongWritable, Text, Text, Text> {

    private Text movieID = new Text(), result = new Text();

    public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
      String v = value.toString();
      String[] tokens = v.split(", ");
      if (tokens[2].equals("Comedy") || tokens[2].equals("Children")) {
        movieID.set(tokens[0]);
        result.set("Movie, " + tokens[1]);
        context.write(movieID, result);
      }
    }
  }

  public static class MovieRatingReducer
    extends Reducer<Text, Text, Text, Text> {

    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
      boolean genresEqualCC = false;
      double avg = 0;
      int count = 0;
      String title = "";
      for (Text val : values) {
        String v = val.toString();
        String[] tokens = v.split(", ");
        if (v.charAt(0) == 'M') {
          genresEqualCC = true;
          title = tokens[1];
        } else {
          count++;
          avg += Double.parseDouble(tokens[1]);
        }
      }
      if (genresEqualCC) {
        if (count != 0) {
          avg /= count;
        }
        result.set(title + ", " + avg);
        context.write(key, result);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "second job");
    job.setJarByClass(SecondJob.class);
    //job.setNumReduceTasks(0);
    //job.setCombinerClass(MovieRatingReducer.class);
    job.setReducerClass(MovieRatingReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    MultipleInputs.addInputPath(
      job,
      new Path(args[0]),
      TextInputFormat.class,
      UserRatingMapper.class
    );
    MultipleInputs.addInputPath(
      job,
      new Path(args[1]),
      TextInputFormat.class,
      MovieMapper.class
    );
    FileOutputFormat.setOutputPath(job, new Path(args[2]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
