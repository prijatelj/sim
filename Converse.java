package sim;
/*
 * Input:
 * 		Handles the user input from the command line and serves as 
 * bridge between the user and the rest of sim.
 * 
 * Uniquely identify the string.
 * 
 * split them into anagrams,
 * 
 */
import java.util.Scanner;
import java.util.regex.Pattern;
public class Converse {
	
	void dialogue(){
		Scanner input = new Scanner(System.in);
		String query = new String();
		query = (input.nextLine()).toLowerCase();
		query = prep(query);
		while (!farewell(query, input)){
			// process request
			if (query.equals("-help")){
				System.out.println("");
			}
			else{
				String response = "";
				// respond
				System.out.println(response);
			}
			query = (input.nextLine()).toLowerCase();
			query = prep(query);
		}
		
	}
	boolean farewell(String query, Scanner input){
		if (query.equals("goodbye") || query.contains("farewell") || query.contains("see you later")
				|| query.contains("see ya") || query.equals("cya") || query.equals("quit")){
			System.out.println("Leaving so soon? [y/n]");
			query = (input.nextLine()).toLowerCase();
			if(query.equals("y") || query.contains("yes")){
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
	// cuts out useless words such as articles, 
	String prep(String query){
		// articles, demonstratives, quantifiers
		query = query.replaceAll("/\b($the)\b/i","");
		query = query.replaceAll("/\b($a)\b/i", "");
		query = query.replaceAll("/\b($an)\b/i", "");
		query = query.replaceAll("/\b($one)\b/i", "");
		query = query.replaceAll("/\b($some)\b/i", "");
		query = query.replaceAll("/\b($few)\b/i", "");
		query = query.replaceAll("/\b($many)\b/i", "");
		query = query.replaceAll("/\b($all)\b/i", "");
		query = query.replaceAll("/\b($enough)\b/i", "");
		query = query.replaceAll("/\b($plenty)\b/i", "");
		query = query.replaceAll("/\b($every)\b/i", "");
		query = query.replaceAll("/\b($neither)\b/i", "");
		query = query.replaceAll("/\b($either)\b/i", "");
		query = query.replaceAll("/\b($each)\b/i", "");
		query = query.replaceAll("/\b($such)\b/i", "");
		query = query.replaceAll("/\b($what)\b/i", "");
		query = query.replaceAll("/\b($rather)\b/i", "");
		query = query.replaceAll("/\b($quite)\b/i", "");
		query = query.replaceAll("/\b($other)\b/i", "");
		query = query.replaceAll("/\b($another)\b/i", "");
		// pronouns
		query = query.replaceAll("/\b($he)\b/i", "");
		query = query.replaceAll("/\b($him)\b/i", "");
		query = query.replaceAll("/\b($she)\b/i", "");
		query = query.replaceAll("/\b($her)\b/i", "");
		query = query.replaceAll("/\b($his)\b/i", "");
		query = query.replaceAll("/\b($hers)\b/i", "");
		query = query.replaceAll("/\b($it)\b/i", "");
		query = query.replaceAll("/\b($its)\b/i", "");
		query = query.replaceAll("/\b($ours)\b/i", "");
		query = query.replaceAll("/\b($our)\b/i", "");
		query = query.replaceAll("/\b($their)\b/i", "");
		query = query.replaceAll("/\b($they)\b/i", "");
		query = query.replaceAll("/\b($me)\b/i", "");
		// 
		query = query.replaceAll("/\b($about)\b/i", "");
		query = query.replaceAll("/\b($is)\b/i", "");
		query = query.replaceAll("/\b($are)\b/i", "");
		query = query.replaceAll("/\b($of)\b/i", "");
		return query;
	}
}
