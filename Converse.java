package sim;
/*
 * Input:	All helper methods in converse class
 * 		Handles the user input from the command line and serves as 
 * bridge between the user and the rest of sim.
 * 
 * Uniquely identify the string.
 */
import java.util.Scanner;

public class Converse {
	static final int minLength = 6 ;
	protected static void dialogue(){
		Scanner input = new Scanner(System.in);
		String query = new String();
		System.out.println("Hello there, I am Sim. Please submit your query about AI.");
		query = (input.nextLine()).toLowerCase();
		while (!farewell(query, input)){	//	loops until stated to quit by user
			// process request
			if (query.equals("--help") || query.equals("-help") || query.equals("help")){
				help();
			}
			else if(query.length() < minLength){	//	input is too short to be useful.
				System.out.println("\nYou look like you could use some help.");
				help();
			}
			else{	//	 response by machine.
				query = nlp(query);// nlp that please.
				String response = "*response*";
				// respond
				System.out.println( "processed query: " + query + " | "  + response);
			}
			System.out.println("Another query?");
			query = (input.nextLine()).toLowerCase();
		}
	}
	protected static boolean farewell(String query, Scanner input){
		if (query.contains("goodbye") || query.contains("farewell") || query.contains("see you later")
				|| query.contains("see ya") || query.equals("cya") || query.equals("quit") ||
				query.contains("exit") || query.contains("no")){
			System.out.println("Leaving so soon? [y/n]");
			query = (input.nextLine()).toLowerCase();
			if(query.equals("y") || query.contains("yes") || query.contains("quit") || query.contains("exit")){
				System.out.println("Goodbye user!");
				return true;
			}
			else{
				System.out.println("Then we're going to need some more caffeine!");
				return false;
				}
		}
		else
			return false;
	}
	private static void help(){	//	return the help 'menu'
		System.out.println();
		for (int i = 0; i < 30; i++)
			System.out.print("*");
		System.out.println("\nHelp:");
		System.out.println(" I am Sim. A simulation of a professor's assistant of Artificial"
				+ " Inelligence. The domain of my knowledge is limited to only the content taught in"
				+ " the COSC 410 class offered at Duquesne Univesity. Ask me any question about the"
				+ " introductory content in the AI class and I will do my best to answer you"
				+ " approriately.\n"
				+ " Your questions should be of the type 'what' or 'how'. You may ask for examples"
				+ " as well."
				+ "\n To leave our discussion simply say a simple farewell or if you wish to be"
				+ " formal, then enter \"exit\" or \"quit\".");
		for (int i = 0; i < 30; i++)
			System.out.print("*");
		System.out.println();
	}
	
