package simplenet;

import java.util.ArrayList;
import java.util.HashMap;

public class PerceptronNode extends Node<Double> {

	public PerceptronNode(){
		nextLayer = new ArrayList<Node<Double>>();
		prevLayer = new ArrayList<Node<Double>>();
		weights = new HashMap<Node<Double>, Double>();
		setState(0.0);
		setBias(0.0);
		act = new Step();
	}
	
}
