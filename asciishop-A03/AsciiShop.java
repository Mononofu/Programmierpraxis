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
		width = image[0].length();

		for(int i = 1; i < height; i++) {
			String line = sc.nextLine();
			enforce(! line.startsWith("fill"));

			// make sure that the line length always stays the same
			enforce(width== line.length());

			image[i] = line;
		}

		while(sc.hasNextLine()) {
			enforce("fill".equals(sc.next()));

			enforce(sc.hasNextInt());
			int x = sc.nextInt();

			enforce(sc.hasNextInt());
			int y = sc.nextInt();

			enforce(sc.hasNext());
			char c = sc.next().charAt(0);
			
			if(x >= 0 && x < width && y >= 0 && y < height) 
				fill(image, x, y, c);
			else {
				System.out.println("OPERATION FAILED");
				return;
			}
			sc.nextLine(); // again, manually advance to next line
		}

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
		char old = image[y].charAt(x);
		image[y] = image[y].substring(0, x) + c + image[y].substring(x+1);

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