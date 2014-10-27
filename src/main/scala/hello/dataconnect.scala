package hello

import com.mongodb.{MongoClientURI, MongoClient}

/**
 * Created by NIKHIL on 10/25/2014.
 */
class dataconnect {

  val uri = "mongodb://wallet:wallet@ds047930.mongolab.com:47930/wallet"
  val mongoClientURI = new MongoClientURI(uri)
  val mongoClient = new MongoClient(mongoClientURI)
  val db = mongoClient.getDB("wallet")
  val coll = db.getCollection("test")
  val dbCursor = coll.find()
  while (dbCursor.hasNext) {
    val o = dbCursor.next()
    println(o)
  }
}
