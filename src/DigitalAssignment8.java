import java.io.File;
import java.io.RandomAccessFile;

import org.jtransforms.fft.DoubleFFT_1D;

/**
 * Mac Clevinger
 * Digital Electronics and Signal Processing
 * Assignment 8
 * Due: March 29, 2019
 * 
 * Referenced library is the following: http://www.labbookpages.co.uk/audio/javaWavFiles.html
 * See included .jar file and include in your project to get this to run. (I packaged the source
 * files provided by a .tar.gz file from that website to include in the build path.)
 * 
 * In addition, a FourierTransform library was used for performing Question 3 in a reasonable time.
 * https://sites.google.com/site/piotrwendykier/software/jtransforms
 * The .jar will be attached as well, but no ownership is claimed over it (and it is only used for
 * question 3, and is in no way a substitute for the implementation of the Fourier Transformation
 * in question 1.)
 * 
 * Program was developed in Eclipse, where some issues of importing between multiple classes
 * was handled automatically; may have to define packages or import from other classes to
 * make work in other contexts.
 * 
 * Program is composed of 3 classes:
 *  - DigitalAssignment8 makes use of FourierTransform and Complex to calculate the Fourier Transformation
 *  of several test arrays of double values, and then read in a .wav file to compute its frequencies.
 *  - FourierTransform is a class that is provided a starting set of Complex numbers to perform a Fourier
 *  Transformation on; this starting set can be provided in numerous formats, some of which default the
 *  imaginary values all to 0. It can also take a Complex array to print to the terminal in different
 *  styles.
 *  - Complex is a class that represents Complex numbers as a single object; it is composed of two double
 *  values, one for the real component and the other for the imaginary component. It provides an easy to
 *  use interface for adding and multiplying Complex numbers, as well as dividing by a scalar value.
 *
 * @author Mac Clevinger
 */

public class DigitalAssignment8 {
	
	/**
	 * For general purposes, just run this and you'll be good to go for producing the results
	 * from questions 1 and 2, as well as producing data for question 3. To use the FourierTransform
	 * in more generic terms, this serves as an example of usage.
	 * 
	 */

	public static void main(String[] args) throws Exception{
		double[] in = new double[] {1, 0, 0, 0};
		double[] in2 = new double[] {1,0,0,0,1,0,0,0};
		double[] in3 = new double[] {0,1,2,0};
		double[][] autom = new double[][] {in, in2, in3};
		for(int i = 0; i < autom.length; i++) {
			FourierTransform fT = new FourierTransform(autom[i]);
			Complex[] out = fT.generateFrequencyDomain();
			fT.printComplexArray(out, -1, false);
		}
		
		
		String filePath = "C:/Users/Reithger/Music/chord-piano.wav";
		WavFile wav = WavFile.openWavFile(new File(filePath));
		wav.display();
		int skip = (int)wav.getNumFrames()/50;
		int numFrames = (int)wav.getNumFrames()*40/50;
		int[][] frames = new int[wav.getNumChannels()][numFrames];
		wav.readFrames(frames, skip);
		frames = new int[wav.getNumChannels()][numFrames];
		wav.readFrames(frames, numFrames);
		
		double[] a = new double[numFrames];
		for(int i = 0; i < a.length; i++) {
			a[i] = (frames[0][i] + frames[1][i]) / 2.0;
		}
		
		DoubleFFT_1D obj = new DoubleFFT_1D(a.length);
		obj.realForward(a);
		
		File f = new File("C:\\Users\\Reithger\\Documents\\School\\Digital Electronics\\output10.txt");
		f.delete();
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		
		for(int i = 0; i < a.length; i += 2) {
			double mag = FourierTransform.round(Math.sqrt(Math.pow(a[i], 2) + Math.pow(a[i+1], 2)), FourierTransform.ROUNDING);
			double freq = FourierTransform.round((i/2) * wav.getSampleRate() / numFrames, FourierTransform.ROUNDING);
			if(freq > 20 && freq < 2500)
				raf.writeBytes(freq + ", " + mag + "\n");
		}
		System.out.println("Done");
		
		//The below was the original usage of in-house Fourier Transforms, replaced with a Fast FT for question 3
		/*
		FourierTransform fT = new FourierTransform(frames);
		Complex[] out = fT.generateFrequencyDomain();
		
		fT.printComplexArray(out, -1, true);
		*/
		wav.close();
		raf.close();
	}
	
}
