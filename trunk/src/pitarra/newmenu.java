import java.io.*;

import javax.swing.JFrame;
public class newmenu {
    
    
 public static void main(String args[]){
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
  //System.out.println (stringLine);
      JFrame frame = new JFrame("Pitarra");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      newmenu ta = new newmenu();
      frame.pack();
     
      frame.setSize(450, 600);
      frame.setVisible(true);


  }
  //Close the input stream
  input.close();
    }catch (Exception e){
    //Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
  }
}
