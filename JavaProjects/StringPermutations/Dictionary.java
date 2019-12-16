import java.util.*;
import java.io.*;

public class Dictionary implements DictionaryInterface{
	private ArrayList<String> dictionaryWords = new ArrayList<String>();
	Scanner kb = new Scanner(System.in);
	private String word;

	public Dictionary(){}

	public Dictionary(Scanner file)throws IOException{
		while(file.hasNext())
			addWord(file.nextLine());		//add all the words to the ArrayList
	}

	public void addWord(String word){		//Go through all the lines in the file
			dictionaryWords.add(word);		//add words to the arrayList
	}

	/*If the array 'CONTAINS' the 'WORD', return true
	otherwise return false*/
	public boolean isWord(String word){
		if(dictionaryWords.contains(word)){
			return true;
		}
		return false;
	}

	//Get the number of words in the dictionary
	public int getSize(){
		return dictionaryWords.size();
	}

	public String toString(){
		return Arrays.toString(dictionaryWords.toArray());
	}
}