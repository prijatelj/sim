package simplenet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Neuron parent class for use in SimpleNet implementations. 
 * @author Tim
 *
 * @param <T> type parameter determining the internal representation of state
 */

public abstract class Node<T> {

	//Priority Queue facilitates intelligent forward propagation with fast completion
	ArrayList<Node<T>> nextLayer = new ArrayList<>();
	PriorityQueue<WNode<T>> prevLayer = new PriorityQueue<>();

	//Internal state field
	T state;
	
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
	
	/**
	 * Adds a single node to the group of forward connections. 
	 * @param fNode the node to be added
	 */
	public void fConnect(Node<T> fNode){
		nextLayer.add(fNode);
	}
	
	/**
	 * Adds a single node and weight to the heap of backward connections.
	 * @param bNode the node to be added
	 */
	public void bConnect(Node<T> bNode, Double weight){
		prevLayer.add(new WNode<T>(bNode,weight));
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
		for(Node<T> node: bLayer.keySet())
			prevLayer.add(new WNode<T>(node,bLayer.get(node)));
	}
	
}
