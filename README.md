# Advanced-Topics-in-DBMS

## Part-1: Hadoop MapReduce
#### `[cloudera@quickstart ~]$ cd Desktop/`

#### `[cloudera@quickstart Desktop]$ mkdir dataSetsProject`
#### `[cloudera@quickstart Desktop]$ cd dataSetsProject/`

#### `[cloudera@quickstart dataSetsProject]$ echo "1, Male, 17`
#### `> 2, Female, 28`
#### `> 3, Female, 25`
#### `> 4, Male, 27`
#### `> 5, Male, 35`
#### `> 6, Female, 30" > Users.txt`

#### `[cloudera@quickstart dataSetsProject]$ echo "1, CS, Comedy`
#### `> 2, ADBMS, Action`
#### `> 3, DSP, Children`
#### `> 4, OS, Comedy`
#### `> 5, WEB, Comedy`
#### `> 6, AI, Children`
#### `> 7, SIGNALS, Action" > Movies.txt`

#### `[cloudera@quickstart dataSetsProject]$ echo "2, 4, 3`
#### `> 4, 3, 3`
#### `> 4, 4, 4`
#### `> 1, 3, 1`
#### `> 5, 4, 4`
#### `> 5, 7, 5`
#### `> 5, 1, 1" > Ratings.txt`

#### `[cloudera@quickstart Desktop]$ cat dataSetsProject/*`
```
1, CS, Comedy
2, ADBMS, Action
3, DSP, Children
4, OS, Comedy
5, WEB, Comedy
6, AI, Children
7, SIGNALS, Action
2, 4, 3
4, 3, 3
4, 4, 4
1, 3, 1
5, 4, 4
5, 7, 5
5, 1, 1
1, Male, 17
2, Female, 28
3, Female, 25
4, Male, 27
5, Male, 35
6, Female, 30
```
***
#### `[cloudera@quickstart ~]$ hadoop fs -mkdir myProject`
#### `[cloudera@quickstart ~]$ hadoop fs -mkdir myProject/inputFirstJob`
#### `[cloudera@quickstart dataSetsProject]$ hadoop fs -put Users.txt  myProject/inputFirstJob/`
#### `[cloudera@quickstart dataSetsProject]$ hadoop fs -put Ratings.txt myProject/inputFirstJob/`
#### `[cloudera@quickstart ~]$ hadoop fs -mkdir myProject/inputSecondJob`
#### `[cloudera@quickstart dataSetsProject]$ hadoop fs -put Movies.txt myProject/inputSecondJob/`
#### `[cloudera@quickstart Desktop]$ hadoop jar Job1.jar FirstJob myProject/inputFirstJob/Users.txt myProject/inputFirstJob/Ratings.txt myProject/outputFirstJob`
#### `[cloudera@quickstart Desktop]$ hadoop fs -cat myProject/outputFirstJob/*`
```
MR	4, 3
MR	4, 4
MR	3, 3
MR	7, 5
MR	4, 4
```
#### `[cloudera@quickstart Desktop]$ hadoop jar Job2.jar SecondJob myProject/outputFirstJob/* myProject/inputSecondJob/Movies.txt myProject/outputSecondJob`
#### `[cloudera@quickstart Desktop]$ hadoop fs -cat myProject/outputSecondJob/*`
```
1	CS, 0.0
3	DSP, 3.0
4	OS, 3.6666666666666665
5	WEB, 0.0
6	AI, 0.0
```
### Done ✅🙂