	/*
	 * nlp: This is our natural language handler. It cuts out useless words such
	 * as articles. It also handles certain words turning them into the three
	 * mainc categories of what, how, and example.
	 * 
	 * It's dirty, its disgusting, but it serves our purpose for deadline. This
	 * will be cleaned and optimized later.
	 */
	protected static String nlp(String query) {
		// remove all punctuation and remove all excess spaces
		query = query.replaceAll("\\s{2,}", " ");
		query = query.replaceAll("[.!?:;,'><]+", "");
		// articles, demonstratives, quantifiers\
		query = query.replaceAll("\\b(the)+\\b", "");
		query = query.replaceAll("\\b( a )\\b", " ");
		query = query.replaceAll("\\b(an)\\b", "");
		query = query.replaceAll("\\b(one)\\b", "");
		query = query.replaceAll("\\b(some)\\b", "");
		query = query.replaceAll("\\b(few)\\b", "");
		query = query.replaceAll("\\b(many)\\b", "");
		query = query.replaceAll("\\b(all)\\b", "");
		query = query.replaceAll("\\b(enough)\\b", "");
		query = query.replaceAll("\\b(plenty)\\b", "");
		query = query.replaceAll("\\b(every)\\b", "");
		query = query.replaceAll("\\b(neither)\\b", "");
		query = query.replaceAll("\\b(either)\\b", "");
		query = query.replaceAll("\\b(each)\\b", "");
		query = query.replaceAll("\\b(such)\\b", "");
		query = query.replaceAll("\\b(rather)\\b", "");
		query = query.replaceAll("\\b(quite)\\b", "");
		query = query.replaceAll("\\b(other)\\b", "");
		query = query.replaceAll("\\b(another)\\b", "");
		// pronouns (keep you and your because then it means "tell me about
		// sim")
		query = query.replaceAll("\\b(he)\\b", "");
		query = query.replaceAll("\\b(him)\\b", "");
		query = query.replaceAll("\\b(she)\\b", "");
		query = query.replaceAll("\\b(her)\\b", "");
		query = query.replaceAll("\\b(his)\\b", "");
		query = query.replaceAll("\\b(hers)\\b", "");
		query = query.replaceAll("\\b(it)\\b", "");
		query = query.replaceAll("\\b(its)\\b", "");
		query = query.replaceAll("\\b(ours)\\b", "");
		query = query.replaceAll("\\b(our)\\b", "");
		query = query.replaceAll("\\b(we)\\b", "");
		query = query.replaceAll("\\b(their)\\b", "");
		query = query.replaceAll("\\b(they)\\b", "");
		query = query.replaceAll("\\b(me)\\b", "");
		query = query.replaceAll("\\b(my)\\b", "");
		query = query.replaceAll("\\b(i)\\b", "");
		// handle special cases first:
		query = query.replaceAll("\\b(can you)\\b", "");
		// change you, your to sim:
		query = query.replaceAll("\\b(you)\\b", "sim");
		query = query.replaceAll("\\b(your)\\b", "sim");
		query = query.replaceAll("\\b(yours)\\b", "sim");
		// cutting off excess useless
		query = query.replaceAll("\\b(about)\\b", "");
		query = query.replaceAll("\\b(is)\\b", "");
		query = query.replaceAll("\\b(are)\\b", "");
		query = query.replaceAll("\\b(of)\\b", "");
		query = query.replaceAll("\\b(in)\\b", "");
		query = query.replaceAll("\\b(with)\\b", "");
		query = query.replaceAll("\\b(can)\\b", "");
		query = query.replaceAll("\\b(will)\\b", "");
		query = query.replaceAll("\\b(does)\\b", "");
		query = query.replaceAll("\\b(this)\\b", "");
		query = query.replaceAll("\\b(that)\\b", "");
		query = query.replaceAll("\\b(has)\\b", "");
		query = query.replaceAll("\\b(have)\\b", "");
		query = query.replaceAll("\\b(on)\\b", "");
		query = query.replaceAll("\\b(there)\\b", "");
		query = query.replaceAll("\\b(to)\\b", "");

		// Handle the main key word alternatives and turn them into main key
		// word:
		// "provide" to "example", "tell" to "what" if appropriate.
		query = query.replaceAll("\\b(prove)\\b", "example"); // all proofs
																// will be
																// stored in
																// example
		query = query.replaceAll("\\b(proof)\\b", "example");

		// give, show, etc... need handled correctly
		if (!query.contains("example")) {
			if (query.contains("how")) {
				query = query.replaceAll("\\b(show)\\b", "");
				query = query.replaceAll("\\b(give)\\b", "");
				query = query.replaceAll("\\b(deliever)\\b", "");
				query = query.replaceAll("\\b(present)\\b", "");
				query = query.replaceAll("\\b(produce)\\b", "");
				query = query.replaceAll("\\b(explanation)\\b", "");
			} else {
				query = query.replaceAll("\\b(show)\\b", "what");
				query = query.replaceAll("\\b(give)\\b", "what");
				query = query.replaceAll("\\b(deliever)\\b", "what");
				query = query.replaceAll("\\b(present)\\b", "what");
				query = query.replaceAll("\\b(produce)\\b", "what");
				query = query.replaceAll("\\b(explanation)\\b", "what");
			}
		} else {
			query = query.replaceAll("\\b(give)\\b", "");
			query = query.replaceAll("\\b(deliever)\\b", "");
			query = query.replaceAll("\\b(present)\\b", "");
			query = query.replaceAll("\\b(show)\\b", "");
			query = query.replaceAll("\\b(produce)\\b", "");
			query = query.replaceAll("\\b(explanation)\\b", "");
		}
		if (query.contains("what") || query.contains("how")) {
			query = query.replaceAll("\\b(tell)\\b", "");
			query = query.replaceAll("\\b(define)\\b", "");
			query = query.replaceAll("\\b(definition)\\b", "");
			query = query.replaceAll("\\b(state)\\b", "");
			query = query.replaceAll("\\b(provide)\\b", "");
			query = query.replaceAll("\\b(explanation)\\b", "");
			if (query.contains("example")) { // example has precedence over
												// what and how
				query = query.replaceAll("\\b(what)\\b", "");
				query = query.replaceAll("\\b(how)\\b", "");
			}
		} // this could be problematic because some tells could be hows.
			// tell about = what
		else {
			query = query.replaceAll("\\b(tell)\\b", "what");
			query = query.replaceAll("\\b(define)\\b", "what");
			query = query.replaceAll("\\b(state)\\b", "what");
			query = query.replaceAll("\\b(definition)\\b", "what");
			query = query.replaceAll("\\b(provide)\\b", "example");
			query = query.replaceAll("\\b(explanation)\\b", "example");
		}
		// if we are lacking any what, how, or example, then add simply
		// "what"
		if (!(query.contains("what") || query.contains("how") || query.contains("example"))) {
			query = "what ".concat(query);
		} else {
			// get rid of multiples after all the modification
			query = query.replaceAll("(?!^)\\b(what)\\b", "");
			query = query.replaceAll("(?!^)\\b(example)\\b", "");
			query = query.replaceAll("(?!^)\\b(how)\\b", "");
		}
		query = query.replaceAll("\\s{2,}", " ");
		return query;
	}
}
