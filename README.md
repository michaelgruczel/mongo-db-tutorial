# mongo db tutorial

# this is a very simple mongo db tutorial by examples

## chapter 0: How to install Mongo

I will skip this, please read the documentation for your operation system at http://docs.mongodb.org/manual/.

This project contains a vagrant box with mongo and jav (needed fo later examples).
So you need vagrant and virtual box and all you have to do is:

* vagrant up
* vagrant ssh

## chapter 1: What is mongodb in a few words and whats the difference to relational databases

Mongo DB is good for:

* Large volumes of structured, semi-structured and unstructured data
* Agile sprints, quick iteration, and frequent code pushes
* Flexible, easy to use object-oriented programming
* Efficient, scale-out architecture instead of expensive, monolithic architecture

Because 

* schemaless (means schema exists in code but not in the db)
* document database (json documents)
* scaling, replication, sharding is build in
* good tuning by defining tradeoff between consistencdy and performance
* fast by working in memory 

You can map

* table -> collection
* row -> document
* colums -> field

see for more details in http://docs.mongodb.org/manual/reference/sql-comparison/

## chapter 2: Insert documents

In your vagrant box

connect to mongo: 

    mongo

check help

	help

Select a Database to use: 

	use myBookStore

The databse was created on the fly if it does not exists

insert some documents into table books:

	book1 = { name : 'mongo rocks', year : 2011, authors: 'statler and waldorf' }
	book2 = { name : 'cobol rocks', year : 1700, authors: 'Igor', format: 'papyrus' }
    book3 = { name : 'cobol is better than java', year : 2010, authors: 'Hannes', 
        comments: [{user: 'idiot', message: 'thats right'}]
    }
	db.books.insert( book1 )
	db.books.insert( book2 )
	db.books.insert( book3 )

The collection books was created on the fly if it does not exists.
Lets check wheter the documents were created.

	show collections
	db.books.find()
	db.books.findOne()
	db.books.find({name : 'mongo rocks'}).pretty()

You can see mongo generated a technical id '_id', but you can define it yourself if you want

Short test: TODO

## chapter 3: find documents

### simple reads

First create some testdata. 
In your vagrant box

    cd /mongotutorial
    mongo testdata1.js
    use myBookStore
    db.books.find({pages: { $gt: 200}}).sort({year: 1}).limit(3)

You have some operators:

* lt means less than
* gt means greater than
* pretty make the output more readable
* or can be used like this $or: [{condition A},{condition B}]
* and is just a , e.g. ..find({key1:value1, key2:value2})

Short test: TODO

### indices

Now lets check the performance of this query

	db.books.find({year: 2013}).explain()

Thats bad, we need an index

	db.books.ensureIndex( { year: 1}, {background: true} )
	db.books.find({year: 2013}).explain()

### cursors

Now lets work with cursors

	db.books.find()

will only return the first elements and it will return a cursor

	var aCursor = db.books.find()
	printjson( aCursor [ 0 ] )
	while ( aCursor.hasNext() ) printjson( aCursor.next() )
	db.books.find({year: { $gt: 2012}}).sort({year: 1}).limit(3)

I am only interested in the name field, so will select the name explicitelly and disable the id field

### selection of fields

	db.books.find({year: { $gt: 2012}}, {name: 1, _id: 0}).sort({year: 1}).limit(3)

more details can be found under http://docs.mongodb.org/manual/core/read-operations-introduction/

## chapter 4: update documents

Try this

	db.books.update(
    	{ name: "mongo rocks" },
    		{
      		$set: {
        	year : 2013, authors: "statler and waldorf"
      		}
    	}
	)

## chapter 5: delete documents

Try this commands:

	db.books.remove({})
	db.books.remove({ name: "mongo rocks" })
	db.books.remove( { { name: "yellow pages" }, 1 )

Short test: What does they mean ?

## chapter 6: backup and restore

There are several options:

- copy files or
- mongodump/mongorestore
- MongoDB Management Service

Short test: Use mongodump to extract data, drop everything and restore it

## chapter 7: Monitoring

There are several options for monitoring

* mongostat 
* mongotop
* db.stats()
* db.serverStatus() 
* rs.status() 
* MongoDB Management Service (MMS) see https://mms.mongodb.com/

Short test: Play with the tools and answer the following questions: what gives me information about collections sizes, overall load of the database and frequenty used collections. Ignore MMS here.

## chapter 8: transactions and consistency ?

Mongo does not have transaction, but you can define your tradeoff between consisteny and performance.

### Write concerns

'Acknowledged' is the default write concern, that means its added to memory. With a receipt acknowledged write concern, the mongod confirms the receipt of the write operation.
It is not stored to disk so far.

When mongod returns a successful 'journaled write concern', the data is fully committed to disk and will be available after mongod restarts. With a journaled write concern, the MongoDB acknowledges the write operation only after committing the data to the journal.

There is a Replica Acknowledged, which means its replicated to secondarys, too.

For systems with multiple concurrent readers and writers, MongoDB will allow clients to read the results of a write operation before the write operation returns.

You can define in every call which concerns you want.

*w: 1 Provides acknowledgment of write operations on a standalone
*w: 4 Aknowledgement from primary and 3 secondary nodes
*w: "majority"
*j: 1 journal true/false

Anyway, you can not guarantee 'transactions over' several inserts, but there is a 2 Phase commits approach to have something like a transaction see http://docs.mongodb.org/manual/tutorial/perform-two-phase-commits/

Short test: What is the best setup regarding write concerns and journal ?

## chapter 9: replication

TODO

## chapter 10: Mongodb and java

check the Hello World Java class in mongotutorial folder

    wget http://central.maven.org/maven2/org/mongodb/mongo-java-driver/2.12.3/mongo-java-driver-2.12.3.jar
	javac -classpath ".:mongo-java-driver-2.12.3.jar" HelloWorld.java
	java -classpath ".:mongo-java-driver-2.12.3.jar" HelloWorld

Short test: Explain what the class is doing 

## chapter 11: open points:

* dropping databases
* creating and dropping collections
* MongoDB Datatypes 
* aggregation
* Sharding, you can find more infos at http://docs.mongodb.org/manual/sharding/
* MMS