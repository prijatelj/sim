package simplenet;

import java.util.HashMap;

public class Sigmoid extends ActivationFunction<Double> {

	@Override
	public Double getRate(HashMap<Node<Double>,Double> input, Double bias) {
		double level = bias;
		for(Node<Double> node : input.keySet()){
			level += input.get(node)*node.getState();
		}
		return 1/(2+Math.expm1(level));
	}

}
