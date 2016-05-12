package dialogue;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;

import simplenet.*;

public class Test1 {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException{
		
		//Generate HashMaps for holding things. 
		HashMap<String, Integer> revmap = new HashMap<>();
		HashMap<Integer, String> map = new HashMap<>();
		HashMap<String, Integer> testmap = new HashMap<>();
		HashSet<String> aset = new HashSet<>();
		
		//Fill the answers HashMap with the appropriate answers for each processed string in the input file
		
		URL qpath = Test1.class.getResource("../../assets/questions.txt");
		URL apath = Test1.class.getResource("../../assets/answers.txt");
		
		Scanner qreader = new Scanner(new File(qpath.getFile()));
		Scanner areader = new Scanner(new File(apath.getFile()));
		
		int index = 0;
		
		while(qreader.hasNextLine() && areader.hasNextLine()){
			String Q = Converse.nlp(qreader.nextLine().toLowerCase());
			String A = areader.nextLine();
			
			if(!aset.contains(A)){
				aset.add(A);
				map.put(index,A);
				revmap.put(A, index);
				index++;
			}
			
			testmap.put(Q, revmap.get(A));
			
		}
		
		qreader.close();
		areader.close();
		
		//Train the neural net to map categories for each processed string
		
		int[] layers = {30,30,index};
		MLPerceptron net = new MLPerceptron(layers,.25);
		
		for(int i = 0; i < 300; i++){
			for(String q : testmap.keySet()){
				double[] target = new double[index];
				target[testmap.get(q)] = 1.0;
				net.train(QueryParser.parse(q, 60, 5), target);
			}
		}
		
		Converse.dialogue(net,map);
		
	}

}
