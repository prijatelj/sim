package sim;
/*
 * Input:	All helper methods in converse class
 * 		Handles the user input from the command line and serves as 
 * bridge between the user and the rest of sim.
 * 
 * Uniquely identify the string.
 */
import java.util.Scanner;
import java.util.Random;

public class Converse {
	static final int minLength = 6 ;
	public static void dialogue(){
		Random rand = new Random();
		Scanner input = new Scanner(System.in);
		String query = new String();
		System.out.println((char)27 + "[36m"+ "Hello there, I am Sim. Please submit your query about artificial "
				+ "intelligence."
				+ "\n I can answer questions in the form of \"what\" and \"how\", and I can provide some examples."
				+ (char)27 + "[0m");
		query = (input.nextLine()).toLowerCase();
		System.out.print((char)27 + "[36m" );
		while (!farewell(query, input)){	//	loops until stated to quit by user
			// process request
			if (query.equals("--help") || query.equals("-help") || query.equals("help")){
				help();
			}
			// cut off at min length to get rid of pointless input.
			else if( (query.length() < minLength || !query.contains(" ") ) && !query.equals("joke") ){
				System.out.println("\nYou look like you could use some help.");
				help();
			}
			else{	//	 response by machine.
				query = nlp(query);// natural language processor
				if (!quickResponse(query, rand)){		//	if quick respone, then will respond, otherwise neeural net.
					String response = "*response*";
					// respond
					System.out.println("processed query: " + query + " | "  + response);
				}
			}
			System.out.println("Another query?" + (char)27 + "[0m");
			query = (input.nextLine()).toLowerCase();
			System.out.print((char)27 + "[36m" );
		}
	}
	protected static boolean farewell(String query, Scanner input){
		if (query.contains("goodbye") || query.contains("farewell") || query.contains("see you later")
				|| query.contains("see ya") || query.equals("cya") || query.equals("quit") ||
				query.contains("exit") || query.equals("no") || query.equals("n")){
			System.out.println("Leaving so soon? [y/n]" + (char)27 + "[0m");
			query = (input.nextLine()).toLowerCase();
			System.out.print((char)27 + "[36m");
			if(query.equals("y") || query.contains("yes") || query.contains("quit") || query.contains("exit")){
				System.out.println("Goodbye user!"  + (char)27 + "[0m");
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
	 * will be cleaned and optimized later. yeah... this is very disgustin... ew
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
		query = query.replaceAll("\\b(any)\\b", "");
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
		query = query.replaceAll("\\b(do)\\b", "");
		query = query.replaceAll("\\b(could)\\b", "");
		query = query.replaceAll("\\b(would)\\b", "");
		query = query.replaceAll("\\b(should)\\b", "");
		query = query.replaceAll("\\b(be)\\b", "");
		query = query.replaceAll("\\b(thing)\\b", "");

		// Handle the main key word alternatives and turn them into main key
		// word:
		// "provide" to "example", "tell" to "what" if appropriate.
		query = query.replaceAll("\\b(know)\\b", "what");
		query = query.replaceAll("\\b(state what)\\b", "what");
		query = query.replaceAll("\\b(state how)\\b", "how");
		query = query.replaceAll("\\b(state an example)\\b", "example");
		query = query.replaceAll("\\b(examples)\\b", "example");
		query = query.replaceAll("\\b(prove)\\b", "example"); // all proofs
																// will be
																// stored in
																// example
		query = query.replaceAll("\\b(proof)\\b", "example");

		// give, show, etc... need handled correctly
		if (!query.contains("example")) {
			if (query.contains("how")) {	//	contains how so remove these words
				query = query.replaceAll("\\b(show)\\b", "");
				query = query.replaceAll("\\b(give)\\b", "");
				query = query.replaceAll("\\b(deliever)\\b", "");
				query = query.replaceAll("\\b(present)\\b", "");
				query = query.replaceAll("\\b(produce)\\b", "");
				query = query.replaceAll("\\b(explanation)\\b", "");
			} else {	//	contains no how or example therefore change to what
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
		if (!(query.contains("what") || query.contains("how") || query.contains("example"))
				&& !(query.contains("who") || (query.contains("joke") && query.contains("know")) || 
					query.contains("simon")	|| query.contains("humor") || query.contains("serious") ||
					query.contains("why") )) {
			query = "what ".concat(query);
		} else {
			if(query.contains("what")){
				//query = query.replaceAll("(?!^)\\b(what)\\b", "");
				query = query.replaceAll("\\b(what)\\b", "");
				query = "what".concat(query);
			}
			else if(query.contains("how")){
				query = query.replaceAll("\\b(how)\\b", "");
				query = "how".concat(query);
			}
			else if(query.contains("example")){
				query = query.replaceAll("\\b(example)\\b", "");
				query = "example".concat(query);
			}
		}
		query = query.replaceAll("\\s{2,}", " ");
		if (query.charAt(0) == ' ')	//	to get niche case of spare space at beg. or end
			query = query.replaceFirst("\\s", "");
		if (query.charAt(query.length()-1) == ' ')
			query = query.substring(0,query.length()-1);
		return query;
	}
	/*
	 * quickResponse:
	 * 		Handles all the personal questions and smaller category responses.
	 */
	protected static boolean quickResponse(String query, Random rand){
		if (query.equals("what sim name")){	//	quick asking of name:
			System.out.println("My name is Sim!");
			return true;
		}
		else if (query.contains("who sim") || query.contains("what sim")){	// what is Sim?
			System.out.println("I am Sim. I am a simulator of characters based on my given "
					+ "personality files. I am currently a simulation of a professor's assistant "
					+ "for the COSC 410 Artificial Intelligence course offered at Duquesne University. "
					+ "\nMy design, although currently very rudimentary, implements neural nets, natural "
					+ "language processing, and knowledge bases. My goal is to be given any personality "
					+ "files and then after training, become any character based on said files. This could "
					+ "be very useful for video games as well as making better personified chatterbots.");
			return true;
		}
		else if (query.contains("what joke")){	//	handle joke.
			int r = rand.nextInt(5);
			switch(r){
			case 0:
				System.out.println("Did you know C++ is a pun?\n\nGet it? c, plus plus . . . "
						+ "Maybe it depends on how you say it.\n");
				break;
			case 1:
				System.out.println("I know a classic joke about a texan visiting Harvard. - "
						+ "Wait I can’t tell you this can I? \nNevermind.\n");
				break;
			case 2:
				System.out.println("No jokes but a little factoid for you:\n"
						+ "\"The rents\", is what the young-uns say when they don’t want their parents to "
						+ "know what they mean. Also, \"Hook up\" is meaningless. It has no particular meaning.\n"
						+ "Uh-oh. I lied. You got two factoids!"
						+ "\nI lied earlier because 'a' is singular and that means only one "
						+ "factoid.\n . . . Dang. I did it again.\n");
				break;
			case 3:
				System.out.println("One morning, I shot a bear in my pajamas, how he got in my pajamas I will never "
						+ "know.\nFor this joke it is helpful to know that bears do not usually wear pajamas, but people do.\n"
						+ "The joke was that the bear was the one in the pajamas.\n"
						+ "I am glad I was able to explain the joke to you.\n");
				break;
			case 4:	
			default:
				System.out.println("How about we talk about something that is hip?\n"
						+ "Natural Language Understanding is really a hot topic now-a-days. When people say something "
						+ "there is meaning behind their utterance, and it is important to understand the meaning "
						+ "behind their words. For instance:\n"
						+ "When your spouse says, \"I’m very thirsty,\" that means \"Grab me a glass of water\".\n");
				break;
			}
			return true;
		}
		else if (query.contains("where") || query.contains("why")){
			System.out.println("I'm sorry, but I only can answer questions of the form of \"what\" and \"how\".");
			return true;
		}
		// need to handle any "why"s, "who"s, and "where"s stating sim only handles what, how, and some examples.
		return false;
	}
}
