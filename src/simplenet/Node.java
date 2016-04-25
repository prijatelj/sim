package simplenet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Neuron parent class for use in SimpleNet implementations. 
 * @author Tim
 *
 * @param <T> type parameter determining the internal representation of state
 */

public abstract class Node<T extends Comparable<T>> {

	//Subclasses may end up choosing optimized layer implementations
	Collection<Node<T>> nextLayer;
	Collection<Node<T>> prevLayer;

	//Map of weights, always a double for now. 
	HashMap<Node<T>,Double> weights;
	
	//Internal State field
	private T state;
	private T bias;
	
	//Activation Function field
	ActivationFunction<T> act;
	
	/**
	 * Directly sets the neuron's internal state
	 * @param state new value for the state field
	 */
	public void setState(T state){
		this.state = state;
	}
	
	/**
	 * Returns the state of the neuron
	 * @return state variable of type <T>
	 */
	public T getState(){
		return state;
	}
	
	public void setBias(T bias){
		this.bias = bias;
	}
	
	public T getBias(){
		return bias;
	}
	
	public void setActivation(ActivationFunction<T> act){
		this.act = act;
	}
	
	/**
	 * Adds a single node to the collection of forward connections. 
	 * @param fNode the node to be added
	 */
	public void fConnect(Node<T> fNode){
		nextLayer.add(fNode);
	}
	
	/**
	 * Adds a single node and weight to the collection of backward connections.
	 * If the passed node is already contained in the collection of backward 
	 * connections, modifies the weight of that node instead.
	 * @param bNode the node to be added
	 * @param weight the weight to be applied to bNode
	 */
	public void bConnect(Node<T> bNode, Double weight){
		if(!weights.containsKey(bNode))
			prevLayer.add(bNode);
		weights.put(bNode,weight);
	}

	/**
	 * Adds a collection of nodes to the group of forward connections.
	 * @param fLayer the nodes to be added
	 */
	public void fConnectAll(Collection<Node<T>> fLayer){
		nextLayer.addAll(fLayer);
	}
	
	/**
	 * Adds a map of nodes and weights to the heap of backward connections.
	 * @param bLayer the map to be added
	 */
	public void bConnectAll(Map<Node<T>,Double> bLayer){
		for(Node<T> node: bLayer.keySet()){
			prevLayer.add(node);
			weights.put(node,bLayer.get(node));
		}
	}
	
	/**
	 * Changes the weight of a node if it is already contained in the collection
	 * of backward connections. Use {@link #bConnect} if a connection should be made in
	 * the case that the passed node is not already connected. Returns true if the 
	 * node is already contained, otherwise returns false;
	 * 
	 * @param bNode the node to be re-weighted
	 * @param weight the new weight of bNode
	 * @return true if the node was already contained in the set of backwards connections, else false
	 */
	public boolean setWeight(Node<T> bNode, Double weight){
		boolean val = weights.containsKey(bNode);
		if(val)
			weights.put(bNode,weight);
		return val;
	}
	
	/**
	 * Returns the weight of the passed node, or null if that node is not in
	 * the previous layer as seen by this node.
	 * 
	 * @param bNode the node to fetch the weight of
	 * @return a double representing the weight of bNode or null if bNode
	 * is not in the previous layer for this node
	 */
	public Double getWeight(Node<T> bNode){
		return weights.get(bNode);
	}
	
	/**
	 * Updates the state of the node based on the current activation function
	 * 
	 * @return the value of state after application of the activation function
	 */
	public T update(){
		return state = act.getRate(weights,bias);
	}
	
}
