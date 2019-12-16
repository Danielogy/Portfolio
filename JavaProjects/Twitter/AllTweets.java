import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
public class AllTweets{
	//set up an ABList to have all the tweets
	private ABList<Tweet> allTweets = new ABList<Tweet>();

	//constructor which will recieve a scanner object and build the list
	public AllTweets(Scanner file)throws IOException{
		buildList(allTweets, file);
	}

	//no arg constructor
	public AllTweets(){}

	public ABList<Tweet> allTweets(){
		return allTweets;
	}
	//find author based on user input
	public ABList<Tweet> findAuthor(String author){
		//set up a new list
		ABList<Tweet> authorTweets = new ABList<Tweet>();
		/*for each tweet in allTweets list, check if the author passed matches
		the author in the list
		if they match, add to new list.*/
		for(Tweet t : allTweets){
			if(t.checkAuthor(author))
				authorTweets.add(t);
		}

		for(Tweet t : authorTweets)
			System.out.println(t);		//print out the list

		return authorTweets;
	}

	public ABList<Tweet> findDate(String dateString){
		//set up new list
		ABList<Tweet> dateTweets = new ABList<Tweet>();
		/*for each tweet in allTweets list, check if the date passed matches
		the date in the list
		if they match, add to new list.*/
		for(Tweet t: allTweets){
			if(t.checkDate(dateString))
				dateTweets.add(t);
		}

		for(Tweet t: dateTweets){
			System.out.println(t);		//print out the list
		}

		return dateTweets;
	}

	public ABList<Tweet> findHashTag(String hashTag){
		//set up new list
		ABList<Tweet> hashTweets = new ABList<Tweet>();
		/*for each tweet in allTweets list, check if the hashtag passed matches
		the hashtag in the list
		if they match, add to new list.*/
		for(Tweet t: allTweets){
			if(t.checkTag(hashTag))
				hashTweets.add(t);
		}

		for(Tweet t: hashTweets)
			System.out.println(t);		//print out the list

		return hashTweets;
	}

	public ABList<Tweet> findBetweenDates(String date1, String date2){
		//set up new list
		ABList<Tweet> datesBetweenTweets = new ABList<Tweet>();

		//set up the local date format
		DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
		//make the strings passed into local date objects named min and max
		LocalDate min = LocalDate.parse(date1, format); //min = 2016-10-01
		LocalDate max = LocalDate.parse(date2, format);	//max = 2016-10-05*/

		//if min < max or if they are the same dates, it is valid input
		if(min.compareTo(max) < 0 || min.compareTo(max) == 0){
			/*for each tweet in all tweet, check if the date is between the two local
			date objects. If they are, add that tweet to the list.*/
			for(Tweet t : allTweets){
				if(t.getDate().compareTo(min) >= 0 && t.getDate().compareTo(max) <= 0)
					datesBetweenTweets.add(t);
			}
		}
		else
			System.out.println("Out of bounds.");		//other wise invalid input. Ex: 2018-10-01 ~ 2017-9-30

		for(Tweet t: datesBetweenTweets)
			System.out.println(t);						//print out the list

		return datesBetweenTweets;
	}

	public ABList<Tweet> findAuthorDate(String author, String date){
		//set up the new list
		ABList<Tweet> authorDates = new ABList<Tweet>();
		//if the author and date passed matches any object in the allTweets list, add to list.
		for(Tweet t : allTweets){
			if((t.checkAuthor(author)) && ((t.checkDate(date))))
				authorDates.add(t);
		}

		for(Tweet t : authorDates)
			System.out.println(t);						//print list

		return authorDates;
	}

	public ABList<Tweet> removeAuthor(String author){
		/*Using a for each loop here would make the list unstable so an iterator should be used
		to remove elements from the list properly - check if the author passed matches any object in
		the allTweets list. If they match, remove that object.*/
		for(Iterator<Tweet> i = allTweets.iterator(); i.hasNext();){
			Tweet t = i.next();
			if(t.checkAuthor(author))
				i.remove();
		}
		for(Tweet t : allTweets)
			System.out.println(t);						//print out the updated allTweets list

		return allTweets;
	}

	public ABList<Tweet> removeAuthorHash(String author, String hash){
		/*Again, iterator should be used to properly remove elements. If the author and hashtag
		passed matches any of the object in the list, remove it from the allTweets list.*/
		for(Iterator<Tweet> i = allTweets.iterator(); i.hasNext();){
			Tweet t = i.next();
			if(t.checkAuthor(author) && t.checkTag(hash))
				i.remove();
		}
		for(Tweet t : allTweets)
			System.out.println(t);						//print out updated allTweets list

		return allTweets;
	}


	private void buildList(ABList<Tweet> tweets, Scanner file){
		//private helper method that will build the allTweets list from the input file.
		String author;
		String dateString;
		String textString;

		//iterate through all the contents of the file
		while(file.hasNextLine()){
			author = file.next();
			dateString = file.next();
			textString = file.nextLine().trim();

			//make a new tweet object from the extracted info
			tweets.add(new Tweet(author, dateString, textString));
		}
	}

	//toString representation of AllTweets object
	public String toString(){
		String str = "";
		for(Tweet t : allTweets)
			System.out.println(t);
		return str;
	}

}