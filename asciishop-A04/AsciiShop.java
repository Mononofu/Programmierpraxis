import java.util.Scanner;

public class AsciiShop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		enforce("read".equals(sc.next()));
		int height = sc.nextInt();
		sc.nextLine(); // we exhausted all symbols except newline
		// so we need to manually advance once

		String temp = sc.nextLine();
		// length of first line is our standard width
		AsciiImage image = new AsciiImage(height, temp.length());
		image.addLine(temp);

		for(int i = 1; i < height; i++) {
			// make sure that we really read as many lines as specified on the
			// first line of input we read
			enforce( sc.hasNextLine() );

			// make sure that the line length always stays the same
			// addLine returns false if line lengths don't match
			// that's how enforce knows the error
			enforce(image.addLine(sc.nextLine()));
		}


		while(sc.hasNextLine()) {
			String command = sc.next();

			if("uniqueChars".equals(command)) {
				System.out.println(image.getUniqueChars());
			}
			else if("flip-v".equals(command)) {
				image.flipV();
			}
			else if("transpose".equals(command)) {
				image.transpose();
			}
			else if("fill".equals(command)) {
				// make sure that the command has correct syntax
				enforce(sc.hasNextInt());
				int x = sc.nextInt();

				enforce(sc.hasNextInt());
				int y = sc.nextInt();

				enforce(sc.hasNext());
				char c = sc.next().charAt(0);
				
				// now check bounds and execute if possible
				if(x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) 
					image.fill(x, y, c);
				else {
					System.out.println("OPERATION FAILED");
					return;
				}
			}
			else if("symmetric-h".equals(command)) {
				System.out.println(image.isSymmetricH());
			}
			else {
				// invalid command
				panic();
			}

			sc.nextLine(); // again, manually advance to next line
		}

		// echo our (maybe modified) image
		System.out.print(image);
		System.out.println(image.getWidth() + " " + image.getHeight());
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