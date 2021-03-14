package com.challenge.message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class SecretMessage {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("D:/Mensaje.txt"));
			String lengths = "";
			String instruction1 = "";
			String instruction2 = "";
			String message = "";
			
			int i = 0;
			while (scanner.hasNextLine()) {
				i++;
				switch(i) {
					case 1:
						lengths = scanner.nextLine();
						break;
					case 2:
						instruction1 = scanner.nextLine();
						break;
					case 3:
						instruction2 = scanner.nextLine();
						break;
					case 4:
						message = scanner.nextLine();
						break;
				}
			}
			scanner.close();
			
			message = deleteDuplicateLetters(message);
			
			createFile(message.contains(instruction1), message.contains(instruction2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static String deleteDuplicateLetters(String word) {
	    if (word.length() <= 1) {
	        return word;
	    }

	    if (word.substring(0,1).equalsIgnoreCase(word.substring(1,2))) {
	        return deleteDuplicateLetters(word.substring(0, 1) + word.substring(2));
	    }
	    else {
	        return word.substring(0,1) + deleteDuplicateLetters(word.substring(1));
	    }
	}
	
	public static void createFile(boolean instruction1, boolean instruction2) {
		try {
		      File myObj = new File("D:\\message-result.txt");
		      
		      myObj.createNewFile();
		      writeFile(instruction1 ? "Si" : "No", instruction2 ? "Si" : "No");		      
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	public static void writeFile(String res1, String res2) {
		try {
	          FileWriter myWriter = new FileWriter("D:\\message-result.txt");
	          myWriter.write(res1);
	          myWriter.write("\n");
	          myWriter.write(res2);
	          myWriter.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	}
}
