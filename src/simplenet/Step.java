package simplenet;

import java.util.HashMap;

public class Step extends ActivationFunction<Double> {
	
	private double threshold;
	
	public Step(double threshold){
		this.threshold = threshold;
	}
	
	public Step(){
		this(0);
	}
	
	public double getThreshold(){
		return threshold;
	}
	
	public void setThreshold(double threshold){
		this.threshold = threshold;
	}

	@Override
	public Double getRate(HashMap<Node<Double>,Double> input, Double bias) {
		double level = bias;
		for(Node<Double> node : input.keySet()){
			level += input.get(node)*node.getState();
		}
		return (level >= threshold)?1.0:-1.0;
	}

}
