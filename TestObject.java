package n1e1to5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

////objeto creado aparte del directorio porque causaba error al serializar el objeto directorio, no es compatible.
//este objeto solo sirve para el test de serializar objetos. 

public class TestObject implements Serializable {
	
	private String name;
	private int number;
	
	public TestObject(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "TestObject [name=" + name + ", number=" + number + "]";
	}
	
	public class EndOfFile implements Serializable {
		
	}
	
	//metodo para escribir un objeto en un archivo .ser
	public void serializeOutJavaObject(String file, TestObject test) {
		ObjectOutputStream objout;
		try {
			objout = new ObjectOutputStream(new FileOutputStream(file));	//crea el stream para es escribir al texto 'file' que escoje el usuario
			objout.writeObject(test);										//el objeto que asigna el usuario, o crea
			objout.writeObject(new EndOfFile());							//un un while o for loop se utiliza esto para marcar el final del archivo/texto
			objout.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	//metodo para leer el objeto de nuevo del archivo .ser
	public void serializeInJavaObject(String file) {
		ObjectInputStream objin;
		try {
			objin = new ObjectInputStream(new FileInputStream(file));		//crea el stream a dentro
			Object obj = null;
			while ((obj = objin.readObject()) instanceof EndOfFile == false) {		//mientras que haya objetos para leer, y no llega al final de la instancia creada previamente, seguira a imprimir
				System.out.println("Name " + ((TestObject)obj).getName() + " number " + ((TestObject)obj).getNumber()); //cojiendo los valores individuales, pero se puede 
			}
			objin.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
