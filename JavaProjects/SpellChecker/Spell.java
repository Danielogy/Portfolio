import java.util.*;
import java.io.*;
public class Spell{
	public static void main(String[]args)throws IOException{
		Scanner kb = new Scanner(System.in);	//prepare a scanner object
		boolean fileFound = false;				//flag for validation

	while(!fileFound){
		try{
			System.out.print("Enter the file to be checked:\t");
			String filename = kb.nextLine();	//get file name from user

			File checkFile = new File(filename);
			Scanner inputFile = new Scanner(checkFile);

			/*Create a new SpellCheck object that will set the course
			for the rest of the program*/
			SpellCheck sp = new SpellCheck(inputFile);

			fileFound = true;
			}catch(FileNotFoundException e){
				System.out.println("File not found, try to enter: " +
								   " 'inputfile.txt'");
			}
		}
	}
}