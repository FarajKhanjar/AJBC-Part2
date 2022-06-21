package ajbc.part2.ChairExercise.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import ajbc.part2.ChairExercise.models.Chair;

import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


public class ChairDAO 
{
	Gson gson = new Gson();

	private MongoCollection<Document> chairsCollection;

	public ChairDAO(MongoCollection<Document> chairsCollection) 
	{
		this.chairsCollection = chairsCollection;
	}

	public void insertChairToDB(Chair chair) 
	{
		String chairJson = gson.toJson(chair);
		chairsCollection.insertOne(Document.parse(chairJson));
		System.out.println("Chair Added to DataBase");

	}

	public void insertListOfChairToDB(List<Chair> chairs) 
	{
		List<Document> chairsDocs = new ArrayList<Document>();
		String chairJson;
		for (Chair chair : chairs) 
		{
			chairJson = gson.toJson(chair);
			chairsDocs.add(Document.parse(chairJson));
		}
		chairsCollection.insertMany(chairsDocs);
		System.out.println("Added list of chaires to DataBase");
	}

	public Chair getChairByID(String id) 
	{
		Document chair = chairsCollection.find(eq("_id", new ObjectId(id))).first();
		return gson.fromJson(gson.toJson(chair), Chair.class);
	}

	public List<Chair> getAllStools() 
	{
		FindIterable<Document> iterable = chairsCollection.find(eq("isStool", true));
		return turnDocumetToChairsList(iterable);
	}

	public List<Chair> getChairByManufacturer(String manufacturer) 
	{
		FindIterable<Document> iterable = chairsCollection.find(eq("manufacturer", manufacturer));
		return turnDocumetToChairsList(iterable);
	}

	public List<Chair> getChairInPriceRange(int minPrice, int maxPrice) 
	{
		FindIterable<Document> iterable = chairsCollection.find(and(gte("price", minPrice), lte("price", maxPrice)));
		return turnDocumetToChairsList(iterable);
	}

	public List<Chair> getChairByManufacturersList(List<String> manufacturers) 
	{
		FindIterable<Document> iterable = chairsCollection.find(in("manufacturer", manufacturers));
		return turnDocumetToChairsList(iterable);
	}

	public List<Chair> getChairUnderHeight(int height) 
	{
		FindIterable<Document> iterable = chairsCollection.find(lte("measurment.height", height));
		return turnDocumetToChairsList(iterable);
	}

	public Chair updateChair(Chair chair) 
	{
		FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
		Document newChair = chairsCollection.findOneAndUpdate(eq("_id", chair.getId()),
				combine(set("manufacturer", chair.getManufacturer()), set("modelName", chair.getModelName()),
						set("measurment.height", chair.getMeasurment().getHeight()),
						set("measurment.width", chair.getMeasurment().getWidth()), set("isStool", chair.isStool()),
						set("measurment.depth", chair.getMeasurment().getDepth()), set("price", chair.getPrice())),
				optionAfter);
		return gson.fromJson(gson.toJson(newChair), Chair.class);
	}

	public Chair updateChairAndReturnOldOne(Chair chair) 
	{
		FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.BEFORE);
		Document oldChair = chairsCollection.findOneAndUpdate(eq("_id", chair.getId()),
				combine(set("manufacturer", chair.getManufacturer()), set("modelName", chair.getModelName()),
						set("measurment.height", chair.getMeasurment().getHeight()),
						set("measurment.width", chair.getMeasurment().getWidth()), set("isStool", chair.isStool()),
						set("measurment.depth", chair.getMeasurment().getDepth()), set("price", chair.getPrice())),
				optionAfter);
		return gson.fromJson(gson.toJson(oldChair), Chair.class);
	}

	public void updateListOfChairs(List<Chair> chairs) 
	{
		chairs.forEach(chair -> updateChair(chair));
	}

	public Chair deleteChairByID(String id) 
	{
		Bson filter = eq("_id", new ObjectId(id));
		Document doc = chairsCollection.findOneAndDelete(filter);
		return gson.fromJson(doc.toJson(JsonWriterSettings.builder().indent(true).build()), Chair.class);
	}

	public void deleteChairsWithEqualOrHeigherHeight(int height) 
	{
		Bson filter = gte("measurment.height", height);
		chairsCollection.deleteMany(filter);

	}

	public void deleteChairsByManufacturer(String manufacturer) 
	{
		Bson filter = eq("manufacturer", manufacturer);
		chairsCollection.deleteMany(filter);

	}

	public void deleteCollection() 
	{
		chairsCollection.drop();
	}

	private List<Chair> turnDocumetToChairsList(FindIterable<Document> iterable) 
	{
		List<Chair> chairs = new ArrayList<Chair>();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext())
			chairs.add(gson.fromJson(gson.toJson(cursor.next()), Chair.class));
		return chairs;
	}

}