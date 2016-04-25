package sim;

public class QueryParser {

	/**
	 * Poorly implemented method to turn strings into arrays of doubles based 
	 * on constituent N-grams. Choosing n=1 turns characters into integers [0,29],
	 * then scales to range.  
	 * 
	 * @param query The string to be <s>mangled and spat back out</s> parsed
	 * @param length The length of the desired array
	 * @param n size of units to be analyzed. probably best to leave in range [1,5]
	 * @return An array of doubles, scaled to range from [-1,1]. Theoretically, each
	 * double is a representation of a unique N-gram taken from the string
	 */
	public static double[] parse(String query, int length, int n){
		double[] output = new double[length];
		double[] values = new double[length+n-1];
		//Make lower case and remove spaces
		query = query.toLowerCase();
		query = query.replace(" ", "");
		//Set array values
		for(int i = 0; i < length+n-1; i++){
			Character c = (i < query.length())?query.charAt(i):' ';
			values[i] = c - 'a';
			if (!(values[i] >= 0 && values[i] < 26)) {
				switch (c) {
				case '*':
					values[i] = 26.0;
				default:
					values[i] = 27.0;
				}
			}
		}
		//Add a normalized double representing each N-gram to the parsed vector
		for(int i = 0; i < length; i++){
			for(int j = 0; j < n; j++){
				output[i] += values[i]*Math.pow(30, j);
			}
			output[i] = (output[i]*2/(Math.pow(30, n) - 1)) - 1;
		}
		return output;
	}
	
}
