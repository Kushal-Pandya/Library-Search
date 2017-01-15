/*
 Assignment1.java (main file)
Please read README.txt before running program
 */
package assignment1;
    import java.util.ArrayList;
    import java.util.Scanner;
/**
 *
 * @author Kushal
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<LibrarySearch> object = new ArrayList();
        
        Scanner scan = new Scanner(System.in);
        int input = 0;
        int inputOp = 0;
        int valid = 0;
        
        object.add(new LibrarySearch(0, "Book"));
        object.add(new LibrarySearch(1, "Journal"));
        
        while (input != 3) {       
            System.out.println("Welcome to Library Search!");
            System.out.println("Select a operation or press 3 to exit:");
            System.out.println("1. Add a book or journal");
            System.out.println("2. Search for a book or journal");
            System.out.println("3. Quit");

            while(!scan.hasNextInt()) 
            {
                scan.next();
                System.out.println("Invalid Input Try Again");
            }
            input = scan.nextInt();

            if (input == 3)
            {
                System.exit(0);
            }
            else if (input < 4 && input > 0)
            {
                valid = 1;
                //input = input - 1;
            }
            else 
            {
                valid = 0;
                System.out.println("Invalid Input Try Again");
                continue;
            }

            menu:
            switch (input) {
                case 1:
                    while (inputOp != 3) {

                        System.out.println("\nPlease select a type of entry:");
                        System.out.println("1. Book");
                        System.out.println("2. Journal");
                        System.out.println("3. Back to main menu");

                        while(!scan.hasNextInt()) {
                            scan.next();
                            System.out.println("Invalid Input Try Again");
                        }
                        inputOp = scan.nextInt();

                        if (inputOp < 4 && inputOp > 0)
                        {
                            valid = 1;
                        }
                        else if (inputOp == 3)
                        {
                            break menu;
                        }
                        else 
                        {
                            valid = 0;
                            System.out.println("Invalid Input Try Again");
                            continue;
                        } 
                        //System.out.println("Successful");

                        switch (inputOp) {

                            case 1:
                                System.out.println("Enter Call Number: ");
                                String callNum = scan.nextLine();
                                scan.nextLine();

                                System.out.println("Enter Author(s): ");
                                String author = scan.nextLine();

                                System.out.println("Enter Title of book: ");
                                String title = scan.nextLine();

                                System.out.println("Enter Publisher: ");
                                String publisher = scan.nextLine();

                                System.out.println("Enter year: ");
                                while(!scan.hasNextInt()) 
                                {
                                    scan.next();
                                    System.out.println("Please enter a NUMBER for year:");
                                }
                                valid = 0;
                                while (valid != 1) 
                                {
                                    int numYear = scan.nextInt();
                                    if (numYear < 9999 && numYear > 1000) {                                     
                                        object.get(0).addbook(callNum, numYear, author, title, publisher);
                                        valid = 1;                                     
                                    }
                                    else System.out.println("Please enter a valid year:");
                                }                                      
                                break menu;

                            case 2:
                                System.out.println("Enter Call Number: ");
                                callNum = scan.nextLine(); 
                                scan.nextLine();

                                System.out.println("Enter Title of book: ");
                                title = scan.nextLine();

                                System.out.println("Enter Organization: ");
                                String org = scan.nextLine();
                                
                                System.out.println("Enter year: ");
                                while(!scan.hasNextInt()) 
                                {
                                    scan.next();
                                    System.out.println("Please enter a NUMBER for year:");
                                }
                                valid = 0;
                                while (valid != 1) 
                                {
                                    int numYear = scan.nextInt();
                                    if (numYear < 9999 && numYear > 1000) {
                                        object.get(1).addJournal(callNum, numYear, title, org);
                                        valid = 1;                                     
                                    }
                                    else System.out.println("Please enter a valid year:");
                                }                                      
                                break menu;       
                        }                        
                    }
                
                case 2:
                    System.out.println("Enter Call Number: ");
                    String callNum = scan.nextLine();
                    scan.nextLine();

                    System.out.println("Enter Title Keywords: ");
                    String keyWord = scan.nextLine();

                    System.out.println("Enter a Time Period in years (XXXX-XXXX): ");
                    String year = scan.nextLine();
                    
                    String[] token = year.split("-");
                    int year1 = Integer.parseInt(token[0]);
                    int year2 = Integer.parseInt(token[1]);
                
                    object.get(0).searchBooks(callNum, year1, year2, keyWord);
                    object.get(1).searchJournals(callNum, year1, year2, keyWord);
                    
                    
            }
        }
    }
}
