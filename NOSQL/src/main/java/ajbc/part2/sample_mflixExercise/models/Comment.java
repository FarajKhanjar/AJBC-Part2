package ajbc.part2.sample_mflixExercise.models;

import java.time.LocalDateTime;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Comment 
{
	private ObjectId id;
	private String name;
	private String email;
	private String text;
	private LocalDateTime date;
	@BsonProperty(value="movie_id")
	private ObjectId movieId;
	
	public Comment(ObjectId id, String name, String email, String text, LocalDateTime localDateTime,
			ObjectId movieId) 
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
		this.date = localDateTime;
		this.movieId = movieId;
	}
	
	public Comment(String name, String email, String text, LocalDateTime localDateTime,
			ObjectId movieId) 
	{
		this.name = name;
		this.email = email;
		this.text = text;
		this.date = localDateTime;
		this.movieId = movieId;
	}
	
	public Comment() 
	{
		
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getLocalDateTime() {
		return date;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}
	public ObjectId getMovieId() {
		return movieId;
	}
	public void setMovieId(ObjectId movieId) {
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", email=" + email + ", text="
				+ text + ", date=" + date + ", movieId=" + movieId + "]";
	}
}
