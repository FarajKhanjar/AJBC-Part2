package ajbc.part2.lesson.main;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Runner 
{
	public static void main(String[] args) 
	{

		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://farajkhanjar:faraj12345@myfirstcluster.we6rkzw.mongodb.net/?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();
		
		
		//connection
		try(MongoClient mongoClient = MongoClients.create(settings);)
		{
			//get DB
			MongoDatabase database = mongoClient.getDatabase("sample_mflix");
			
			//how much documents in DB?
			long numOfDocuments = database.getCollection("movies").countDocuments();
			System.out.println("Num of docs in movies = "+numOfDocuments);
			
			//get & print object?
			FindIterable<Document> docs = database.getCollection("movies").find();
			int num = 100;
			int counter = 0;
			for (Document document : docs)
			{
				if(num==counter)
				{
					System.out.println("-------------------------------------------------------------------------");
					System.out.println(document.toJson());
					System.out.println("-------------------------------------------------------------------------");
					break;
				}
				counter++;
			}
			
		}
		
	}

}