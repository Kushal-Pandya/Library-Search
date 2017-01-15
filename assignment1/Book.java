/*
 Book.java
 */
package assignment1;

/**
 *
 * @author Owner
 */
/**This method declares the instance variables when creating
a new book object, assigns them and returns them**/
public class Book {
    
    private String callNum;
    private String title;
    private int numYear; 
    private String author;
    private String publisher;
    
    /**This method assigns values to the instance variables when creating
     a new book object**/
    public Book (String callNum, int numYear, String author, String title, String publisher) {
        this.callNum = callNum;
        this.numYear = numYear;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
    }
    
    public String callNum() {
        return callNum;
    }
    
    public int numYear() {
        return numYear;
    }
    
    public String title() {
        return title;
    }
    
    public String author() {
        return author;
    }
        
    public String publisher() {
        return publisher;
    }
}
