import java.util.Scanner;
import java.util.Arrays;

public class Scrabble {
	
	/**
	* main-Methode: liest von der Standardeingabe ein und arbeitet die
	* einegegebenen Befehle ab. Erzeugt entsprechende Ausgaben.
	*/
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		String eingabe = sc.next();
		
		//macht aus einem String ein char-Array
		char[] charSet = eingabe.toCharArray(); 
		
		while(sc.hasNext()){
			
			String command = sc.next();
			
			if(command.equals("print")) {
				System.out.println(Arrays.toString(charSet));
			} 
			else if(command.equals("occurrences")) {
				System.out.println(occurrences(eingabe, sc.next().charAt(0)));
			}
			else if(command.equals("distinct")) {
				// need to reverse first so ordering is kept
				System.out.println(distinct(reverse(sc.next())));
			}
			else if(command.equals("moveleft")) {
				moveleft(charSet, sc.next().charAt(0));
			}
			else if(command.equals("buildword")) {
				System.out.println(buildword(sc.next(), charSet));
			}

		}
	}
	
	/**
	* bewegt alle Vorkommnisse des Zeichens c im Array charSet ganz nach 
	* links. Die Reihenfolge aller anderen Zeichen im Array ist danach 
	* beliebig.
	* @param charSet Das Array mit der Zeichenmenge
	* @param c Alle Vorkommnisse dieses Zeichens sollen im Array ganz nach 
	* links bewegt werden.
	*/
	public static void moveleft (char[] charSet, char c) {
		int n = occurrences(charSet, c);
		String sum = "";
		for(String str : (new String(charSet)).split("" + c)) {
			sum += str;
		}
		char[] res = (repeat(c, n) + sum).toCharArray();
		// stupid java is stupid
		for(int i = 0; i < sum.length() + n; i++) {
			charSet[i] = res[i];
		}
	}

	static String repeat(char c, int n) {
		if(n == 0) return "";
		else return repeat(c, n-1) + c;
	}
	
	/**
	* liefert die Häufigkeit des angegebenen Zeichens c in der 
	* Zeichenfolge charSet zurück
	* @param charSet Das Array mit der Zeichenmenge
	* @param c Alle Vorkommnisse dieses Zeichens werden gezählt
	* @return Die Anzahl der Vorkommnisse
	*/
	public static int occurrences(char[] charSet, char c) {
		return occurrences(new String(charSet), c);
	}
	
	
	/**
	* liefert die Häufigkeit des angegebenen Zeichens c im 
	* String word
	* @param word Eine Zeichenkette in der die Anzahl des angegebenen 
	* Zeichens bestimmt wird
	* @param c Alle Vorkommnisse dieses Zeichens werden gezählt
	* @return Die Anzahl der Vorkommnisse
	*/
	public static int occurrences(String word, char c) {
		if(0 == word.length()) return 0;
		else return (word.charAt(0) == c ? 1 : 0) + occurrences(word.substring(1), c);	
	}
	
	/**
	* liefert einen String, der alle Zeichen enthält, die auch in word 
	* vorkommen, jedoch keine Zeichen mehrfach enthält. Die Reihenfolge der 
	* Zeichen ist beliebig.
	* @param word Der Eingabestring
	* @return Ein String in dem die Zeichen des Eingabestrings einmalig
	* vorkommen.
	*/
	public static String distinct(String word) {
		if(0 == word.length()) return "";
		char first = word.charAt(0);
		String res = distinct(word.substring(1));
		if(occurrences(res, first) > 0) return res;
		else return res + first;	
	}

	static String reverse(String word) {
		if(0 == word.length()) return "";
		else return reverse(word.substring(1)) + word.charAt(0);
	}

	
	/**
	* liefert einen boolean Wert der angibt, ob das als Parameter 
	* angegebene Wort word aus den im Array from gespeicherten Zeichen
	* gebildet werden kann (true) oder nicht (false).
	* @param word Das zu bildende Wort
	* @param from Aus den Zeichen dieses Arrays soll das Wort gebildet 
	* werden
	* @return true wenn das Wort gebildet werden kann, sonst false
	*/
	public static boolean buildword(String word, char[] from) {
		if(word.length() == 0) return true;
		else return (occurrences(from, word.charAt(0)) > 0) && buildword(word.substring(1), from);	
	}
	
}
