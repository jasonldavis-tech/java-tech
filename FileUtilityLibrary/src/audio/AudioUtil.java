package audio;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioUtil {
	private static Logger logger = Logger.getLogger(AudioUtil.class.getName());
    final static int SAMPLING_RATE = 44100;              // Audio sampling rate
    final static int SAMPLE_SIZE = 2;                    // Audio sample size in bytes	
    final static double noteSpeedRatio = 0.2;  // Usually set to 1 can be increased to increase Tempo
		
   public static void playSineWaveAtFrequencyForSeconds(double frequency, double seconds) throws InterruptedException, LineUnavailableException {
	      //Open up audio output, using 44100hz sampling rate, 16 bit samples, mono, 
	      //and big endian byte ordering
	      AudioFormat format = new AudioFormat(SAMPLING_RATE, 16, 1, true, true);
	      DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

	      if (!AudioSystem.isLineSupported(info)){
	         System.out.println("Line matching " + info + " is not supported.");
	         throw new LineUnavailableException();
	      }

	      SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
	      line.open(format);  
	      line.start();

	      writeSamplesLoop(frequency, line, seconds);

	      // Done playing the whole waveform, now wait until the queued samples finish 
	      //playing, then clean up and exit
	      line.drain();                                         
	      line.close();
	   }
	   
	public static void writeSamplesLoop(double frequency, SourceDataLine line, double seconds) throws InterruptedException {
		  ByteBuffer cBuf = ByteBuffer.allocate(line.getBufferSize());   
	      //Position through the sine wave as a percentage (i.e. 0 to 1 is 0 to 2*PI)
	      double fCyclePosition = 0;
		  int ctSamplesTotal = (int) (SAMPLING_RATE*seconds*noteSpeedRatio); 
	      while (ctSamplesTotal>0) {
		         double fCycleInc = frequency/SAMPLING_RATE;    // Fraction of cycle between samples

		         cBuf.clear();                              // Discard the samples from the last pass

		         // Figure out how many samples we can add
		         int ctSamplesThisPass = line.available()/SAMPLE_SIZE;   
		         for (int i=0; i<ctSamplesThisPass; i++) {
		            cBuf.putShort((short)(Short.MAX_VALUE * Math.sin(2*Math.PI * fCyclePosition)));

		            fCyclePosition += fCycleInc;
		            if (fCyclePosition > 1)
		               fCyclePosition -= 1;
		         }

		         //Write sine samples to the line buffer.  If the audio buffer is full, this will 
		         // block until there is room (we never write more samples than buffer will hold)
		         line.write(cBuf.array(), 0, cBuf.position());            
		         ctSamplesTotal -= ctSamplesThisPass;     // Update total number of samples written 

		         //Wait until the buffer is at least half empty  before we add more
		         while (line.getBufferSize()/2 < line.available())   
		            Thread.sleep(1);                                             
		      }		
	}
	
	
	public static void playNote(double frequency, double seconds) {
		try {
			playSineWaveAtFrequencyForSeconds(frequency, seconds);
		} catch (InterruptedException e) {
			
		} catch (LineUnavailableException e) {

		}
	}
	
	private static HashMap<String, Integer> frequencyMap;
	public static HashMap<String, Integer> getFrequencyMap() {
		if (null!=frequencyMap) {
			return frequencyMap;
		}
		
		frequencyMap = new HashMap<>();
		frequencyMap.put("middleC", 260);
		frequencyMap.put("D", 290);
		frequencyMap.put("E", 320);
		frequencyMap.put("F", 345);
		frequencyMap.put("G", 392);
		frequencyMap.put("Ab", 415);
		frequencyMap.put("A", 445);
		frequencyMap.put("Bb", 467);
		frequencyMap.put("B", 490);
		frequencyMap.put("C", 520);
		frequencyMap.put("highDb", 540);
		
		return frequencyMap;
	}
	
	public static void playMiddleC(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("middleC"), seconds);
	}
	
	public static void playD(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("D"), seconds);
	}	
	
	public static void playE(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("E"), seconds);
	}
	
	public static void playF(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("F"), seconds);
	}		
	
	public static void playG(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("G"), seconds);
	}
	public static void playAb(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("Ab"), seconds);
	}
	
	public static void playA(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("A"), seconds);
	}
	
	public static void playBb(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("Bb"), seconds);
	}		
	
	public static void playB(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("B"), seconds);
	}		
	
	public static void playC(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("C"), seconds);
	}	
	
	public static void playHighDb(double seconds) {
		playNote(AudioUtil.getFrequencyMap().get("highDb"), seconds);
	}		
	
	public static void playFarandolePhrase1() {
		// Farandole
		AudioUtil.playF(1);
		AudioUtil.playMiddleC(1);
		AudioUtil.playF(1);
		AudioUtil.playG(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playG(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playF(0.5);
		AudioUtil.playC(1);
		AudioUtil.playAb(0.5);
		AudioUtil.playBb(0.5);
		AudioUtil.playC(0.5);
		
		AudioUtil.playHighDb(0.5);
		AudioUtil.playC(0.5);
		AudioUtil.playBb(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playG(0.5);
		AudioUtil.playC(0.5);
		
		AudioUtil.playC(0.5);
		AudioUtil.playBb(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playG(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playF(0.5);
	}
	
	public static void playFarandolePhrase2() {
		// Farandole
		AudioUtil.playF(1);
		AudioUtil.playMiddleC(1);
		AudioUtil.playF(1);
		AudioUtil.playG(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playG(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playF(0.5);
		AudioUtil.playC(1);
		AudioUtil.playAb(0.5);
		AudioUtil.playBb(0.5);
		AudioUtil.playC(0.5);
		
		AudioUtil.playHighDb(0.5);
		AudioUtil.playC(0.5);
		AudioUtil.playBb(0.5);
		AudioUtil.playAb(0.5);
		AudioUtil.playAb(1);
		AudioUtil.playG(1);
		AudioUtil.playF(1);
	}	
	
	public static void main(String[] args) {
		logger.info("Testing Audio System in Java");		
		playMiddleC(0.2);
		playD(0.2);
		playE(0.2);
		playF(0.2);
		playG(0.2);
		playA(0.2);
		playB(0.2);
		playC(0.2);
		playMiddleC(0.2); // Octave Contrast
	}
}
