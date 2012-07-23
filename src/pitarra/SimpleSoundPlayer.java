import java.io.*;
import javax.sound.sampled.*;

public class SimpleSoundPlayer {
  /**  public static void main(String[] args){
	//load sound
        SimpleSoundPlayer button =
            new SimpleSoundPlayer("button-1.wav");
	//create the stream
        InputStream buttonStream =
            new ByteArrayInputStream(button.getSamples());
        // play the sound        
        button.play(buttonStream);        
        // exit
        System.exit(0);
    }
*/
    private AudioFormat format;
    private byte[] samples;

    /**
        Opens a sound from a file.
    */
    public SimpleSoundPlayer(String filename) {
        try {
            AudioInputStream stream =
                AudioSystem.getAudioInputStream(
                new File(filename));
            format = stream.getFormat();
            samples = getSamples(stream);            
        }
        catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
        Gets the samples of this sound as a byte array.
    */
    public byte[] getSamples() {
        return samples;
    }

    /**
        Gets the samples from an AudioInputStream as an array
        of bytes.
    */
    private byte[] getSamples(AudioInputStream audioStream) {
        int length = (int)(audioStream.getFrameLength() *
            format.getFrameSize());
        byte[] samples = new byte[length];
        DataInputStream is = new DataInputStream(audioStream);
        try {
            is.readFully(samples);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return samples;
    }

    /**
        Plays a stream. This method blocks (doesn't return) until
        the sound is finished playing.
    */
    public void play(InputStream source) {
        int bufferSize = format.getFrameSize() *
            Math.round(format.getSampleRate() / 10);
        byte[] buffer = new byte[bufferSize];
        SourceDataLine line;
        try {
            DataLine.Info info =
                new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine)AudioSystem.getLine(info);
            line.open(format, bufferSize);
        }
        catch (LineUnavailableException ex) {
            ex.printStackTrace();
            return;
        }
        line.start();
        try {
            int numBytesRead = 0;
            while (numBytesRead != -1) {
                numBytesRead =
                    source.read(buffer, 0, buffer.length);
                if (numBytesRead != -1) {
                   line.write(buffer, 0, numBytesRead);
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        line.drain();
        line.close();

    }
    
}



