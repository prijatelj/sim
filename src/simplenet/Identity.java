package simplenet;

import java.util.PriorityQueue;

public class Identity extends ActivationFunction<Number> {

	@Override
	public Number activate(PriorityQueue<WNode<Number>> input) {
		double level = 0;
		for(WNode<Number> node:input){
			level += (double)node.getNode().getState()*node.getWeight();
		}
		return level;
	}

}
