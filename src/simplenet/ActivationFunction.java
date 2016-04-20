package simplenet;

import java.util.PriorityQueue;

public abstract class ActivationFunction<T> {

	public abstract T activate(PriorityQueue<WNode<T>> input);
	
}
