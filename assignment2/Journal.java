/*
 * Journal.java
 */
package assignment2;

/**
 *
 * @author Kushal
 */
/**This class is a child of reference that declares the instance variables 
 * when creating new journal object, assigns them and returns them**/
public class Journal extends Reference {
    
    private String org;
    
    /**This method assigns values to the instance variables when creating
     a new journal object**/
    public Journal (String callNum, int numYear, String title, String org) {
        super(callNum, numYear, title);
        this.org = org;
    }
    
    public String org() {
        return org;
    }    
    
    @Override /**Override method of toString for Journal**/
    public String toString() {
        return "\nJournal is found! \n"+ 
            "Call Number: " + this.callNum() +
            "\nTitle: " + this.title() +
            "\nOrganization: " + org() +
            "\nYear: " + this.numYear() + "\n";
    }
}
