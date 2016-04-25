package simplenet;


/**
 * Simple, versatile, fixed-size array-backed neural net implementation with
 * modular construction. 
 * @author Tim
 */

public class SimpleNet<T extends Comparable <T>,N extends Node<T>> {
	
	N[][] network; 

	/**
	 * Core constructor accepting an array of integers to define layer sizes, starting 
	 * with the first hidden layer and ending with the last hidden layer (before the
	 * terminal layer). 
	 */
	
	public SimpleNet(){
		
	}
	
	public SimpleNet(int[] layers){
		
	}
	
}
