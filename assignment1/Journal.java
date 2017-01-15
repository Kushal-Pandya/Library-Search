/*
 * Journal.java
 */
package assignment1;

/**
 *
 * @author Kushal
 */
/**This class declares the instance variables when creating
 a new journal object, assigns them and returns them**/
public class Journal {
    
    private String callNum;
    private int numYear;
    private String title;
    private String org;
    
    /**This method assigns values to the instance variables when creating
     a new journal object**/
    public Journal (String callNum, int numYear, String title, String org) {
        this.callNum = callNum;
        this.numYear = numYear;
        this.title = title;
        this.org = org;
    }
    
    /**This method searches through the books ArrayList to find a book.
       It takes into account the relevant call number, title and years**/
    public String callNum() {
        return callNum;
    }
    
    public int numYear() {
        return numYear;
    }
    
        public String title() {
        return title;
    }
    
    public String org() {
        return org;
    }      
    
}
