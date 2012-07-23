import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class makeSound {
	
	public static void playThis(String fileToPlay){
      	SimpleSoundPlayer sound =
            new SimpleSoundPlayer(fileToPlay);
        // create the stream to play
        InputStream soundStream =
            new ByteArrayInputStream(sound.getSamples());
        
        sound.play(soundStream);   
    }
public static void keepPlayingThis(final String fileToPlay){
	Runnable r1 = new Runnable() {
		  public void run() {
		    try {
		      while (true) {
		    	  SimpleSoundPlayer sound =
		              new SimpleSoundPlayer(fileToPlay);
		          // create the stream to play
		          InputStream stream =
		              new ByteArrayInputStream(sound.getSamples());
		    	sound.play(stream);
		        Thread.sleep(1000L);//wait a sec after
		      }
		    } catch (InterruptedException iex) {}
		  }
		};
  
	Thread thr1 = new Thread(r1);
	//plays continuously in background
	thr1.start(); 
	}
	
}
