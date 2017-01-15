package assignment3;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


/**This is the main class in which the GUI is created**/
public class Assignment3 {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    
    JPanel cards; //a panel that uses CardLayout
    final static String Book = "Book"; 
    final static String Journal = "Journal";
    
    static JMenuItem menuItem;                  
    JLabel LWelcomeText = new JLabel("Adding a reference");  
    JLabel LSearchText = new JLabel("Searching a reference"); 
    JLabel LCurrAuthor = new JLabel("Author(s): ");
    JLabel LCurrPublisher = new JLabel("Publisher: ");
    JLabel LCurrCallNum = new JLabel("Call Number: ");
    JLabel LCurrTitleKeywords = new JLabel("Title Keywords: ");
    JLabel LCurrTitle = new JLabel("Title: ");
    JLabel LCurrYear = new JLabel("Year: ");
    JLabel LCurrStartYear = new JLabel("Start Year: ");
    JLabel LCurrEndYear = new JLabel("End Year: ");
    JLabel LCurrOrg = new JLabel("Organization: ");
    JLabel LMessages = new JLabel("Messages: "); 
    
    String comboBoxItems[] = { Book, Journal };
    JComboBox Reference = new JComboBox(comboBoxItems);
    
    /**This is where the reference is added and the fields are shown**/
    public void addComponentToPane(Container pane) {
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        JPanel status = new JPanel();        
        
        JTextField FcallNo = new JTextField(10);
        JTextField Fauthor = new JTextField(10);
        JTextField Ftitle = new JTextField(15);
        JTextField Fpublisher = new JTextField(10);
        JTextField Fyear = new JTextField(4);
        JTextField Forg = new JTextField(10);  
                        
        JTextArea messages = new JTextArea(5,20);
        messages.setLineWrap(true);
                
        status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));
       
        LWelcomeText.setAlignmentX(Component.LEFT_ALIGNMENT);
        LCurrTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        LCurrCallNum.setAlignmentX(Component.LEFT_ALIGNMENT);
        LCurrYear.setAlignmentX(Component.LEFT_ALIGNMENT);
        messages.setLayout(new BorderLayout());      
        
        status.add(LWelcomeText);
        status.add(LCurrCallNum);
        status.add(FcallNo);  
        status.add(LCurrAuthor);
        status.add(Fauthor); 
        status.add(LCurrTitle);
        status.add(Ftitle);
        status.add(LCurrPublisher);
        status.add(Fpublisher);
        status.add(LCurrYear);
        status.add(Fyear);
        status.add(LCurrOrg);
        status.add(Forg);
        status.add(LMessages);
        status.add(messages, BorderLayout.WEST);       
                
        Reference.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                
                //check which item in the combo box has been selected 
                /*****************************************************************/
                if(e.getItem().toString().equals("Book"))
                {
                    LCurrAuthor.setVisible(true);
                    Fauthor.setVisible(true);
                    LCurrPublisher.setVisible(true);
                    Fpublisher.setVisible(true);
                    LCurrOrg.setVisible(false);
                    Forg.setVisible(false);    
                    
                    
                    //Getting user input below from GUI but does not work!
                    /*String callNum = FcallNo.getText();
                    String author = Fauthor.getText();
                    String title = Ftitle.getText();
                    String publisher = Fpublisher.getText();
                    String year = Fyear.getText();
                    int numYear = Integer.parseInt(year);  */                 
                    //addBook( new Book(callNum, author, title, publisher, numYear) );
   
                }               
                
                if(e.getItem().toString().equals("Journal"))
                {
                    LCurrOrg.setVisible(true);
                    Forg.setVisible(true); 
                    LCurrAuthor.setVisible(false);
                    Fauthor.setVisible(false);
                    LCurrPublisher.setVisible(false);
                    Fpublisher.setVisible(false);                        
                }                              
                
                //initialize the cards in the CardLayout
                LWelcomeText.setText("Welcome to " + e.getItem());
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, (String)e.getItem());
            }
        });
       
        comboBoxPane.add(Reference);
                
        //this method creates our three cards (JPanels) for us
        createCards();
        
        //add the combo box at the top of the pane
        pane.add(comboBoxPane);
        
        //add the status pane at the center of the main container
        pane.add(status);
        
        //add the cards at the bottom of the main container
        pane.add(cards);
    }
    
    /**This is Search Panel of the GUI**/
    public void addComponentToPaneSearch(Container pane) {
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        JPanel status = new JPanel(); 
        
        JTextField FcallNo = new JTextField(10);
        JTextField Fauthor = new JTextField(10);
        JTextField Ftitle = new JTextField(15);
        JTextField Fpublisher = new JTextField(10);
        JTextField FstartYear = new JTextField(4);
        JTextField FendYear = new JTextField(4);
        JTextField Forg = new JTextField(10);  
                        
        JTextArea messages = new JTextArea(5,20);
        messages.setLineWrap(true);
                
        status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));
       
        LSearchText.setAlignmentX(Component.LEFT_ALIGNMENT);
        LCurrTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        LCurrCallNum.setAlignmentX(Component.LEFT_ALIGNMENT);
        LCurrYear.setAlignmentX(Component.LEFT_ALIGNMENT);
        messages.setLayout(new BorderLayout());
        
        JButton resetB = new JButton("RESET");
        resetB.setAlignmentX(Component.RIGHT_ALIGNMENT);  
        JButton searchB = new JButton("SEARCH");
        
        status.add(LSearchText);
        status.add(LCurrCallNum);
        status.add(FcallNo);   
        status.add(LCurrTitleKeywords);
        status.add(Ftitle);
        status.add(LCurrStartYear);
        status.add(FstartYear);
        status.add(LCurrEndYear);
        status.add(FendYear);
        status.add(LMessages);
        status.add(messages, BorderLayout.WEST);
        
        status.add(resetB, Component.BOTTOM_ALIGNMENT);
        status.add(searchB, Component.BOTTOM_ALIGNMENT);
        resetB.setVisible(true);
        searchB.setVisible(true);
                
        Reference.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){                                
                //initialize the cards in the CardLayout  
                //LSearchText.setText("Searching " + e.getItem());
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, (String)e.getItem());
            }
        });          
        
        //add the status pane at the center of the main container
        pane.add(status);
    }
  
  
    /**This is where the cards are created for the JPanels*/
    private void createCards() {
        //Create the "cards".
        JButton resetB = new JButton("RESET");
        resetB.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JButton addB = new JButton("ADD"); 
        JButton searchB = new JButton("SEARCH");
        
        JPanel card3 = new JPanel();
        card3.add(resetB);
        card3.add(addB); 
           
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card3, Book);
        cards.add(card3, Journal); 
        
    }

    /**This is where the initial frame is created and message is brought up**/
    private static void createAndShowGUI() {                                       
                
        //Create and set up the window.
        JFrame frame = new JFrame("Library Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        JLabel message = new JLabel("<html>Welcome to Library Search.<br><br>"
                + "Choose a command from the \"Commands\""
                + " menu above for adding a reference, searching references, or"
                + " quitting the program.</html>", SwingConstants.CENTER);
        frame.add(message);
        
        JMenu commandMenu = new JMenu("Commands");

        JMenuItem add = new JMenuItem("Add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                frame.remove(message);
                Assignment3 demo = new Assignment3();
                demo.addComponentToPane(frame.getContentPane());
            }
        });
        commandMenu.add(add);

        JMenuItem search = new JMenuItem("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                frame.remove(message);
                Assignment3 demo = new Assignment3();
                demo.addComponentToPaneSearch(frame.getContentPane());
            }
        });        commandMenu.add(search);

        JMenuItem quit = new JMenuItem("Quit");        
        commandMenu.add(quit);
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quit();
            }
        });

        JMenuBar bar = new JMenuBar( );
        bar.add(commandMenu);
        frame.setJMenuBar(bar);       
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        

    }
    
    private static void quit() {
        
        System.exit(0);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}