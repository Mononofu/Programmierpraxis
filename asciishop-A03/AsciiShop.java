import java.util.Scanner;

public class AsciiShop {
	static int width = -1;
	static int height = -1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		enforce("read".equals(sc.next()));
		height = sc.nextInt();
		sc.nextLine(); // we exhausted all symbols except newline
		// so we need to manually advance once

		String[] image = new String[height];
		image[0] = sc.nextLine();
		// length of first line is our standard width
		width = image[0].length();

		for(int i = 1; i < height; i++) {
			// make sure that we really read as many lines as specified on the
			// first line of input we read
			enforce( sc.hasNextLine() );
			String line = sc.nextLine();

			// also take care that fill commands don't end up in the image
			enforce(! line.startsWith("fill"));

			// make sure that the line length always stays the same
			enforce(width== line.length());

			image[i] = line;
		}


		while(sc.hasNextLine()) {
			// from here on, only valid "fill" commands should be encountered
			enforce("fill".equals(sc.next()));

			// make sure that those commands have correct syntax
			enforce(sc.hasNextInt());
			int x = sc.nextInt();

			enforce(sc.hasNextInt());
			int y = sc.nextInt();

			enforce(sc.hasNext());
			char c = sc.next().charAt(0);
			
			// now check bounds and execute if possible
			if(x >= 0 && x < width && y >= 0 && y < height) 
				fill(image, x, y, c);
			else {
				System.out.println("OPERATION FAILED");
				return;
			}
			sc.nextLine(); // again, manually advance to next line
		}

		// echo our (maybe modified) image
		for(String line : image)
			System.out.println(line);
		
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

	public static void fill(String[] image, int x, int y, char c) {
		// cache the char which was here previously for later comparisons
		char old = image[y].charAt(x);
		// replace it in the image, so subsequent calls work on correct data
		image[y] = image[y].substring(0, x) + c + image[y].substring(x+1);

		// For each of the 4 chars surrounding our char, check if it's a valid
		// position. If so, recursively call fill with the new position
		if(x + 1 < width && old == image[y].charAt(x+1) )
			fill(image, x+1, y, c);
		if(x - 1 >= 0 && old == image[y].charAt(x-1) )
			fill(image, x-1, y, c);
		if(y + 1 < height && old == image[y+1].charAt(x) )
			fill(image, x, y+1, c);
		if(y - 1 >= 0 && old == image[y-1].charAt(x) )
			fill(image, x, y-1, c);
	}
}