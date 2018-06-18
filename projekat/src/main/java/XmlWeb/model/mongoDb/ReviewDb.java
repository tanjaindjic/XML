package XmlWeb.model.mongoDb;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ReviewDb {
	
	private ReviewDb instance = null;
	private String dbUri = "mongodb+srv://admin:admin@cluster0-v13re.mongodb.net/test?retryWrites=true";
	private MongoCollection collection;
	
	private ReviewDb() {
		MongoClientURI uri = new MongoClientURI(dbUri);
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("XML");
        collection = database.getCollection("Reviews");
	}
	
	public ReviewDb getInstance() {
		if(instance==null) {
			return new ReviewDb();
		}
		else {
			return instance;
		}
	}

	public MongoCollection getCollection() {
		return collection;
	}

	public void setCollection(MongoCollection collection) {
		this.collection = collection;
	}
	
	

}
