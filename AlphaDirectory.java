package n1e1to5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;

public class AlphaDirectory implements Serializable {
	
	private Path workingDir;
	private DirectoryStream<Path> pathStream;
	private ArrayList<Path> pathList;
	
	public AlphaDirectory() {
		this.workingDir = null;
		this.pathStream = null;
		this.pathList = new ArrayList<>(); 
	}
	
	//Paths.get(System.getProperty("user.dir")); this is the test path for now
	public void createPathStream(String path) {		//usuario elije el destino y se utiliza como parametro
		
		try {
			
			workingDir = Paths.get(System.getProperty(path)); 	//busca el directorio y busca el contenido aqui dentro
			pathStream = Files.newDirectoryStream(workingDir);	//crea el stream para poder escribir la informacion del archivo
						
			for(Path p : pathStream) {		//mientras el stream va coje la informacion y lo añade a un arraylist
				pathList.add(p);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//coje el arraylist y imprime la informacion basica en manera alfabetica
	public void printPathStream() {
		Collections.sort(pathList);
		for (int i = 0; i < pathList.size(); i++) {
			System.out.println(pathList.get(i).toAbsolutePath());	
		}
		System.out.println();
	}
	
	
	//////Exercise 2//////
	/*Add to the class from the previous exercise the functionality to list a directory 
	tree with the contents of all its levels (recursively) so that they are printed
	to the screen in alphabetical order within each level, also indicating whether 
	it is a directory (D) or a file (F), and its last modified date.*/
	public int printPathStreamAndAttr(int count) {		//empieza desde 0 como inicial, pero el parametro es general para poder seguir contando hasta el final del array
		if (count == pathList.size()) {		//condicion basica para salir del metodo recursivo. cuando llega al ultimo indice del array, sale
			return count;
		}
		System.out.print(pathList.get(count).toAbsolutePath());		//imprime el directorio
		try {
			BasicFileAttributes attr = Files.readAttributes(pathList.get(count), BasicFileAttributes.class);	//coje los atributos de cada unidad en el array
			if (attr.isDirectory()) {		//si es una carpeta imprime al final la letra d
				System.out.println("(D)");
			} else if (attr.isRegularFile()) {
				System.out.println("(F)");	//si es un archivo imprime al final la letra f
			}
			System.out.println(" is a directory (D)? -> " + attr.isDirectory());		//devuelve true or false para ambas
			System.out.println(" is a file (F)? -> " + attr.isRegularFile());
			System.out.println(" when was it last modified? -> " + attr.lastModifiedTime());	//ultima vez modificada
			
			System.out.println();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		printPathStreamAndAttr(count + 1);		//suma uno mas cada vez que va por el metodo y asi hasta el ultimo del size del array
		return count;
	}
	
	//////Exercise 3//////
	//Modify the previous exercise. Now, instead of displaying the result on the screen,
	// it saves the result in a TXT file.
	public void outputDirectoryToTxtFile(String directory) {		//from https://www.geeksforgeeks.org/write-hashmap-to-a-text-file-in-java/
		BufferedWriter bw = null;
		
		try {

			File file = new File(directory);		//usuario escribe donde quiere que sea el directorio
			bw = new BufferedWriter( new FileWriter(file));
				
			//crear afuera para utilizar cada vez en el for loop
			Path absPath = null;
			FileTime time = null;
			String isAFile = "";
			String isADirectory = "";
			for (int i = 0; i < pathList.size(); i++) {

				absPath = pathList.get(i).toAbsolutePath();
				try {
					BasicFileAttributes attr = Files.readAttributes(pathList.get(i), BasicFileAttributes.class);
					if (attr.isDirectory()) {
						isADirectory = "(D)";
						isAFile = "no";
					} else if (attr.isRegularFile()) {
						isAFile = "(F)";
						isADirectory = "no";
					}	
					time = attr.creationTime();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				bw.write("The path is: " + absPath
						+"\n is a directory " + isADirectory
						+"\n is a file " + isAFile
						+"\n when was it last modified? ->" + time);
				bw.newLine();
				bw.flush();						
			}	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
			}
		}
		System.out.println("File created successfully.");
	}
	
	//////Exercise 4//////
	//Adds the functionality to read any TXT file and display its contents by console.
	
	
	public void readTXTFileFromDirectory(String filePath) {		//from https://www.geeksforgeeks.org/reading-text-file-into-java-hashmap/
		BufferedReader br = null;

		try {

			File file = new File(filePath); 	//usuario escoje el texto del directorio que quiere utilizar
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
				}
			}
		}

	}
}
