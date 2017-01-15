/*
 * LibrarySearch.java
 */
package assignment2;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Kushal
 */
/** This class generates the adding and searching of objects, It starts off creating the instance variables and creates the ArrayList**/
public class LibrarySearch {
        
    private int index;
    private String type;
    
    private String callNum;
    private int numYear;
    private String author;
    private String title;
    private String publisher;
    private String org;

    
    private ArrayList<Reference> references = new ArrayList<>();
    private HashMap<String, HashSet<Integer>> hMap = new HashMap<>();
   
    /**This method returns the instance variables  **/
    public LibrarySearch(int index, String type) {
        this.index = index;
        this.type = type;
    }
    
    /**This method adds a new reference to the ArrayList of references as well as adds to HashMap and prints to file  **/
    public void addReference (String callNum, int numYear, String title, String author, String publisher, String org, int input) {
        int checkKey = 0;
	input--;
        /*for loop to go through all references*/
        for (Reference reference : references) {
            if (reference.callNum().equals(callNum) && 
                    reference.numYear() == numYear) {
                checkKey = 1;
                break;
            }
        }
        
        if (checkKey == 0) {
            if (input == 0) {
                references.add(new Book(callNum, numYear, title, author, publisher));
                System.out.println("Book Added.");
            } else if (input == 1) {
                references.add(new Journal(callNum, numYear, title, org));
                System.out.println("Journal Added.");
            }
            catalogReference("Output.txt", input, callNum, numYear, title , author, 
                    publisher, org);
            addHashValue(title.split("[ ,\n]+"));
        } else if (checkKey == 1) {
            System.out.println("Object Key already exists!");
            if (input == 0) {
                System.out.println("Book was Not Added.");
            } else if (input == 1) {
                System.out.println("Journal was Not Added.");
            }
        }
    }              

    /**This method searches through the reference ArrayList and HashMap to find a book or journal**/    
    public void searchReferences(String callNum, int year1, int year2, String word, int input) {
        		//for loop to go through all references
        for (Reference reference : references) {
            if (year1 <= reference.numYear() && reference.numYear() <= year2 && 
                    (reference.callNum().equalsIgnoreCase(callNum) || callNum.isEmpty())) {
                System.out.println(reference.toString());
            }
        }
    }
    
    public void listHashMap(String callNum, String[] keywords, int year1, int year2, int input){
        HashSet<Integer> indices = new HashSet<>();
        if (hMap.containsKey(keywords[0])) {
            indices.addAll(hMap.get(keywords[0]));
        } else {
            return;
        }
        
        for (String keyword: keywords){
            if (hMap.containsKey(keyword)) {
                indices.retainAll(hMap.get(keyword));
            }
        }
        
        Iterator i = indices.iterator();        
        while (i.hasNext()) {
            Reference otherRef = references.get((int)i.next());
            if (year1 <= otherRef.numYear() && otherRef.numYear() <= year2 && 
                    (otherRef.callNum().equalsIgnoreCase(callNum) || callNum.isEmpty())) {
                    System.out.println(otherRef.toString());                           
            }
        }
    }
    
    /**This method effectively prints out the books/journals to the output file**/
    public void catalogReference(String fileName, int input, String callNum, 
            int numYear, String title, String author, String publisher,
            String org) {

        PrintWriter catalog;
        try {
            catalog = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            if (input == 1) {
                catalog.println("Type: \"Book\"");
                catalog.println("Call Number: " + "\"" + callNum + "\"");
                catalog.println("Author: " + "\"" + author + "\"");
                catalog.println("Title: " + "\"" + title + "\"");
                catalog.println("Publisher: " + "\"" + publisher + "\"");
                catalog.println("Year: " + "\"" + numYear + "\"");
                catalog.println();
            }
            else {
                catalog.println("Type: \"Journal\"");
                catalog.println("Call Number: " + "\"" + callNum + "\"");
                catalog.println("Title: " + "\"" + title + "\"");
                catalog.println("Organization: " + "\"" + org + "\"");
                catalog.println("Year: " + "\"" + numYear + "\"");
                catalog.println();
            }
            catalog.close();
        } catch (IOException ex) {
            Logger.getLogger(Reference.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**This method adds a HashMap value using a loop through the keywords as the key and the value bring the HashSet if integers**/
    public void addHashValue(String[] keyWords) {
        HashSet<Integer> value;
        for (String keyWord : keyWords) {
            keyWord = keyWord.toLowerCase();
            if(!hMap.containsKey(keyWord)){
                value = new HashSet();
                value.add(references.size()-1);
                hMap.put(keyWord, value);
            }
            else{
                hMap.get(keyWord).add(references.size()- 1);
            }
        }
    }
}
