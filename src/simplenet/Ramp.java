package simplenet;

import java.util.PriorityQueue;

public class Ramp extends ActivationFunction<Double>{

	@Override
	public Double activate(PriorityQueue<WNode<Double>> input) {
		double level = 0;
		for(WNode<Double> node: input){
			level += node.getWeight()*Math.max(0, node.getNode().getState());
		}
		return level;
	}

}
