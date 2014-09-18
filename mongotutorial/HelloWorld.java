import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
 
/**
 * Java + MongoDB Hello world Example
 * 
 */
public class HelloWorld {

  public static void main(String[] args) {
 
    try {
 

	MongoClient mongo = new MongoClient("localhost", 27017);
	DB db = mongo.getDB("myBookStore");
	DBCollection table = db.getCollection("books");
 
    // Insert
	BasicDBObject document = new BasicDBObject();
	document.put("name", "java is cool");
	document.put("year", 2011);
	table.insert(document);

    // find
	BasicDBObject searchQuery = new BasicDBObject();
	searchQuery.put("name", "java is cool");
	DBCursor cursor = table.find(searchQuery);
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
	}
 
	// Update
    BasicDBObject query = new BasicDBObject();
	query.put("name", "mkyong");
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "mkyong-updated");
	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);
	table.update(query, updateObj);
 
    } catch (UnknownHostException e) {
		e.printStackTrace();
    } catch (MongoException e) {
		e.printStackTrace();
    }
 
  }
}