/*
 Assigment 2: See ReadMe for what this code does

LIBRARY SEARCH 

@Author: Kushal
 */
package assignment2;
    import java.util.ArrayList;
    import java.util.Scanner;
    import java.io.BufferedWriter;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.logging.Level;
    import java.util.logging.Logger;

/**
 *
 * @author Kushal
 */
public class Assignment2 {

    /**Main method which runs program and Command Loops, Most code is reused from A1**/
    public static void main(String[] args) {
        
        ArrayList<LibrarySearch> object = new ArrayList<>();
        
        Scanner scan = new Scanner(System.in);
        int input = 0;
        int inputOp = 0;
        int valid = 0;
                
        object.add(new LibrarySearch(0, "Book"));
        object.add(new LibrarySearch(1, "Journal"));
        
        if (args.length > 1) {
            loadFile(args[0], args[1], object);
        }          
        
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
                    while (inputOp != 1 || inputOp != 2) {

                        System.out.println("\nPlease select a type of entry:");
                        System.out.println("1. Book");
                        System.out.println("2. Journal");
                        
                        while(!scan.hasNextInt()) {
                            scan.next();
                            System.out.println("Invalid Input Try Again");
                        }
                        inputOp = scan.nextInt();

                        if (inputOp < 3 && inputOp > 0)
                        {
                            valid = 1;
                        }
                        else 
                        {
                            valid = 0;
                            System.out.println("Invalid Input Try Again");
                            continue;
                        } 

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
                                        valid = 1;  
                                        String org = "";
                                        object.get(0).addReference(callNum, numYear, title, author, publisher, org, inputOp);                                                                           
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
                                        author = "";
                                        publisher = "";
                                        object.get(1).addReference(callNum, numYear, title, author, publisher, org, inputOp);
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
                    String title = scan.nextLine();
                    String keyWord = title.toLowerCase();

                    /**Using Fei Song's time-period code to find separate years**/
                    int year1 = Integer.MIN_VALUE, year2 = Integer.MAX_VALUE;
                    boolean ifValid;
                    do {
                            ifValid = true;
                            System.out.println("Enter a time period as startYear-endYear, or year>: ");
                            String line = scan.nextLine();
                            if( !line.equals("") ) {
                                    int hyphen = line.indexOf('-');
                                    if( hyphen < 0 ) 
                                            year1 = year2 = Integer.parseInt(line);
                                    else {
                                            String startValue = line.substring(0, hyphen);
                                            year1 = startValue.equals("") ? Integer.MIN_VALUE : Integer.parseInt(startValue);
                                            String endValue = line.substring(hyphen + 1);
                                            year2 = endValue.equals("") ? Integer.MAX_VALUE : Integer.parseInt(endValue);
                                            if( year1 > year2 ) 
                                                    ifValid = false;
                                    }
                            }
                    } while(!ifValid);
                
                    /*checks the title's condition and calls appropriate listing method*/
                    if (keyWord.isEmpty()) {
                        object.get(0).searchReferences(callNum, year1, year2, keyWord, inputOp);
                        object.get(1).searchReferences(callNum, year1, year2, keyWord, inputOp);
                    } else {
                        object.get(0).listHashMap(callNum, keyWord.split("[ ,\n]+"), year1, year2, inputOp);
                        object.get(1).listHashMap(callNum, keyWord.split("[ ,\n]+"), year1, year2, inputOp);
                    }                                        
            }
        }
    }
    
    /**This method loads the input file and adds to the list of references**/
    public static void loadFile (String inputFile, String outputFile, ArrayList<LibrarySearch> object) {
        Scanner inputStream = null;
        try {
            String type = "";
            String callNum = "";
            String title = "";                
            int numYear = 0;
            String author = "";
            String publisher = "";
            String org = "";         
            inputStream = new Scanner(new FileInputStream(inputFile));
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                String[] result = line.split("\"");
                if("".equals(line)) {
                    break;
                }
                if (result[1].equals("")) {
                    break;
                }
                if (line.toLowerCase().startsWith("call")) {
                    callNum = result[1];
                }
                if (line.toLowerCase().startsWith("author")) {
                    author = result[1];
                }
                if (line.toLowerCase().startsWith("title")) {
                    title = result[1];
                }
                if (line.toLowerCase().startsWith("publisher")) {
                    publisher = result[1];
                }
                if (line.toLowerCase().startsWith("org")) {
                    org = result[1];
                }
                if (line.toLowerCase().startsWith("year")) {
                    numYear = Integer.parseInt(result[1]);
                }
                if (line.toLowerCase().startsWith("type")) {
                    if (result[1].equalsIgnoreCase("book")) {
                        type = "book";
                    }
                    else type = "journal";
                }            
            }  
            if (type.equalsIgnoreCase("book")) {
                int inputOp = 1;
                object.get(0).addReference(callNum, numYear, title, author, publisher, org, inputOp);                                                                                               
                System.out.println("Reference is loaded");
            }
            else {
                int inputOp = 2;
                object.get(1).addReference(callNum, numYear, title, author, publisher, org, inputOp);                                                                                                              
                System.out.println("Reference is loaded");
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("File " + inputFile + " was not found "
                    + "or could not be opened");
            System.exit(0);
        }
        PrintWriter catalog = null;

        /**Adding to output file from the data of the input file**/
        while (inputStream.hasNextLine()) {
            try {
                catalog = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
                catalog.println(inputStream.nextLine());
                catalog.close();
            } catch (IOException ex) {
                Logger.getLogger(Reference.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
