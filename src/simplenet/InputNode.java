package simplenet;

import java.util.Map;

public class InputNode<T extends Comparable<T>> extends Node<T> {

	public InputNode(){
		act = null;
		prevLayer = null;
	}
	
	/**
	 * Empty method; input nodes cannot be back-connected
	 */
	@Override
	public void bConnect(Node<T> node, Double weight){}
	
	/**
	 * Empty method; input nodes cannot be back-connected
	 */
	@Override
	public void bConnectAll(Map<Node<T>,Double> bLayer){}
	
}
