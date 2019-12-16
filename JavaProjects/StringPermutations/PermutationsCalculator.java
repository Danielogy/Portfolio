import java.util.*;
import java.lang.*;
import java.io.*;
/*______________________________________________________________________
|	Rodriguez, Daniel					PermutationsCalculator.java		|
|	Professor Fleming													|
|	Due: 3/07/2019														|
|	LAB TWO																|
  ----------------------------------------------------------------------*/
public class PermutationsCalculator{
	//declare all variables
	private ArrayBoundedStack<String> permutations = new ArrayBoundedStack<String>();
	protected ArrayList<String> allPermutations = new ArrayList<String>();
	protected ArrayList<String> uniquePerms = new ArrayList<String>();
	protected ArrayList<String> englishWords = new ArrayList<String>();

	public ArrayList<String> allPermutations(String word) throws IOException{
		permutations.push("+" + word);				//push user input into stack
		while(!permutations.isEmpty()){
			String currWord = permutations.top(); 	//set the top element in the stack to currWord
			permutations.pop();						//pop it off the stack
			if(currWord.endsWith("+")){			//if the word ends with a plus sign ; ex: tra+
				allPermutations.add(currWord.substring(0,currWord.length()-1));	//add that word to allPermutations arrayLisy except the '+'
			}else{
				String[] charArray = currWord.split("\\+");	//split the word by the plus and store into a String array
				for(int i = 0; i <charArray[1].length(); i++){	//iterate through the array
					String newWord = charArray[charArray.length - 1];	//set newWord to the whatever is before the sign
					StringBuilder sb = new StringBuilder(newWord);		//make a String builder object of newWord
					String addWord = (newWord.charAt(i) + charArray[0] + "+" + sb.deleteCharAt(i).toString());
					permutations.push(addWord);							//push the new word into the stack to further process
				}
			}
		}

		return allPermutations;
	}

	public ArrayList uniquePermutations(String word){
		//make a new HashSet which will automatically find all the unique
		//elements in the all Permutations arrayList
		Set<String> setList = new HashSet<String>(allPermutations);

		//for each of the elements stored in setList from allPermutations, add them to the
		//uniquePerms arrayList and return it
		for(String s : setList)
			uniquePerms.add(s);
		return uniquePerms;
	}

	public ArrayList uniqueWords(String word)throws IOException{
		File file = new File("dictionary.txt");
		Scanner inFile = new Scanner(file);
		Dictionary d = new Dictionary(inFile);

		//if the words in allPermutations arrayList are found in the dictionary,
		//add them to the englishWords arrayList
		for(String s : allPermutations)
			if(d.isWord(s))
				englishWords.add(s);
		return englishWords;
	}

	public int numDuplicates(){
		int numDuplicates = 0;

		//iterate through the entire arrayList
		for(int i = 0; i < allPermutations.size()-1; i++){
			boolean found = false;	//flag to determine if duplicate or not
				for(int j = i+1; !found && j < allPermutations.size(); j++){
					//if two elements are 'equal' from the arrayList
					//set the boolean value to true and add 1 to the counter
					if(allPermutations.get(i).equals(allPermutations.get(j))){
						found = true;
						numDuplicates++;
					}
				}
			}

		//return the number of duplicates
		return numDuplicates;
	}
}