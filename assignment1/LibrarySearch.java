/*
 * LibrarySearch.java
 */
package assignment1;
    import java.util.ArrayList;
/**
 *
 * @author Owner
 */
/** This class generates the adding and searching of objects, It starts off creating the instance variables and creates the ArrayLists**/
public class LibrarySearch {
        
    private int index;
    private String type;
    
    private String callNum;
    private int numYear;
    private String author;
    private String title;
    private String publisher;
    private String org;

    
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Journal> journals = new ArrayList<>();
   
    /**This method returns the instance variables  **/
    public LibrarySearch(int index, String type) {
        this.index = index;
        this.type = type;
    }
    
    /**This method adds a new book to the ArrayList of books  **/
    public void addbook (String callNum, int numYear, String author, String title, String publisher) {
        for (Book book : books) {
            if (callNum.equalsIgnoreCase(book.callNum())) {
                if (numYear == book.numYear()) {
                    System.out.println("Book with same call number and year exists!\n");
                    return;
                }
            }
        }
        books.add(new Book(callNum, numYear, author, title, publisher));
        System.out.println("Book is added!");
    }
    
     /**This method adds a new journal to the ArrayList of journals  **/
    public void addJournal (String callNum, int numYear, String title, String org) {
        for (Journal journal : journals) {
            if (callNum.equalsIgnoreCase(journal.callNum())) {
                if (numYear == journal.numYear()) {
                    System.out.println("Journal with same call number and year exists!\n");
                    return;
                }
            }
        }
        journals.add(new Journal(callNum, numYear, title, org));
        System.out.println("Journal is added!");
    }
    
    /**This method searches through the books ArrayList to find a book.
       It takes into account the relevant call number, title and years**/
    public void searchBooks (String callNum, int year1, int year2, String keyWord) {
        for (Book book : books){
            if (callNum.equalsIgnoreCase(book.callNum())) {
                if (book.title().contains(keyWord)) {
                    if (year1 == book.numYear() || 
                            year1 <= book.numYear() && book.numYear() <= year2) {
                        System.out.println("\nBook is found!");
                        System.out.println("Call Number: " + book.callNum());
                        System.out.println("Author: " + book.author());
                        System.out.println("Title: " + book.title());
                        System.out.println("Publisher: " + book.publisher());
                        System.out.println("Year: " + book.numYear() + "\n");
                    }
                    else System.out.println("No matches found");
                }
                else System.out.println("No matches found");
            }
            else System.out.println("No matches found");
        }
    }
    
    /**This method searches through the journals ArrayList to find a journal.
       It takes into account the relevant call number, title and years**/
    public void searchJournals (String callNum, int year1, int year2, String keyWord) {
        for (Journal journal : journals){
            if (callNum.equalsIgnoreCase(journal.callNum())) {
                if (journal.title().contains(keyWord)) {
                    if (year1 == journal.numYear() || 
                            year1 <= journal.numYear() && journal.numYear() <= year2) {
                        System.out.println("\nJournal is found!");
                        System.out.println("Call Number: " + journal.callNum());
                        System.out.println("Title: " + journal.title());
                        System.out.println("Organization: " + journal.org());
                        System.out.println("Year: " + journal.numYear() + "\n");
                    }
                    else System.out.println("No matches found");
                }
                else System.out.println("No matches found");
            }
            else System.out.println("No matches found");
        }
    }
   
    
}
