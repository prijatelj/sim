package simplenet;

import java.util.Random;

public class MLPerceptron extends SimpleNet<Double,MLPerceptronNode> {
	
	//Learning rate
	double lrate;
	
	//Activation Function Type Variable
	ActivationFunction<Double> act_type = null;

	public MLPerceptron(int[] layers, double rate, ActivationFunction<Double> act){
		this(layers, rate);
		act_type = act;
	}
	
	/**
	 * Constructs a Perceptron network with variable layer structure
	 * based on initialization. Accepts a learning rate argument.
	 *  
	 * @param layers array of layer sizes; first and last layer will 
	 * be treated as input/output
	 * @param rate learning rate, default value is .01, valid values are 
	 * positive doubles 0 < rate < 1
	 */
 	public MLPerceptron(int[] layers, double rate){
		initialize(layers);
		this.lrate = rate;
	}
	
	/**
	 * Constructs a Perceptron network with variable layer structure
	 * based on initialization. The default learning rate of .01 is 
	 * implemented.
	 *  
	 * @param layers array of layer sizes; first and last layer will 
	 * be treated as input/output
	 */
	public MLPerceptron(int[] layers){
		this(layers,.01);
	}
	
	/**
	 * Initialize a Perceptron network  with default random weights
	 * @param layers array of network layer sizes
	 */
	private void initialize(int[] layers) {
		// Initialize Network Array
		network = new MLPerceptronNode[layers.length][];
		// Make a Random object for initializing weights
		Random random = new Random();
		// Attempt to construct proposed network
		try {
			//Initialize input layer
			network[0] = new MLPerceptronNode[layers[0]];
			for(int i = 0; i < network[0].length; i++)
				network[0][i] = (act_type == null)?new MLPerceptronNode(act_type):new MLPerceptronNode();
			//Initialize hidden layers and output layer
			for (int i = 1; i < layers.length; i++) {
				network[i] = new MLPerceptronNode[layers[i]];
				// Variances normalized based on input size
				double norm = Math.sqrt(layers[i - 1]);
				for (MLPerceptronNode p : network[i]) {
					p = (act_type == null)?new MLPerceptronNode(act_type):new MLPerceptronNode();
					for (MLPerceptronNode v : network[i - 1]){
						p.bConnect(v, random.nextGaussian() / norm);
						v.fConnect(p);
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error: Invalid Perceptron Initialization!");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Train the Perceptron on a single example, passed as an array.
	 * Does not check input or output vector lengths; assumes zero for
	 * missing arguments and only checks the number of arguments 
	 * specified for the net.
	 * 
	 * @param input
	 * @param output
	 */
	public void train(double[] input, double[] output){
		
	}
}
