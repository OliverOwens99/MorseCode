package ie.atu.sw;

import java.util.*;

public class morseCode {

	// A static map declaration I do it like this because it was the only way I
	// could figure out on how to use the same map across the programme
	static final Map<Character, String> englishToMorse = new HashMap<Character, String>() {
		{

			put('a', ".-");
			put('b', "-...");
			put('c', "-.-.");
			put('d', "-..");
			put('e', ".");
			put('f', "..-.");
			put('g', "--.");
			put('h', "....");
			put('i', "..");
			put('j', ".---");
			put('k', "-.-");
			put('l', ".-..");
			put('m', "--");
			put('n', "-.");
			put('o', "---");
			put('p', ".--.");
			put('q', "--.-");
			put('r', ".-.");
			put('s', "...");
			put('t', "-");
			put('u', "..-");
			put('v', "...-");
			put('w', ".--");
			put('x', "-..-");
			put('y', "-.--");
			put('z', "--..");
			put('0', "-----");
			put('1', ".----");
			put('2', "..---");
			put('3', "...--");
			put('4', "....-");
			put('5', ".....");
			put('6', "-....");
			put('7', "--...");
			put('8', "---..");
			put('9', "----.");
			put(' ', "/");
			put('\n', "\n"); // this was to handle the printing to the text  box
		}

	};

	static final Map<String, Character> morseToEnglish;

	static {
		// so this is a decoding map the idea here was to try and place both sets in
		// individual maps to speed up the operation of the programme
		// makes a new map and then gets a set of the data stored in said map however I
		// then place them value,key instead of key,value
		morseToEnglish = new HashMap<>();
		for (Map.Entry<Character, String> entry : englishToMorse.entrySet()) {
			morseToEnglish.put(entry.getValue(), entry.getKey());
		}
	}

	public static String encode(String text) {

		StringBuilder encodeBuild = new StringBuilder();

		// iteration going through the map finding the character converting it to lower
		// case and addding it to a string called morsecode if it finds it apends the
		// string and adds
		// spaces to the string when its not in the map then returns this new string
		// with the mapped morse code value for the character it finds in the map
		//
		for (char c : text.toLowerCase().toCharArray()) {
			String morseCode = englishToMorse.get(c);

			if (morseCode != null) {

				encodeBuild.append(morseCode).append(" ");
			}

			else {

				encodeBuild.append(c).append(" ");
			}

		}

		return encodeBuild.toString();

	}
	// this is a similar method but instead loops up the decode map  map usage should be O(n) time 
	public static String decode(String morse) {
		StringBuilder builder = new StringBuilder();
		
		// takes in the string takes out leading spaces and then ads a space delimiter
		// then we make and array of words and that are then converted into letters and
		// decoded using the map then use / to show spaces
		String[] words = morse.trim().split(" ");
		for (String word : words) {
			String[] letters = word.split(" ");
			for (String letter : letters) {
				Character c = morseToEnglish.get(letter);
				if (c != null) {
					builder.append(c);
				} else {
					builder.append(letter);
				}
			}
			builder.append(" ");
		}

		return builder.toString();
	}
	
	// this was a helper main to test if I could get the wanted effect on a  smaller scale
	public static void main(String[] args) {
		// Morse code by indexing

		// Given Strings
		String morseCode = "  - .... . / --- .-.. -.. / - . ... - .- -- . -. - \r\n"
				+ " ..-. .. .-. ... - / .--. ..- -... .-.. .. ... .... . -.. / -... -.-- / - .... . / . -. --. .-.. .. ... .... / -.-. --- .-.. .-.. . --. . / .- - / -.. --- ..- .- -.-- \r\n"
				+ " .- . -.. . / .---- -.... ----- ----. / & / .---- -.... .---- -----  ";

		System.out.println(" Please Enter what you wish to convert to morse code");
		Scanner sc = new Scanner(System.in);

		String english = sc.next();

		// Morse to English

		System.out.println(encode(english));

		// English to Morse code
		System.out.println(decode(morseCode));

	}

}
