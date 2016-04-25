package simplenet;

import java.util.ArrayList;
import java.util.HashMap;

public class MLPerceptronNode extends Node<Double> {

	private double delta = 0.0;
	
	public MLPerceptronNode(ActivationFunction<Double> act){
		nextLayer = new ArrayList<Node<Double>>();
		prevLayer = new ArrayList<Node<Double>>();
		weights = new HashMap<Node<Double>, Double>();
		setState(0.0);
		setBias(0.0);
		this.act = act;
	}
	
	public MLPerceptronNode(){
		this(new Sigmoid());
	}
	
	public void setDelta(double delta){
		this.delta = delta;
	}
	
	public double getDelta(){
		return delta;
	}
	
}
