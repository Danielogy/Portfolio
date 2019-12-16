import java.util.*;
import java.io.*;

public class PermutationsConsole{
	public static void main(String[] args)throws IOException{
		Scanner kb = new Scanner(System.in);	//make a scanner object to take in user Input

		System.out.println("Enter the dictionary file: "); //prompt user
		boolean found = false;							//flag for finding file
		while(!found)
			try{
				File file = new File(kb.nextLine());	//create file
				Scanner dicFile = new Scanner(file);

				found = true;							//if found, come out of loop

				Dictionary d = new Dictionary(dicFile);	//make the dictionary object
				makeQueue(kb, d);						//prepare to process words in queue

				System.out.println("Words have been processed and a 'perms.txt' file" +
								   " as well as a 'analytics.txt' file have been created.");

			}catch(FileNotFoundException e){			//exception if file not found
				System.out.println("File not found, try again!");
			}
	}

	public static void makeQueue(Scanner kb,Dictionary d)throws IOException{
		String userInput;	//variable for userInput

		//make an Unbounded Queue Object to store user Inputted words
		ArrayUnboundedQueue<String> userWords = new ArrayUnboundedQueue<String>();
		System.out.println("Enter words to process: "+"[X to stop]" +"\n-------------");

		//make the user input lowercase for easier processing of the word
		userInput = kb.nextLine().toLowerCase();

		//stop when the user enters 'x' or 'X'; continue otherwise
		while(!userInput.equals("x")){
			userWords.enqueue(userInput);				//enqueue the word to the queue
			userInput = kb.nextLine().toLowerCase();	//make word lowercase for easier processing
		}

		makeAnalytics(userWords);						//prepare to process the words
	}

	public static void makeAnalytics(ArrayUnboundedQueue<String> userWords)throws IOException{
		File outAnalytics = new File("analytics.txt");			//create analytic file
		PrintWriter analytics = new PrintWriter(outAnalytics);

		File outPermutations = new File("perms.txt");			//create permutations file
		PrintWriter perms = new PrintWriter(outPermutations);	//create a Printwriter to write to file
		perms.println("\t\t-----PERMUTATIONS TEXT-----");

		PermutationsCalculator permCalc = new PermutationsCalculator();	//create a permutationsCalc object
		ArrayList<String> perm = new ArrayList<String>();		//make an arrayList to hold all permutations
		ArrayList<String> engWords = new ArrayList<String>();	//make an arrayList to hold all english words
		ArrayList<String> uniqueWords = new ArrayList<String>();//make an arrayList to hold all unique words

		double length = 0;		//variable to hold the length of the word
		double wordCount = 0;	//holds the amount of words the user entered
		int numPerms = 0;		//holds the number of permutations created
		int duplicates = 0;		//holds the number of duplicates created
		int numEnglish =0;		//holds the number of english words created

		while(!userWords.isEmpty()){	//loop to process the word
			String wordToProcess = userWords.dequeue();	//deqeue the word to begin processing
			wordCount++;								//add one to word count
			length = length + wordToProcess.length();	//find the length of the word inputted
			perm = permCalc.allPermutations(wordToProcess);	//store all the permutations of the word into the arrayList 'perm'
			engWords = permCalc.uniqueWords(wordToProcess);	//store all the englishWords found in the permutations into 'engWords'
			numEnglish += permCalc.englishWords.size();		//keep track of the number of english words found
			uniqueWords = permCalc.uniquePermutations(wordToProcess);	//store all the unique words found in all permutations into 'uniqueWords'
			numPerms += permCalc.allPermutations.size();	//keep track of all the permutations found
			duplicates += permCalc.numDuplicates();			//keep track of all the duplicates found
			makePermutations(wordToProcess, perm,perms);	//write the data retrieved to perms.txt
			makeEngWords(engWords, perms);					//" 									"
			makeUnique(uniqueWords, perms);					//"										"
		}

		perms.close();									//close the file

		double avgLength = length / wordCount;			//find the average length of the word

		double percentage = (double)(numEnglish * 100) / numPerms;	// part/whole --> <--- x/100 (:

		//print all of the data retrieved to the analytics.txt file
		analytics.println("\t\t-----ANALYTICS TEXT-----");
		analytics.println("Total Number of Words Inputted: " + wordCount);
		analytics.println("Average length of the words inputted: " + avgLength);
		analytics.println("Duplicates: " + duplicates);
		analytics.println("Total Permutations: " + numPerms + "\nTotal Words which were English: " +
						  numEnglish);
		String s = "Percentage of Permutations which were English Words: %6.2f%%.%n";
		analytics.printf(s, percentage);

		analytics.close();	//close the file
	}

	/*The next three methods are similar, except they print out a different
	arrayList to the perms.txt file*/
	public static void makePermutations(String word,ArrayList<String> perm,
			PrintWriter perms) throws IOException{

			perms.println("\n=-=-=-=-\nPermutations generated:" + "\n--------------");
			perms.println("Original Word: " + word);
			for(String s : perm)
				perms.println(s);
			perm.clear();

	}

	public static void makeEngWords(ArrayList<String> engWords,
			PrintWriter perms) throws IOException{

			perms.println("\n=-=-=-=-\nEnglish Words Generated: " + "\n--------------");
			for(String s: engWords)
				perms.println(s);
			engWords.clear();
	}

	public static void makeUnique(ArrayList<String> uniqueWords,
			PrintWriter perms) throws IOException{

			perms.println("\n=-=-=-=-\nUnique Words Generated: " +  "\n--------------");
			for(String s : uniqueWords)
				perms.println(s);
			uniqueWords.clear();
	}

	//end main
}