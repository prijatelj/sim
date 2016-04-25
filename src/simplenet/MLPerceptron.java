package simplenet;

import java.util.Random;

public class MLPerceptron extends SimpleNet<Double,MLPerceptronNode> {
	
	//Learning rate
	double lrate;
	
	//Activation Function Type Variable
	ActivationFunction<Double> act_type = null;

	/**
	 * Generalized Perceptron network constructor that can operate over
	 * any supplied activation function. Currently non-functional, as the 
	 * calculation of gradients must be implemented in a dynamic fashion first
	 * .
	 * @param layers sizes; first and last layer will be treated as input/output
	 * @param rate learning rate, default value is .01, valid values are 
	 * positive doubles 0 < rate < 1
	 * @param act activation function to be implemented for all nodes at startup
	 */
	public MLPerceptron(int[] layers, double rate, ActivationFunction<Double> act){
		initialize(layers);
		this.lrate = rate;
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
		this(layers, rate, new Sigmoid());
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
		this(layers,.01, new Sigmoid());
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
				network[0][i] = (act_type != null)?new MLPerceptronNode(act_type):new MLPerceptronNode();
			//Initialize hidden layers and output layer
			for (int i = 1; i < layers.length; i++) {
				network[i] = new MLPerceptronNode[layers[i]];
				// Variances normalized based on input size
				double norm = Math.sqrt(layers[i - 1]);
				for (int j = 0; j < network[i].length; j++) {
					network[i][j] = (act_type != null)?new MLPerceptronNode(act_type):new MLPerceptronNode();
					for (MLPerceptronNode v : network[i - 1]){
						network[i][j].bConnect(v, random.nextGaussian() / norm);
						v.fConnect(network[i][j]);
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
	 * Train the Perceptron on a single example with the backpropagation 
	 * algorithm. Does not check input or output vector lengths; assumes 
	 * zero for missing arguments and only checks the number of arguments 
	 * specified for the net.  
	 * 
	 * @param input the vector of data to be trained
	 * @param expected the expected output for the given input vector
	 * @return a vector of doubles representing the difference between 
	 * input data and expected outputs
	 */
	public double[] train(double[] input, double[] expected){
		double[] output = test(input);

		double[] diff = new double[network[network.length-1].length];
		
		for(int i = 0; i < network[network.length-1].length; i++){
			MLPerceptronNode p = network[network.length-1][i];
			diff[i] = expected[i] - output[i];
			p.setDelta(diff[i]*p.getState()*(1-p.getState()));
			for(Node<Double> x : p.prevLayer){
				p.setWeight(x,p.getWeight(x) + lrate*p.getDelta()*x.getState());
			}
		}
		
		for (int k = network.length - 2; k > 0; k--) {
			for (int i = 0; i < network[k].length; i++) {
				MLPerceptronNode p = network[k][i];
				double deltasum = 0;
				for(Node<Double> x : p.nextLayer){
					deltasum += x.getWeight(p)*((MLPerceptronNode) x).getDelta();
				}
				p.setDelta(deltasum*p.getState()*(1-p.getState()));
				for (Node<Double> x : p.prevLayer) {
					p.setWeight(x,p.getWeight(x) + lrate*p.getDelta()*x.getState());
				}
			}
		}
		return diff;
	}
	
	/**
	 * Tests the Perceptron network with a vector of data. Assumes missing data
	 * values to be zero. 
	 * 
	 * @param input The vector of data to be tested
	 * @return an array containing the states of the output layer
	 */
	public double[] test(double[] input){
		double[]output = new double[network[network.length - 1].length];
		for(int i = 0; i < network[0].length; i++)
			network[0][i].setState((i < input.length) ? input[i]:0);
		for(int i = 1; i < network.length - 1; i++){
			for(MLPerceptronNode p : network[i])
				p.update();
		}
		for(int i = 0; i < network[network.length - 1].length; i++)
			output[i] = network[network.length-1][i].update();
		return output;
	}
}
