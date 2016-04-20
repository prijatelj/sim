package simplenet;

/**
 * Helper class to provide a lightweight comparable interface over nodes. 
 * @author Tim
 *
 * @param <T> type parameter of the node contained in this weighted node
 */

class WNode<T> implements Comparable<WNode<T>> {

	private Node<T> node;
	private Double weight;

	public WNode(Node<T> node, double weight){
		this.node = node;
		this.weight = weight;
	}
	
	public Node<T> getNode(){
		return node;
	}
	
	public Double getWeight(){
		return weight;
	}
	
	public void setWeight(double weight){
		this.weight = weight;
	}
	
	@Override
	public int compareTo(WNode<T> node) {
		return weight.compareTo(node.getWeight());
	}

}
