/**
 * Mac Clevinger
 * Digital Electronics and Signal Processing
 * Assignment 8
 * Due: March 29, 2019
 * 
 * FourierTransform is a class that is provided a starting set of Complex numbers to perform a Fourier
 * Transformation on; this starting set can be provided in numerous formats, some of which default the
 * imaginary values all to 0. It can also take a Complex array to print to the terminal in different
 * styles.
 * 
 * 
 * @author Mac Clevinger
 *
 */

class FourierTransform{
	
//---  Constant Values   ----------------------------------------------------------------------
	
	/** Constant int value denoting how many decimal places to retain when printing Complex values to the terminal*/
	public final static int ROUNDING = 2;
	
//---  Instance Variables   -------------------------------------------------------------------
	
	/** Array of Complex objects representing the starting set of values that a Fourier Transform will be done on*/
	Complex[] in;
	
//---  Constructors   -------------------------------------------------------------------------
	
	/**
	 * Constructor for objects of the FourierTransform type that takes a single array of Complex values.
	 * 
	 * @param given - Array of Complex values representing the starting set of values
	 */
	
	public FourierTransform(Complex[] given) {
		in = given;
	}
	
	/**
	 * Constructor for objects of the FourierTransform type that takes a single array of double values
	 * and defaults the imaginary values to 0.
	 * 
	 * @param real - Array of double values representing the real components of a set of Complex numbers
	 */

	public FourierTransform(double[] real) {
		in = new Complex[real.length];
		for(int i = 0; i < real.length; i++) {
			in[i] = new Complex(real[i], 0);
		}
	}
	
	/**
	 * Constructor for objects of the FourierTransform type that takes a two-dimensional array of int values
	 * containing two channels of input that are averaged together when composed for a single array of Complex
	 * values. Imaginary values are assumed 0.
	 * 
	 * @param real - Two dimensional array of int values representing a set of Complex numbers' real components.
	 */
	
	public FourierTransform(int[][] real) {
		in = new Complex[real[0].length];
		for(int i = 0; i < real[0].length; i++) {
			in[i] = new Complex((real[0][i] + real[1][i]) / 2, 0);
		}
	}
	
	/**
	 * Constructor for objects of the FourierTransform type that takes two double arrays of
	 * double values representing the real and imaginary components of Complex numbers.
	 * 
	 * @param real - Array of double values representing the real components of Complex numbers
	 * @param complex - Array of double values representing the complex components of Complex numbers
	 */
	
	public FourierTransform(double[] real, double[] complex) {
		in = new Complex[real.length];
		for(int i = 0; i < real.length; i++) {
			in[i] = new Complex(real[i], complex[i]);
		}
	}
	
//---  Operations   ---------------------------------------------------------------------------
	
	/**
	 * This method performs a Fourier Transformation on the set of Complex values provided
	 * at the construction of this FourierTransformation object, generating a new set of
	 * Complex values that exist in the Frequency Domain as opposed to the original set
	 * that existed in the time domain.
	 * 
	 * @return - Returns a set of Complex objects representing the Fourier Transformation of the input set of Complex numbers
	 */
	
	public Complex[] generateFrequencyDomain() {
		int k = in.length % 2 == 0 ? in.length/2 + 1 : in.length/2 + 1;
		Complex[] out = new Complex[k];
		for(int i = 0; i < k; i++) {
			Complex sum = new Complex(0, 0);
			for(int j = 0; j < in.length; j++) {
				double theta = 2.0 * Math.PI * i * j / in.length;
				Complex temp = new Complex(Math.cos(theta), Math.sin(theta));
				temp.multiply(in[j]);
				sum.add(temp);
			}
			sum.scalarDivide(in.length);
			out[i] = sum;
		}
		return out;
	}

//---  Mechanics   ----------------------------------------------------------------------------
	
	/**
	 * This method prints the provided array of Complex values to the terminal, marking
	 * the start and end of the array. It also takes a size value to denote how many
	 * elements to print to the terminal, with the convention that a size of -1 should
	 * print all the values in the array.
	 * 
	 * Prints in the form: "('real_number', 'complex_number'i)"
	 * 
	 * @param given - Array of Complex values to be printed to the terminal
	 * @param size - int value representing how many elements of the Complex array given to print to the terminal
	 */
	
	public void printComplexArray(Complex[] given, int size, boolean magnitude) {
		System.out.println("Start of length: " + given.length);
		for(int i = 0; i < given.length && (i < size || size == -1); i++) {
			if(magnitude) {
				double mag = round(Math.sqrt(Math.pow(given[i].real, 2) + Math.pow(given[i].complex, 2)), ROUNDING);
				if(mag > 20 && Math.abs(given[i].real) > 27)
					System.out.println(round(Math.abs(given[i].real), ROUNDING) + ", " + mag);
			}
			else
				System.out.println("(" + round(given[i].real, ROUNDING) + ", " + round(given[i].complex, ROUNDING) + "i)");
		}
		System.out.println("End of length: " + given.length);
	}
	
	/**
	 * This method takes a double value and rounds it by multiplying it by the defined
	 * magnitude, casting it to an int value, and dividing it by that same amount as a
	 * double value. A class constant exists for defining the number of digits to retain
	 * following the decimal point.
	 * 
	 * @param in - double value provided to be rounded
	 * @param length - int value representing how many digits past the decimal place to retain
	 * @return - Returns a double value representing the rounded version of the provided double value
	 */
	
	public static double round(double in, int length) {
		int temp = (int)((in * Math.pow(10,  length)));
		return (double)temp / Math.pow(10,  length);
	}
	
}
