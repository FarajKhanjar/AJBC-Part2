package ajbc.part2.ChairExercise.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ajbc.part2.ChairExercise.crud.ChairDAO;
import ajbc.part2.ChairExercise.models.Chair;
import ajbc.part2.ChairExercise.models.Measurment;


public class Runner 
{
	public static void main(String[] args) 
	{
		ConnectionString connectionString = MyConnectionString.uri();
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();

		try (MongoClient mongoClient = MongoClients.create(settings);) 
		{

			// create new DB
			final String DB_NAME = "Furniture"; 
			String COLLECTION = "chairs";
			MongoDatabase db = mongoClient.getDatabase(DB_NAME);

			// create collection
			MongoCollection<Document> chairsCollection = db.getCollection(COLLECTION);
			
			//Chair chair = new Chair("Boston","Bar-101",true,590,new Measurment(37,38,61));
			List<Chair> chairsList = initListOfChairs();
			
			//create DBservice class
			ChairDAO chairDao = new ChairDAO(chairsCollection);
			
			// check ChairDAO methods
			// Insert
			//chairDao.insertChairToDB(chair);
			//chairDao.insertListOfChairToDB(chairsList);
			
			// Read 
			//Chair chairByID = chairDao.getChairByID("62b153482f0643383ea59c80");
			//System.out.println("[chairs DB]-> returned: " + chairByID);
			
			//List<Chair> stools = chairDao.getAllStools();
			//System.out.println("All stools:");
			//stools.forEach(System.out::println);
			
			//List<Chair> sameManufacturer = chairDao.getChairByManufacturer("Boston");
			//System.out.println("[chairs DB]-> All chairs from 'Boston' Manufacturer:");
			//sameManufacturer.forEach(System.out::println);
			
//			List<Chair> chairsIPriceRange = chairDao.getChairInPriceRange(100, 500);
//			System.out.println("[chairs DB]-> All chairs in the price range of '100-500'");
//			chairsIPriceRange.forEach(System.out::println);
			
//			List<String> manufacturers = Arrays.asList("Boston","Alfa","Gala");
//			List<Chair> chairsByManufacturers = chairDao.getChairByManufacturersList(manufacturers);
//			System.out.println("[chairs DB]-> All chairs by manufacturers list of 'Boston','Alfa','Gala': ");
//			chairsByManufacturers.forEach(System.out::println);
			
//			List<Chair> chairsUnderHeight = chairDao.getChairUnderHeight(80);
//			System.out.println("[chairs DB]-> All chairs under height '80': ");
//			chairsUnderHeight.forEach(System.out::println);
			
			// Update
//			Chair chairByID = chairDao.getChairByID("62b158f8e31c395431e37ee9");
//			chairByID.setManufacturer("Boston");
//			chairByID.setPrice(700);
//			chairByID.setMeasurment(new Measurment(97,45,45));
//			Chair updatedChair = chairDao.updateChair(chairByID);
//			System.out.println("updated chair: \n" + updatedChair);
			
//			Chair chairByID = chairDao.getChairByID("62b158f8e31c395431e37ee9");
//			chairByID.setManufacturer("Boston");
//			chairByID.setPrice(600);
//			chairByID.setMeasurment(new Measurment(87,45,45));
//			Chair updatedChair = chairDao.updateChairAndReturnOldOne(chairByID);
//			System.out.println("the chair before updating : \n" + updatedChair);

//			List<Chair> chairsUnderOneHeight = chairDao.getChairUnderHeight(70);
//			chairsUnderOneHeight.stream().forEach(ch -> ch.setPrice(250));
//			chairDao.updateListOfChairs(chairsUnderOneHeight);
			
			
			// Delete By Id
			//System.out.println(chairDao.deleteChairByID("62b154ce241c253b174f09e6"));	
			
			// Delete By Manufacturer
			//chairDao.deleteChairsByManufacturer("Gala");
			
			// Delete By Height
			//chairDao.deleteChairsWithEqualOrHeigherHeight(60);
		}
	}
	
	public static List<Chair> initListOfChairs()
	{
		List<Chair> chairsList = new ArrayList<Chair>();
		chairsList.add( new Chair("Alfa","A-102",false,250,new Measurment(60,60,60)));
		chairsList.add( new Chair("Cuba","C-111",false,650,new Measurment(120,130,30)));
		chairsList.add( new Chair("Gala","GBar1",true,300,new Measurment(40,40,87)));
		chairsList.add( new Chair("Boston","B-80",true,350,new Measurment(47,92,77)));
		return chairsList;
	}

}
