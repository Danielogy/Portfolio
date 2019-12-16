import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;

public class Tweet {
	private String author;			//hold author
	private String dateString;		//hold the dateString
	private String text;			//hold the text
	private LocalDate ds;			//hold the dateStamp

	public Tweet(){}				//no arg constructor

	public Tweet(String author, String dateString, String text){
		this.author = author;
		this.text = text;

		//initialize the dateString to everything before the T in the timeStamp
		this.dateString = dateString.substring(0, dateString.indexOf("T"));

		//turn the substring into a LocalDate object
		DateTimeFormatter format2 = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate dateStamp = LocalDate.parse(this.dateString, format2);
		ds = dateStamp;
	}

	//check if the arguments are equal to this object's fields.
	public boolean checkAuthor(String author){
		return(this.author.equals(author));
	}

	public boolean checkDate(String dateString){
		return(this.dateString.equals(dateString));
	}

	public boolean checkTag(String hashTag){
		return(text.contains(hashTag));
	}

	public LocalDate getDate(){
		return ds;
	}

	//toString representation of object
	public String toString(){
		return "\n" + author + "\nDate: " + dateString + "\ntext: " + text + "\n\n";
	}
}
