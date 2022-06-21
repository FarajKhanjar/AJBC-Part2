package ajbc.part2.lesson.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import ajbc.part2.lesson.nosql.MyConnectionString;

import static com.mongodb.client.model.Filters.*;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


public class Delete {
	private static final int CLASS_ID_MAX = 1;
	private static final int STUDENT_ID_MAX = 10000;
	private static final int MAX_GRADE = 100;
	private static final Random rand = new Random();

	public static void main(String[] args) 
	{
		ConnectionString connectionString =  MyConnectionString.uri();
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.serverApi(ServerApi.builder()
						.version(ServerApiVersion.V1)
						.build())
				.build();
	
		try (MongoClient mongoClient = MongoClients.create(settings);) 
		{
			JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
			final String DB_NAME = "my_own_db", COLLECTION="myCollection";
			MongoDatabase database = mongoClient.getDatabase(DB_NAME);
			MongoCollection<Document> myCollection = database.getCollection(COLLECTION);

			// delete one document
            Bson filter = eq("student_id", 10000);
            DeleteResult result = myCollection.deleteOne(filter);
            System.out.println(result);

            // findOneAndDelete operation
            filter = eq("student_id", 10002);
            Document doc = myCollection.findOneAndDelete(filter);
            System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));

            // delete many documents
            filter = gte("student_id", 10000);
            result = myCollection.deleteMany(filter);
            System.out.println(result);

            // delete the entire collection and its metadata (indexes, chunk metadata, etc).
            myCollection.drop();
			
			
		}

	}

}
