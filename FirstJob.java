import java.io.IOException;
import java.util.ArrayList;
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

public class FirstJob {

  public static class UserMapper
    extends Mapper<LongWritable, Text, Text, Text> {

    private Text userID = new Text(), result = new Text();

    public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
      String v = value.toString();
      String[] tokens = v.split(", ");
      if (Integer.parseInt(tokens[2]) > 25) {
        userID.set(tokens[0]);
        result.set("User");
        context.write(userID, result);
      }
    }
  }

  public static class RatingMapper
    extends Mapper<LongWritable, Text, Text, Text> {

    private Text userID = new Text(), result = new Text();

    public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
      String v = value.toString();
      String[] tokens = v.split(", ");
      if (Double.parseDouble(tokens[2]) > 2) {
        userID.set(tokens[0]);
        result.set(tokens[1] + ", " + tokens[2]);
        context.write(userID, result);
      }
    }
  }

  public static class UserRatingReducer
    extends Reducer<Text, Text, Text, Text> {

    private Text myKey = new Text("MR");

    public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
      ArrayList<Text> ratingG2 = new ArrayList<Text>();
      int valuesSize = 0, ratsSize = 0;
      for (Text val : values) {
        String v = val.toString();
        if (v.charAt(0) != 'U') {
          ratingG2.add(new Text(v));
          ratsSize++;
        }
        valuesSize++;
      }
      if (valuesSize != ratsSize) {
        for (Text val : ratingG2) {
          context.write(myKey, val);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "first job");
    job.setJarByClass(FirstJob.class);
    //job.setNumReduceTasks(0);
    //job.setCombinerClass(UserRatingReducer.class);
    job.setReducerClass(UserRatingReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    MultipleInputs.addInputPath(
      job,
      new Path(args[0]),
      TextInputFormat.class,
      UserMapper.class
    );
    MultipleInputs.addInputPath(
      job,
      new Path(args[1]),
      TextInputFormat.class,
      RatingMapper.class
    );
    FileOutputFormat.setOutputPath(job, new Path(args[2]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
