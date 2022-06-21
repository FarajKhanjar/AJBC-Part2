package ajbc.part2.sample_mflixExercise.main;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;


import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import ajbc.part2.lesson.nosql.MyConnectionString;
import ajbc.part2.sample_mflixExercise.models.Comment;
import ajbc.part2.sample_mflixExercise.models.Movie;

public class Runner {

	public static void main(String[] args) 
	{

				// prepare codec registry
				CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
				CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
				
				MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(MyConnectionString.uri())
						.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
						.codecRegistry(codecRegistry)
						.build();

				try (MongoClient mongoClient = MongoClients.create(settings)) 
				{
					MongoDatabase myDb = mongoClient.getDatabase("sample_mflix");
					MongoCollection<Movie> moviesCollection = myDb.getCollection("movies", Movie.class);
					MongoCollection<Comment> commentsCollection = myDb.getCollection("comments", Comment.class);
					
					//Read
					Movie movie1 = moviesCollection.find(Filters.eq("_id", new ObjectId("573a1391f29313caabcd8979"))).first();
					System.out.println(movie1);
					
					//Read
					List<Comment> commentsList = commentsCollection.find(Filters.eq("movie_id", movie1.getId())).into(new ArrayList<>());
					commentsList.forEach(System.out::println);
					
				}

	}

}
