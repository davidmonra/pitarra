import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.awt.*;
class MegaViewer1 extends JFrame
{
    JTabbedPane jtp1=new JTabbedPane();
    JPanel jp1=new JPanel();
    JTextArea t1=new JTextArea();
    MegaViewer1() throws Exception
    {
        super("Pitarra");
        FileInputStream  f=new FileInputStream ("example.txt");
        DataInputStream input = new DataInputStream(f);
        BufferedReader brk=new BufferedReader(new InputStreamReader(input));       
        String s;
        while((s=brk.readLine())!=null){
        t1.append(s);
        }
        jp1.add(t1);
        jtp1.addTab("How to play",t1);
        add(jtp1);  
    }
    public static void main(String args[]) throws Exception
    {
        MegaViewer1 mv1=new MegaViewer1();
       //mv1.pack();
        mv1.setSize(450, 600);
        mv1.setVisible(true);   
        mv1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}    
