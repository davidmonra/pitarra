import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class DisplayInstructions {

		public static void main (String[] args) throws IOException{
			JFrame frame = new JFrame ("I");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JTextArea ta = new JTextArea (20,30);
			JFileChooser chooser = new JFileChooser("Z:/Working/4953/menu/example.txt");
			
			
		//int status = chooser.showOpenDialog(null);
			
		//if(status != JFileChooser.APPROVE_OPTION)
				//ta.setText("No File Chosen");
			//else{
				File file = chooser.getSelectedFile();
				Scanner scan = new Scanner(file);
				
				String info = "";
				while(scan.hasNext())
					info += scan.nextLine() + "\n";
				
				ta.setText(info);
				
			//}
			frame.getContentPane().add(ta);
			frame.pack();
			frame.setVisible(true);
			
		}
}
