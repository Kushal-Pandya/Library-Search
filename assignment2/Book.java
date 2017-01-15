/*
 Book.java
 */
package assignment2;

/**
 *
 * @author Kushal
 */
/**This class is a child of reference that declares the instance variables 
 * when creating a new book object, assigns them and returns them**/
public class Book extends Reference{
     
    private String author;
    private String publisher;
    
    /**This method assigns values to the instance variables when creating
     a new book object**/  
    public Book (String callNum, int numYear, String title, String author, String publisher) {
        super(callNum, numYear, title);
        this.author = author;
        this.publisher = publisher;
    }
    
    public String author() {
        return author;
    }
        
    public String publisher() {
        return publisher;
    }
    
    @Override /**Override method of toString for Book**/
    public String toString() {
        return "\nBook is found! \n"+ 
            "Call Number: " + this.callNum() +
            "\nAuthor: " + author() +
            "\nTitle: " + this.title() +
            "\nPublisher: " + publisher() +
            "\nYear: " + this.numYear() + "\n";
    }
}
