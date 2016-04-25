package simplenet;

import java.util.HashMap;

public class Ramp extends ActivationFunction<Double>{
	
	double inflection;
	
	/**
	 * Ramp activation functions map all values below the inflection
	 * value to the inflection value and all values above the inflection
	 * value to themselves.
	 *  
	 * @param inflection the cutoff point for this Ramp instance
	 */
	public Ramp(double inflection){
		this.inflection = inflection;
	}
	
	public Ramp(){
		this(0);
	}

	@Override
	public Double getRate(HashMap<Node<Double>,Double> input, Double bias) {
		double level = bias;
		for(Node<Double> node: input.keySet()){
			level += input.get(node)*node.getState();
		}
		return Math.max(inflection,level);
	}

}
