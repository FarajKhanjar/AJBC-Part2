package ajbc.part2.lesson.crud;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;

import ajbc.part2.lesson.models.Exam;
import ajbc.part2.lesson.models.Student;
import ajbc.part2.lesson.nosql.MyConnectionString;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class POJOMapping 
{
	public static void main(String[] args) 
	{
		// set logger to Errors only
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);

		// prepare codec registry
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(MyConnectionString.uri())
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
				.codecRegistry(codecRegistry)
				.build();

		try (MongoClient mongoClient = MongoClients.create(settings)) {
			MongoDatabase myDb = mongoClient.getDatabase("my_own_db");
			MongoCollection<Student> studentsCollection = myDb.getCollection("myCollection", Student.class);
			
			//Create 
			List<Exam> grades = Arrays.asList(
					new Exam("Java", 89), 
					new Exam("Greek", 3));
			Student student = new Student(123456, 789, "Jojo", "Halastra", grades); 
//			System.out.println(student);
			
			InsertOneResult result = studentsCollection.insertOne(student);
			System.out.println(result.wasAcknowledged());
			
			//Read
			
			Student student1 = studentsCollection.find(Filters.eq("_id", new ObjectId("62aaeb856cfad93c13e24489"))).first();
			System.out.println(student1);
			
			//Update

			//remove all exams of student1
			student1.setExams(new ArrayList<Exam>());
			FindOneAndReplaceOptions returnAfterOption = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
			Student student2 = studentsCollection.findOneAndReplace(Filters.eq("_id", student1.getId()), student1, returnAfterOption);
			System.out.println(student2);
			
			//Delete
			Bson rogerFilter = Filters.eq("_id", student1.getId());
			studentsCollection.deleteOne(rogerFilter);
			
		}
	}
}
