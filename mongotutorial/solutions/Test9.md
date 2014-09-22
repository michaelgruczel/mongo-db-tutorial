# Solution for test in chapter 9

## stop the current service

We will create a small local replicaset:

kill mongo by:

mongod --shutdown

or

use admin
db.shutdownServer()

## start a replica set

Create folders to store data:

sudo mkdir -p /srv/mongodb/rs0-0 /srv/mongodb/rs0-1 /srv/mongodb/rs0-2

Start 3 mongo instances:

sudo mongod --port 27017 --dbpath /srv/mongodb/rs0-0 --replSet rs0 --smallfiles --oplogSize 128
sudo mongod --port 27018 --dbpath /srv/mongodb/rs0-1 --replSet rs0 --smallfiles --oplogSize 128
sudo mongod --port 27019 --dbpath /srv/mongodb/rs0-2 --replSet rs0 --smallfiles --oplogSize 128

Log in into one instance and configure replica set:

mongo --port 27017

rsconf = {
           _id: "rs0",
           members: [
                      {
                       _id: 0,
                       host: "localhost:27017"
                      }
                    ]
         }

rs.initiate(rsconf)
rs.conf()
rs.add("localhost:27018")
rs.add("localhost:27019")
rs.status()

shut down the nodes and check how it will behave