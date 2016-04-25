package simplenet;

import java.util.Random;

public class Perceptron extends SimpleNet<Double,PerceptronNode> {
	
	//Learning rate
	double lrate;
	
	/**
	 * Constructs a single-layer Perceptron network. Accepts a 
	 * learning rate argument.
	 *  
	 * @param inputs size of the input vector
	 * @param outputs size of the output vector
	 * @param rate learning rate, default value is .01, valid values are 
	 * positive doubles 0 < rate < 1
	 */
	public Perceptron(int inputs, int outputs, double rate){
		initialize(inputs,outputs);
		this.lrate = rate;
	}
	
	/**
	 * Constructs a single-layer Perceptron network. The default learning
	 * rate of .01 is implemented.
	 * 
	 * @param inputs size of the input vector
	 * @param outputs size of the output vector
	 */
 	public Perceptron(int inputs, int outputs){
		this(inputs, outputs, .01);
	}
	
	/**
	 * Initialize a Perceptron network  with default random weights
	 * @param layers array of network layer sizes
	 */
	private void initialize(int inputs, int outputs) {
		// Initialize Network Array
		network = new PerceptronNode[2][];
		// Make a Random object for initializing weights
		Random random = new Random();
		// Attempt to construct proposed network
		try {
			//Initialize input layer
			network[0] = new PerceptronNode[inputs];
			for(int i = 0; i < network[0].length; i++)
				network[0][i] = new PerceptronNode();
			//Initialize output layer
			network[1] = new PerceptronNode[outputs];
			for(int i = 0; i < network[1].length; i++) {
				// Variances normalized based on input size
				double norm = Math.sqrt(inputs);
				network[1][i] = new PerceptronNode();
				for (PerceptronNode v : network[0]) {
					network[1][i].bConnect(v, random.nextGaussian() / norm);
					v.fConnect(network[1][i]);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error: Invalid Perceptron Initialization!");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Train the Perceptron on a single example with the Perceptron 
	 * training algorithm. Does not check input or output vector 
	 * lengths; assumes zero for missing arguments and only checks 
	 * the number of arguments specified for the net.  
	 * 
	 * @param input the vector of data to be trained
	 * @param expected the expected output for the given input vector
	 * @return a vector of doubles representing the difference between 
	 * input data and expected outputs
	 */
	public double[] train(double[] input, double[] expected){
		double[] output = test(input);
		double[] diff = new double[network[1].length];
		for(int i = 0; i < network[1].length; i++){
			PerceptronNode p = network[1][i];
			diff[i] = expected[i] - output[i];
			for(Node<Double> x : p.prevLayer){
				p.setWeight(x, p.getWeight(x) + lrate*diff[i]*x.getState());
			}
		}
		return diff;
	}
	
	/**
	 * Tests the Perceptron with a vector of data. Assumes missing data
	 * values to be zero. 
	 * 
	 * @param input The vector of data to be tested
	 * @return an array containing the states of the output layer
	 */
	public double[] test(double[] input){
		double[]output = new double[network[1].length];
		for(int i = 0; i < network[0].length; i++)
			network[0][i].setState((i < input.length) ? input[i]:0);
		for(int i = 0; i < network[1].length; i++)
			output[i] = network[1][i].update();
		return output;
	}
}
