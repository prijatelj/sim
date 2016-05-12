package diction;
/*
 * Database:
 * 		The Database object receives a directory path and creates a database
 *  using the .txt files within the provided directory. The files will create
 *  the main categories and every \n will separate the sub-categories within
 *  the file. The following line after \n is used as the title of the
 *  sub-category. The database object will create a constant-time accessible
 *  data structure based on the categories created, that Sim will use to 
 *  find the appropriate response to the user.
 *
 *	The name of the categories are for 
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database{
	private ArrayList <ArrayList < ArrayList <String> > > data;
	/*		Empty Constructor assumes directory is in same directory as prog
	 * 	and is names "dictionary" also assumes the extension being used is txt
	 */
	public Database(){
		createDB("dictionary", "txt");
	}
	// Constructor receives path to directory as string and assumes txt as extension
	public Database(String dir){
		createDB(dir, "txt");
	}
	//	Constructor receives path to directory and name of extension as strings
	public Database(String dir, String extension){
		if(extension.compareTo("txt") != 0){
			System.out.println("Currently, only .txt files may be used when making Databases.");
			System.exit(0);
			}
		else{
			createDB(dir, extension);
			}
	}
	//	sets the Database data from the files in provided directory.
	private void createDB(String dir, String extension){
		data = new ArrayList <ArrayList < ArrayList <String> > > ();	//	set up data for input
		try{
		Files.walk(Paths.get("src"+ File.separator + dir)).forEach(filePath -> {	//	loops through all files
			if(Files.isRegularFile(filePath)){	//	assumes only txt files are in dictionary
				System.out.println(filePath);
				data.add(new ArrayList <ArrayList <String> >());// add new main category
				subs(filePath);	//	create new sub categories
			}
		});
		}
		catch(IOException e){
			System.out.println("Error " + e );
			System.exit(0);
		}
	}
	//	create the sub-categories for the database
	private void subs(Path filePath){
		try{
			Scanner sc = new Scanner(filePath);
			boolean first = true;
			String line = new String();
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if (first){	//	make first new sub-category under main category (special case)
					first = false;
					// line is currently the title of the sub cat., skip.
					data.get(data.size()-1).add(new ArrayList <String> ());	//	add sub-category
					//line = sc.nextLine();
				}
				//	I was running into a problem where I could not identify the singular char/token returned by
				//		nextLine(). So we're doing this as a work around.
				else if (line.length()<=1){	//	if line only contains an end line symbol
					System.out.println("line = " + line);
					data.get(data.size()-1).add(new ArrayList <String> ());	//	add sub-category
					//line = sc.nextLine();// move to title of new sub-cat. currently doing nothing w/ titles.
				}
				else{	// currently on content to be written to current sub-cat.
					data.get(data.size()-1).get((data.get(data.size()-1).size()-1)).add(line);// add line to sub-cat
				}
			}
			sc.close();
		}
		catch(IOException e){
			System.out.println("Error " + e );
			System.exit(0);
		}
	}
	protected boolean isEmpty(){	//	checks if database data is empty or not
		return data.isEmpty();
	}
	protected int size(){	//	# of main categories
		return data.size();
	}
	protected int size(int i){	//	# of sub categories under given main category
		if (i < data.size())
			return data.get(i).size();
		else{
			System.out.println("Error: DNE: index for category " + i + " does not exist.");
			System.exit(0);
		}
		return 0;
	}
	protected int size(int i, int j){	//	# of response (strings) in given sub-cat of given main cat.
		if (i < data.size()) {
			if (j < data.get(i).size())
				return data.get(i).get(j).size();
			else {
				System.out.println("Error: DNE: index for sub-category " + j + " does not exist under category "+ i +".");
				System.exit(0);
			}
		} else {
			System.out.println("Error: DNE: index for category " + i + " does not exist.");
			System.exit(0);
		}
		return 0;
	}
	
	//	For getting content from the 3 leveled arraylist
	protected ArrayList < ArrayList <ArrayList <String> > > get(){	//	returns data arrayList
		return data;
	}
	protected ArrayList < ArrayList <String> > get(int i){	//	returns main category i
		if (i < data.size())
			return data.get(i);
		else{
			System.out.println("Error: DNE: index for category " + i + " does not exist.");
			System.exit(0);
		}
		return null;
	}
	protected ArrayList <String> get(int i, int j){	//	returns sub-cat arrayList of String responses
		if (i < data.size()) {
			if (j < data.get(i).size())
				return data.get(i).get(j);
			else {
				System.out.println("Error: DNE: index for sub-category " + j + " does not exist under category "+ i +".");
				System.exit(0);
			}
		} else {
			System.out.println("Error: DNE: index for category " + i + " does not exist.");
			System.exit(0);
		}
		return null;
	}
	protected String get(int i, int j, int k){	//	returns single response string located at [i,j,k]
		if (i < data.size()) {
			if (j < data.get(i).size())
				if (k < data.get(i).get(j).size())
					return data.get(i).get(j).get(k);
				else {
					System.out.println("Error: DNE: index for respone "+k+" in sub-category " + j + " does not exist under category "+ i +".");
					System.exit(0);
				}
			else {
				System.out.println("Error: DNE: index for sub-category " + j + " does not exist under category "+ i +".");
				System.exit(0);
			}
		} else {
			System.out.println("Error: DNE: index for category " + i + " does not exist.");
			System.exit(0);
		}
		return null;
	}
	protected void print(int i, int j){	//	print all the strings in the subcat
		if (i < data.size()) {
			if (j < data.get(i).size())
				for (int x = 0; x < data.get(i).get(j).size(); x++){
					System.out.println(data.get(i).get(j).get(x));
				}
			else {
				System.out.println("Error: DNE: index for sub-category " + j + " does not exist under category "+ i +".");
				System.exit(0);
			}
		} else {
			System.out.println("Error: DNE: index for category " + i + " does not exist.");
			System.exit(0);
		}
	}
}

