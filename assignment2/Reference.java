/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author Kushal
 */
/**New parent reference class that has methods that exist for Journal and Book
    Returns its own methods and instance variables**/
public class Reference {
    
    private String callNum;
    private String title;
    private int numYear;
    
    public Reference (String callNum, int numYear, String title) {
        this.callNum = callNum;
        this.numYear = numYear;
        this.title = title;
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
}
