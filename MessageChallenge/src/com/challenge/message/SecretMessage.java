package com.challenge.message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import java.io.FileWriter;
import java.io.IOException;

public class SecretMessage {

	public static void main(String[] args) {
		try {

	        JFileChooser chooser = new JFileChooser();
	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal != JFileChooser.APPROVE_OPTION) {
	            System.out.println("File wasn't selected");
	        }
	        else
	        {
	        	Scanner scanner = new Scanner(chooser.getSelectedFile());
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
	        }
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
			JFileChooser chooser = new JFileChooser(); 
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Select message destination");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			
			int returnVal = chooser.showOpenDialog(null);
			String path = "";
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				path = chooser.getSelectedFile().getPath();
				File myObj = new File(path + "\\result.txt");
			      
			    myObj.createNewFile();
			    writeFile(myObj, instruction1 ? "Si" : "No", instruction2 ? "Si" : "No");	
			}
			else {
				System.out.println("No Selection ");
			}	      
	    } 
		catch (IOException e) {
			e.printStackTrace();
	    }
	}
	
	public static void writeFile(File file, String res1, String res2) {
		try {
	          FileWriter myWriter = new FileWriter(file.getPath());
	          myWriter.write(res1);
	          myWriter.write("\n");
	          myWriter.write(res2);
	          myWriter.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	}
}
