import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipPlayer extends JFrame {
	
   public Clip sClip;

// Constructor
   public SoundClipPlayer(String soundFileName, int howMany) {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Test Sound Clip");
      this.setSize(300, 200);
      this.setVisible(true);//can set to false if you want to make it invisible
      
      try {
         // Open an audio input stream.
    	 File soundFile = new File(soundFileName);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
         // Get a sound clip resource.
         sClip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         sClip.open(audioIn);
         if(howMany==1)
        	 sClip.start(); //play one time
         else
        	 sClip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
         //you can use loop to change how many times it plays if you want 
         //I figured we only needed once and repeat
         
      } catch (UnsupportedAudioFileException e) {
    	  System.out.println("sound error 1");
      } catch (IOException e) {
    	  System.out.println("sound error 2");
      } catch (LineUnavailableException e) {
    	  System.out.println("sound error 3");
      }
   }
   
   public void stop(){
	   sClip.stop();
   }
   
   
}