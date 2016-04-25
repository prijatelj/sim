package sim;

import java.util.HashMap;
import simplenet.*;

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		
		//Generate a neural net and HashMap of answers
		int[] layers = {60,60,60};
		MLPerceptron net = new MLPerceptron(layers);
		HashMap<String, String> answers = new HashMap<>();
		HashMap<Integer,String> map = new HashMap<>();
		
		//Fill the answers HashMap with the appropriate answers for each processed string in the input file
		
		
		//Train the neural net to map categories for each processed string
		
		
		Converse.dialogue(net,map);
		
	}

}
