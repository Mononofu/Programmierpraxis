import java.util.Scanner;

public class AsciiShop {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int lineWidth = sc.nextLine().length();
		int numLines = 1;

		while(sc.hasNextLine()) {
			// make sure that the line length always stays the same
			if (lineWidth != sc.nextLine().length())
				panic();
				// if it dosen't, panic
			// otherwise, just increment our line counter
			numLines++;
		}

		System.out.println(lineWidth + " " + numLines);
	}

	static void panic() {
		System.out.println("INPUT MISMATCH");
		System.exit(1);
	}
}