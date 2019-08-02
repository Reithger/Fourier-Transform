/**
 * Mac Clevinger
 * Digital Electronics and Signal Processing
 * Assignment 8
 * Due: March 29, 2019
 * 
 * Complex is a class that represents Complex numbers as a single object; it is composed of two double
 * values, one for the real component and the other for the imaginary component. It provides an easy to
 * use interface for adding and multiplying Complex numbers, as well as dividing by a scalar value.
 * 
 * @author Mac Clevinger
 */

class Complex{
	
//---  Instance Variables   -------------------------------------------------------------------
	
	/** double value representing the real component of this Complex number*/
	double real;
	/** double value representing the imaginary component of this Complex number*/
	double complex;
	
//---  Constructors   -------------------------------------------------------------------------
	
	/**
	 * Constructor for objects of the Complex type that assigns the provided Real and
	 * Imaginary value to the class instance variables.
	 * 
	 * @param inR - double value representing the real component of this Complex number
	 * @param inC - double value representing the imaginary component of this Complex number
	 */
	
	public Complex(double inR, double inC) {
		real = inR;
		complex = inC;
	}
	
//---  Operations   ---------------------------------------------------------------------------
	
	/**
	 * This method adds a provided Complex object to this Complex object by summing
	 * its Real and Imaginary values to this object's Real and Imaginary values.
	 * 
	 * @param b - Complex object representing another Complex value to add to this Complex object
	 */
	
	public void add(Complex b) {
		real += b.real;
		complex += b.complex;
	}
	
	/**
	 * This method adds the provided Real and Imaginary values to this Complex object's
	 * stored Real and Imaginary values.
	 * 
	 * @param realIn - double value representing the provided Real value to sum to this Complex object's
	 * @param complexIn - double value representing the provided Imaginary value to sum to this Complex object's
	 */
	
	public void add(double realIn, double complexIn) {
		real += realIn;
		complex += complexIn;
	}
	
	/**
	 * This method multiplies the provided Complex value to this Complex object by summing
	 * the product of the two's real values to the negative product of the two's imaginary
	 * values for the Real value, and summing the product of the two's real and complex
	 * values (realA * complexB + realB * complexA) for the Imaginary value.
	 * 
	 * @param b - Complex object representing the value being multipled to this Complex object.
	 */
	
	public void multiply(Complex b) {
		double nReal = (real * b.real - complex * b.complex);
		complex = (real * b.complex + complex * b.real);
		real = nReal;
	}

	/**
	 * This method performs a scalar divide to this Complex value: for whatever double
	 * value is provided, it divides the Real and Imaginary values stored by this Complex
	 * object by that value.
	 * 
	 * @param scalar - double value that this Complex object is divided by.
	 */
	
	public void scalarDivide(double scalar) {
		real /= scalar;
		complex /= scalar;
	}
	
}