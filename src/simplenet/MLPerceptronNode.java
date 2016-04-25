package simplenet;

import java.util.ArrayList;
import java.util.HashMap;

public class MLPerceptronNode extends Node<Double> {

	public MLPerceptronNode(ActivationFunction<Double> act){
		nextLayer = new ArrayList<Node<Double>>();
		prevLayer = new ArrayList<Node<Double>>();
		weights = new HashMap<Node<Double>, Double>();
		setState(0.0);
		setBias(0.0);
		this.act = act;
	}
	
	public MLPerceptronNode(){
		this(new Tanh());
	}
	
}
