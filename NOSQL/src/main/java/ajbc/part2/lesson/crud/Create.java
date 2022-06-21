package ajbc.part2.lesson.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;

import ajbc.part2.lesson.models.Exam;
import ajbc.part2.lesson.models.Student;
import ajbc.part2.lesson.nosql.MyConnectionString;

public class Create 
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
	
		try (MongoClient mongoClient = MongoClients.create(settings);) 
		{
			JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
			final String DB_NAME = "my_own_db", COLLECTION="myCollection";
			MongoDatabase database = mongoClient.getDatabase(DB_NAME);
			MongoCollection<Document> myCollection = database.getCollection(COLLECTION);
			
			
			Document studentDoc = Basic.createStudentDoc(1,2,"Faraj","Khanjar");
			InsertOneResult insertResult = myCollection.insertOne(studentDoc);
			System.out.println(insertResult.wasAcknowledged());
			
			//create a POJO
			List<Exam> exams = new ArrayList<Exam>();
			exams.add(new Exam("Java",89));
			exams.add(new Exam("FloorWashing",50));
			exams.add(new Exam("Math",100));
			
			Student student = new Student(1234,5678,"Moshe","Ufnik",exams);
			
			//convert to JSON
			Gson gson = new Gson();
			String studentJson = gson.toJson(student);
			//System.out.println(studentJson);
			
			//parse Json to document
			Document studentDoc1 = Document.parse(studentJson);
			//System.out.println(studentDoc1.toJson(prettyPrint));
			InsertOneResult insertResult1 = myCollection.insertOne(studentDoc1);
			System.out.println(insertResult1.wasAcknowledged());
			
			//list of student
			Student student2 = new Student(222,2154,"Shimi","Tavory",exams);
			Student student3 = new Student(333,123,"Avi","Bitter",exams);
			  //to Json
			  String studentJson2 = gson.toJson(student2);
			  String studentJson3 = gson.toJson(student3);
			  
			    //to doc
				Document studentDoc2 = Document.parse(studentJson2);
				Document studentDoc3 = Document.parse(studentJson3);

			List<Document> studentDocs = new ArrayList<>();
			studentDocs.add(studentDoc2);
			studentDocs.add(studentDoc3);
			InsertManyResult manyResult = myCollection.insertMany(studentDocs);
			boolean ack = manyResult.wasAcknowledged();
			System.out.println(ack);

		}
	}
}