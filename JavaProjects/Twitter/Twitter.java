import java.util.*;
import java.io.*;
public class Twitter {
	public static void main(String[] args) throws IOException{
		boolean found = false;									//flag for file
		System.out.println("Enter the txt file to process.");
		Scanner kb = new Scanner(System.in);					//set up keyboard for input
		while(!found){
			try{
				File file = new File(kb.nextLine());			//ask user for file
				Scanner inFile = new Scanner(file);
				found = true;									//if correct file, set flag to true

				File outFile = new File("twitter.out");			//create outFile
				PrintWriter pw = new PrintWriter(outFile);

				AllTweets at = new AllTweets(inFile);			//create the list of tweets by calling
				//create the menu								//calling AllTweets constructor
				createMenu(at, pw);

				inFile.close();
			}catch(FileNotFoundException e){System.out.println("File not Found. Try Again.");}

		}

	}

	public static void createMenu(AllTweets at, PrintWriter pw)throws InputMismatchException{
		Scanner kb = new Scanner(System.in);
		int choice = 0;
		while(choice != 9){ //make the menu for choices
			System.out.println("Enter the number for the desired option:" +
			"\n  1:\tprint all tweets" + "\n  2:\tget all tweets by given author" +
			"\n  3:\tget all the tweets written on the given date" +
			"\n  4:\tget all the tweets containing the given hashtag" +
			"\n  5:\tget all the tweets written between the given dates" +
			"\n  6:\tget all the tweets by the given author on the given date" +
			"\n  7:\tremove all tweets by a given user" + "\n  8:\tremove all tweets by the given author with the given hashtag" +
			"\n  9:\texit");
			System.out.print("Choice==> ");
			try{
				//read the choice
				choice = kb.nextInt();
				desiredOption(choice, at, pw);
			}catch(InputMismatchException e){
				System.out.println("Input a number. 1-9");
				createMenu(at, pw);
			}
		}
	}

	public static void desiredOption(int choice, AllTweets at,PrintWriter pw){
		//Depending on the choice picked, print updated lists to console and to output file
		Scanner kb = new Scanner(System.in);
		if(choice == 1){
			pw.println("------------ALL TWEETS------------" + at.allTweets());
			System.out.println(at);
		}
		else if(choice == 2){
			System.out.print("What author are you searching for?" + "\n==>");
			pw.println("-------------TWEETS BY AUTHOR---------------------------------------");
			String authorInput = kb.nextLine();
			pw.println("Inputted : " + authorInput);
			pw.println(at.findAuthor(authorInput));
		}
		else if(choice == 3){
			System.out.print("Enter the date of the Tweet: yyyy-mm-dd" + "\n==>");
			pw.println("--------------TWEETS ON GIVEN DATE----------------------------------");
			String dateString = kb.nextLine();
			pw.println("Date Inputted: " + dateString);
			pw.println(at.findDate(dateString));
		}
		else if(choice == 4){
			System.out.print("What hash tag are you searching for?" + "\n==>");
			pw.println("---------------TWEETS BY HASHTAG------------------------------------");
			String hashInput = kb.nextLine();
			pw.println("Hash tag Inputted: " + hashInput);
			pw.println(at.findHashTag(hashInput));
		}
		else if(choice == 5){
			System.out.print("Enter two dates to find tweets between the two: yyyy-mm-dd" + "\n==>");
			pw.println("---------------TWEETS IN BETWEEN 2 DATES----------------------------");
			String date1, date2;
			date1 = kb.nextLine();
			date2 = kb.nextLine();
			pw.println("Dates Inputted: " + date1 + "\t" + date2);
			pw.println(at.findBetweenDates(date1, date2));
		}
		else if(choice == 6){
			System.out.print("Enter author and the date of the tweet:" + "\n==>");
			pw.println("--------------TWEETS BY AUTHOR AND DATE------------------------------");
			String author, date;
			author = kb.nextLine();
			date = kb.nextLine();
			pw.println("author Inputted: " + author + "\nDate Inputted: " + date);
			pw.println(at.findAuthorDate(author, date));
		}
		else if(choice == 7){
			System.out.print("Enter the author to remove all their tweets: " + "\n==>");
			pw.println("--------------TWEETS AFTER DELETING AUTHOR---------------------------");
			String authorD = kb.nextLine();
			pw.println("Author to delete from list: " + authorD);
			pw.println(at.removeAuthor(authorD));
		}
		else if(choice == 8){
			System.out.print("Enter the author and the hashtag to delete the tweet: " + "\n==>");
			pw.println("--------------TWEETS AFTER DELETING AUTHOR AND HASHTAG---------------");
			String authorD2 = kb.nextLine();
			String hashTag = kb.nextLine();
			pw.println("author inputted: " + authorD2 + "\nHashTag: " + hashTag);
			pw.println(at.removeAuthorHash(authorD2, hashTag));
		}
		else if(choice == 9){
			pw.close();
			System.out.println("Information has been written to 'twitter.out' file.");
			System.exit(0);
		}
		else{
			System.out.println("Not a number between 1 through 9. Try again.");
		}
	}
}
