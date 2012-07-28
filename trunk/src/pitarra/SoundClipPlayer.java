import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
   
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipPlayer extends JFrame {
	
   public Clip sClip;

// Constructor
   public SoundClipPlayer(String soundFileName) {
      try {
         // Open an audio input stream.
    	 File soundFile = new File(soundFileName);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
         // Get a sound clip resource.
         sClip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         sClip.open(audioIn);
         
      } catch (UnsupportedAudioFileException e) {
    	  System.out.println("sound error 1");
      } catch (IOException e) {
    	  System.out.println("sound error 2");
      } catch (LineUnavailableException e) {
    	  System.out.println("sound error 3");
      }
   }
   
   public void stop(){//to stop it 
	   sClip.stop();
   }
   public void play(){//play it once
	   if(sClip.isRunning())
   			sClip.stop();
	    sClip.setFramePosition(0);
		 sClip.start();
   }
   public void playItForever(){//loop it 
	   sClip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   
}