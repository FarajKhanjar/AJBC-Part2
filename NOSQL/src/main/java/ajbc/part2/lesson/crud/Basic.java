package ajbc.part2.lesson.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import ajbc.part2.lesson.nosql.MyConnectionString;

public class Basic 
{
	public static void main(String[] args) 
	{
		ConnectionString connectionString =  MyConnectionString.uri();
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.serverApi(ServerApi.builder()
						.version(ServerApiVersion.V1)
						.build())
				.build();

		// connection
		try (MongoClient mongoClient = MongoClients.create(settings);) {
			
			// print databases
			mongoClient.listDatabaseNames().forEach(System.out::println);
			
			final String DB_NAME = "my_own_db", COLLECTION="myCollection";
			MongoDatabase database = mongoClient.getDatabase(DB_NAME);
			System.out.println("success");
			
			System.out.println("----------------[After DB creation]-----------------");
			mongoClient.listDatabaseNames().forEach(System.out::println);
			
			//create collection
			MongoCollection<Document> myCollection = database.getCollection(COLLECTION);
			System.out.println("----------------[After creating a Collection]-----------------");
			mongoClient.listDatabaseNames().forEach(System.out::println);
			
			Document studentDoc = createStudentDoc(1,2,"Faraj","Khanjar");
			
			//Insert to collection
			InsertOneResult insertResult = myCollection.insertOne(studentDoc);
			System.out.println(insertResult.wasAcknowledged());
			
			
		}
	}
		
		public static Document createStudentDoc(int studentId, int classId, String firstName, String lastName)
		{
			Document studentDoc = new Document("student_id", studentId)
					.append("class_id", classId)
					.append("first_name", firstName)
					.append("last_name", lastName);
			List<Document> scores = new ArrayList<Document>();
			scores.add(new Document("topic","java").append("score", 99));
			scores.add(new Document("topic","English").append("score", 70));
			scores.add(new Document("topic","Math").append("score", 90));
			
			studentDoc.append("exams", scores);
			return studentDoc;
		}

}
