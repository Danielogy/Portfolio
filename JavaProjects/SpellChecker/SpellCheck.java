import java.util.*;
import java.io.*;
public class SpellCheck{
	private Dictionary d;
	Scanner kb = new Scanner(System.in);	//prepare scanner object
	private boolean fileFound = false;		//flag for file

	public SpellCheck(Scanner infile)throws IOException{
		while(!fileFound){
			try{
				System.out.print("Open the dictionary file:\t");
				String filename = kb.nextLine();	//get filename from user

				File dictionaryFile = new File(filename);
				Scanner dicfile = new Scanner(dictionaryFile);

				//make a Dictionary object to use its methods.
				d = new Dictionary(dicfile);
				System.out.println(d);
				System.out.println("Dictionary size is: " + d.getSize());

				/*Here, spell check the words from the input file
				to the dictionary and compare them to see which
				ones are not in the dictionary*/
				spellCheck(infile);

				fileFound = true;
			}catch(FileNotFoundException e){
				System.out.println("File not found, try to enter: " +
								   "\tdictionary.txt'");
			}
		}

	}

	public void spellCheck(Scanner file)throws IOException{
		File outFile = new File("results.txt");		//make the result file
		PrintWriter pw = new PrintWriter(outFile);	//to print the results to the file

		String word = "";							//prepare to extract the words
		String[] tokens;							//prepare to place words to an array
		while(file.hasNext()){
			word = file.next();						//sets word to the words in the file

			//place all of the words into the token arrays by removing punctuations
			//setting them to lowercase words, and splitting by whitespace
			tokens = word.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

			for (String i : tokens){				//for each String in the tokens array
				if(d.isWord(i) == false){			//check to see if that String is a word in the dictionary
					scanWord(i, pw);				//if it's not in dictionary, perform operations on it.
				}
			pw.println(i);							//print the words into the output file.
			}
		}
		pw.close();									//close the printwriter


		System.out.println("File has been checked, and the output has been" +
						   " sent to the file: 'results.txt'");
	}

	private void scanWord(String i, PrintWriter pw){
		try{
			System.out.print("\n" + i + " is not in the dictionary.\nWhat would you like to do?" +
						   "\n1: Replace in text file" + "\n2: add to dictionary" +
						   "\n3: continue" + "\n==>");
			int userInput;								//prepare to hold user input
			Scanner input = new Scanner(System.in);		//prepare scanner object
			userInput = input.nextInt();				//get user input
			if(userInput == 1)
				replaceWord(i, pw);						//if user enters 1, replace the word in text file
			else if(userInput == 2){
				d.addWord(i);							//if 2, add word to dictionary
				System.out.println(i + " added to dictionary.");
			}
			else if(userInput == 3)						//if 3, just continue
				System.out.println("Continuing to check file..");

			while(userInput < 1 || userInput > 3){		//check to make sure user enters the right #'s.
				System.out.print("ERROR! : Enter either 1, 2, or 3" +
								   "\n==>");
				userInput = input.nextInt();
			}
		}catch(InputMismatchException e){
			System.out.print("ERROR! : You must enter a number.");
			scanWord(i, pw);
		}
	}

	private String replaceWord(String word, PrintWriter pw){
		String stringReplace;					//string to replace the word
		Scanner kb = new Scanner(System.in);

		System.out.print("Enter Word to replace " + word +
						 "\n==>");

		stringReplace = kb.nextLine();			//get the word to replace it
		pw.print(stringReplace.replace(word, stringReplace));	//replace bad word, with the user word

		return stringReplace;
	}
}