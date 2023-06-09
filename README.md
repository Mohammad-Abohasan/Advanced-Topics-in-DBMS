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

***

## Part-2: NoSQL mongoDB
#### `test> use gameDB`
```
switched to db gameDB
```
#### `db`
```
gameDB
```
#### `gameDB> db.createCollection("games")`
```
{ ok: 1 }
```
#### `gameDB> show collections`
```
games
```
#### `db.games.insertMany( [ `
#### `... {name: "Mincraft", publisher: "Mohammad", year_released: 2010, rating: 4}, `
#### `... {name: "GTA V", publisher: "Ahmad", year_released: 2014, rating: 5}, `
#### `... {name: "Fortnite", publisher: "Rami", year_released: 2017, rating: 2}, `
#### `... {name: "Deathloop", publisher: "Hala", year_released: 2019, rating: 1}, `
#### `... {name: "PUBG", publisher: "Aya", year_released: 2008, rating: 3} `
#### `... ] )`
```
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId("646bed576dab34cfcc24fdf2"),
    '1': ObjectId("646bed576dab34cfcc24fdf3"),
    '2': ObjectId("646bed576dab34cfcc24fdf4"),
    '3': ObjectId("646bed576dab34cfcc24fdf5"),
    '4': ObjectId("646bed576dab34cfcc24fdf6")
  }
}
```
#### `gameDB> db.games.find()`
```
[
  {
    _id: ObjectId("646bed576dab34cfcc24fdf2"),
    name: 'Mincraft',
    publisher: 'Mohammad',
    year_released: 2010,
    rating: 4
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf3"),
    name: 'GTA V',
    publisher: 'Ahmad',
    year_released: 2014,
    rating: 5
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf4"),
    name: 'Fortnite',
    publisher: 'Rami',
    year_released: 2017,
    rating: 2
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf5"),
    name: 'Deathloop',
    publisher: 'Hala',
    year_released: 2019,
    rating: 1
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf6"),
    name: 'PUBG',
    publisher: 'Aya',
    year_released: 2008,
    rating: 3
  }
]
```
#### `gameDB> db.games.find().limit(3)`
```
[
  {
    _id: ObjectId("646bed576dab34cfcc24fdf2"),
    name: 'Mincraft',
    publisher: 'Mohammad',
    year_released: 2010,
    rating: 4
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf3"),
    name: 'GTA V',
    publisher: 'Ahmad',
    year_released: 2014,
    rating: 5
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf4"),
    name: 'Fortnite',
    publisher: 'Rami',
    year_released: 2017,
    rating: 2
  }
]
```
#### `gameDB> db.games.find().sort( { rating: -1 } ).limit(3)`
```
[
  {
    _id: ObjectId("646bed576dab34cfcc24fdf3"),
    name: 'GTA V',
    publisher: 'Ahmad',
    year_released: 2014,
    rating: 5
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf2"),
    name: 'Mincraft',
    publisher: 'Mohammad',
    year_released: 2010,
    rating: 4
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf6"),
    name: 'PUBG',
    publisher: 'Aya',
    year_released: 2008,
    rating: 3
  }
]
```
#### `gameDB> db.games.find( { rating: 5, year_released: { $gt: 2007 } } )`
```
[
  {
    _id: ObjectId("646bed576dab34cfcc24fdf3"),
    name: 'GTA V',
    publisher: 'Ahmad',
    year_released: 2014,
    rating: 5
  }
]
```
#### `gameDB> db.games.update( { rating: 3 }, { $set: { rating: 4 } } )`
```
DeprecationWarning: Collection.update() is deprecated. Use updateOne, updateMany, or bulkWrite.
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
```
#### `gameDB> db.games.find()`
```
[
  {
    _id: ObjectId("646bed576dab34cfcc24fdf2"),
    name: 'Mincraft',
    publisher: 'Mohammad',
    year_released: 2010,
    rating: 4
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf3"),
    name: 'GTA V',
    publisher: 'Ahmad',
    year_released: 2014,
    rating: 5
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf4"),
    name: 'Fortnite',
    publisher: 'Rami',
    year_released: 2017,
    rating: 2
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf5"),
    name: 'Deathloop',
    publisher: 'Hala',
    year_released: 2019,
    rating: 1
  },
  {
    _id: ObjectId("646bed576dab34cfcc24fdf6"),
    name: 'PUBG',
    publisher: 'Aya',
    year_released: 2008,
    rating: 4
  }
]
```
### Done ✅🙂
