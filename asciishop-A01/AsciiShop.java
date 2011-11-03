import java.util.Scanner;

public class AsciiShop {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// length of first line is our standard width
		int width = sc.nextLine().length();
		int height = 1;

		while(sc.hasNextLine()) {
			// make sure that the line length always stays the same
			enforce(width == sc.nextLine().length());

			height++;
		}

		System.out.println(width + " " + height);
	}

	static void panic() {
		System.out.println("INPUT MISMATCH");
		System.exit(0);
	}

	// obviously can't name this 'assert'
	static void enforce(boolean condition) {
		if(!condition)
			panic();
	}
}