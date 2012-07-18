import java.io.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;



public class newmenu implements ActionListener, ItemListener {
    JTextArea results;
    JScrollPane scrollPane;
    String newline = "\n";

	
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Menu");
        menuBar.add(menu);
        menuItem = new JMenuItem("How To Play",KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
                return menuBar;       
    }

    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        results = new JTextArea(20, 30);
        results.setEditable(false);
        scrollPane = new JScrollPane(results);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;
    }


    public void actionPerformed(ActionEvent e) {
    	  try{
    		  // Open file
    		  FileInputStream fstream = new FileInputStream("example.txt");
    		  // Grab the file with DataInputStream
    		  DataInputStream input = new DataInputStream(fstream);
    		  BufferedReader buff = new BufferedReader(new InputStreamReader(input));
    		  String stringLine;
    		  // Read the file line by line
    		  while ((stringLine = buff.readLine()) != null)   {
    		  // Print the content on the console
    		  System.out.println (stringLine);
    		  }
    		  //Close the input stream
    		  input.close();
    		    }catch (Exception e2){
    		    //Catch exception if any
    		  System.err.println("Error: " + e2.getMessage());
    		  }
    		  
    	
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Instructions."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        results.append(s + newline);
        results.setCaretPosition(results.getDocument().getLength());
    }

	
	
	
    protected String getClassName(Object object) {
        String classString = object.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Pitarra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HowToPlayMenu demo = new HowToPlayMenu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
        frame.setSize(450, 600);
        frame.setVisible(true);
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 /*public static void main(String args[]){
  try{
  // Open file
  FileInputStream fstream = new FileInputStream("example.txt");
  // Grab the file with DataInputStream
  DataInputStream input = new DataInputStream(fstream);
  BufferedReader buff = new BufferedReader(new InputStreamReader(input));
  String stringLine;
  // Read the file line by line
  while ((stringLine = buff.readLine()) != null)   {
  // Print the content on the console
  System.out.println (stringLine);
  }
  //Close the input stream
  input.close();
    }catch (Exception e){
    //Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
  }

*/
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
          createAndShowGUI();
      }
  }
  );
}
}

 
    

