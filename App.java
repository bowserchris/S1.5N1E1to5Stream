package n1e1to5;

import java.util.Scanner;

public class App {

	
			
	public static void main(String[] args) {
		
		//////Exercise 1//////

		//Create a class that alphabetically lists the contents of a directory received by parameter.

		AlphaDirectory alpha = new AlphaDirectory();
		
		
		alpha.createPathStream("user.dir");		//inputString("What´s the directory you wish to see?")
		
		alpha.printPathStream();
		
		//////Exercise 2//////
		/*Add to the class from the previous exercise the functionality to list a directory 
		tree with the contents of all its levels (recursively) so that they are printed
		 to the screen in alphabetical order within each level, also indicating whether 
		 it is a directory (D) or a file (F), and its last modified date.*/
		
		alpha.printPathStreamAndAttr(0);
		
		//////Exercise 3//////
		//Modify the previous exercise. Now, instead of displaying the result on the screen,
		// it saves the result in a TXT file.
		
		alpha.outputDirectoryToTxtFile("directoryList.txt");		//inputString("What´s the title of the document you wish to create in the directory?")
		
		//////Exercise 4//////
		//Adds the functionality to read any TXT file and display its contents by console.
		
		alpha.readTXTFileFromDirectory("testfile.txt");		//inputString(""What´s the name of the file you wish to read in the directory?")
		
		//////Exercise 5//////
		//Now the program needs to serialize a Java Object to a .ser file and then deserialize it.
		TestObject test = new TestObject("henry", 5);
		
		test.serializeOutJavaObject("javaobject.ser", test);
		test.serializeInJavaObject("javaobject.ser");

	}
	
	static String inputString(String message) {				//request a string input
		Scanner input = new Scanner(System.in);
		System.out.println(message);
		return input.nextLine();
	}
	
	

}

