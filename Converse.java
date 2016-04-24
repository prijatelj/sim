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
		String prompt = new String();
		prompt = (input.nextLine()).toLowerCase();
		prompt = prep(prompt);
		while (!farewell(prompt, input)){
			// process request
			if (prompt.equals("-help")){
				System.out.println("");
			}
			else{
				String response = "";
				// respond
				System.out.println(response);
			}
			
			
			prompt = (input.nextLine()).toLowerCase();
			prompt = prep(prompt);
		}
		
	}
	boolean farewell(String prompt, Scanner input){
		if (prompt.contains("goodbye")){
			System.out.println("Goodbye user!");
			return true;
		}
		else
			return false;
	}
	// cuts out useless words such as articles, 
	String prep(String prompt){
		// articles, demonstratives, quantifiers
		prompt = prompt.replaceAll("/\b($the)\b/i","");
		prompt = prompt.replaceAll("/\b($a)\b/i", "");
		prompt = prompt.replaceAll("/\b($an)\b/i", "");
		prompt = prompt.replaceAll("/\b($one)\b/i", "");
		prompt = prompt.replaceAll("/\b($some)\b/i", "");
		prompt = prompt.replaceAll("/\b($few)\b/i", "");
		prompt = prompt.replaceAll("/\b($many)\b/i", "");
		prompt = prompt.replaceAll("/\b($all)\b/i", "");
		prompt = prompt.replaceAll("/\b($enough)\b/i", "");
		prompt = prompt.replaceAll("/\b($plenty)\b/i", "");
		prompt = prompt.replaceAll("/\b($every)\b/i", "");
		prompt = prompt.replaceAll("/\b($neither)\b/i", "");
		prompt = prompt.replaceAll("/\b($either)\b/i", "");
		prompt = prompt.replaceAll("/\b($each)\b/i", "");
		prompt = prompt.replaceAll("/\b($such)\b/i", "");
		prompt = prompt.replaceAll("/\b($what)\b/i", "");
		prompt = prompt.replaceAll("/\b($rather)\b/i", "");
		prompt = prompt.replaceAll("/\b($quite)\b/i", "");
		prompt = prompt.replaceAll("/\b($other)\b/i", "");
		prompt = prompt.replaceAll("/\b($another)\b/i", "");
		// pronouns
		prompt = prompt.replaceAll("/\b($he)\b/i", "");
		prompt = prompt.replaceAll("/\b($him)\b/i", "");
		prompt = prompt.replaceAll("/\b($she)\b/i", "");
		prompt = prompt.replaceAll("/\b($her)\b/i", "");
		prompt = prompt.replaceAll("/\b($his)\b/i", "");
		prompt = prompt.replaceAll("/\b($hers)\b/i", "");
		prompt = prompt.replaceAll("/\b($it)\b/i", "");
		prompt = prompt.replaceAll("/\b($its)\b/i", "");
		prompt = prompt.replaceAll("/\b($ours)\b/i", "");
		prompt = prompt.replaceAll("/\b($our)\b/i", "");
		prompt = prompt.replaceAll("/\b($their)\b/i", "");
		prompt = prompt.replaceAll("/\b($they)\b/i", "");
		prompt = prompt.replaceAll("/\b($me)\b/i", "");
		// 
		prompt = prompt.replaceAll("/\b($about)\b/i", "");
		prompt = prompt.replaceAll("/\b($is)\b/i", "");
		prompt = prompt.replaceAll("/\b($are)\b/i", "");
		prompt = prompt.replaceAll("/\b($of)\b/i", "");
		return prompt;
	}
}
