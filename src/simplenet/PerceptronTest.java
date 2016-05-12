package simplenet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import dialogue.QueryParser;

public class PerceptronTest {

	public static void main(String[] args) throws FileNotFoundException{
		
		//Parse attributes and test data
		Scanner at = new Scanner(new File("bridges.txt"));
		Scanner rt = new Scanner(new File("failures.txt"));
		
		ArrayList<String> inputStrings = new ArrayList<>();
		ArrayList<String> outputStrings = new ArrayList<>();
		
		ArrayList<double[]> input = new ArrayList<>();
		ArrayList<double[]> expected = new ArrayList<>();
		
		double[] ok = {1.0,-1.0};
		double[] notok = {-1.0, 1.0};
		
		//Turn strings into proper integer representations
		while(at.hasNextLine()){
			if(!rt.hasNextLine()){
				System.err.println("Input File Length Mismatch!");
				System.exit(0);
			}
			//Generate training string and expected string
			String I = at.nextLine();
			inputStrings.add(I);
			input.add(QueryParser.parse(I, 60, 3));
			
			String O = rt.nextLine();
			outputStrings.add(O);
			expected.add((O == "Okay")?ok:notok);
		}
		
		at.close();
		rt.close();
		
		//Train Perceptron on input data
		
		Perceptron net = new Perceptron(60,2);
		Random random = new Random();
		
		//Training loop
		for(int i = 0; i < 50; i++){
			int seed = random.nextInt(input.size());
			net.train(input.get(seed), expected.get(seed));
		}
		
		int correct = 0;
		
		//Testing loop
		for(int i = 0; i < input.size(); i++){
			double[] cat = net.test(input.get(i));
			double[] exp = expected.get(i);
			if(cat[0] == exp[0] && cat[1] == exp[1]){
				System.out.println("Correct Categorization");
				correct ++;
			}else
				System.out.println("Incorrect Categorization");
		}
		
		System.out.println(correct);
		
	}

}
