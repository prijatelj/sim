package simplenet;

import java.util.HashMap;

public abstract class ActivationFunction<T extends Comparable<T>> {

	public abstract T getRate(HashMap<Node<T>,Double> input, T bias);
	
}
