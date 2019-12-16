public interface DictionaryInterface{
	void addWord(String word);     //adds a new word to this dictionary
	boolean isWord(String word);   //returns true if word is in the dictionary and false otherwise
	int getSize();                 //number of words in the dictionary
    String toString();             //return String of dictionary words
}