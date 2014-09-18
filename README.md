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

## chapter 3: find documents

TODO

## chapter 4: update documents

TODO

## chapter 5: delete documents

TODO

## chapter 6: performance tuning

TODO

## chapter 7: backup and restore

TODO

## chapter 8: Monitoring

TODO

## chapter 9: transactions and consistency ?

TODO

## chapter 10: replication

TODO

## chapter 11: sharding

TODO

## chapter12: Mongodb and java

open points:

* dropping databases
* creating and dropping collections
* MongoDB Datatypes 