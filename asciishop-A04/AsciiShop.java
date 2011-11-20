import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

class AsciiImage {
	String image;
	int height;
	int width;

	AsciiImage(int h, int w) {
		height = h;
		width = w;
		image = "";
	}

	public Boolean addLine(String line) {
		if(line.length() != width) return false;
		image += line;
		return true;
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void flipV() {
		String newImage = "";
		for(int i = height; i > 0; i--)
			newImage += image.substring((i-1)*width, i*width);
		image = newImage;
	}

	private char getPixel(int x, int y) {
		return image.charAt(x + width * y);
	}

	private String getLine(int n) {
		return image.substring(n*width, (n+1)*width);
	}

	private void setPixel(int x, int y, char c) {
		int index = x + width * y;
		image = image.substring(0, index) + c + image.substring(index+1);
	}

	public int getUniqueChars() {
		String chars = "";
		for(char c : image.toCharArray())
			if(!chars.contains("" + c))
				chars += "" + c;
			
		return chars.length();
	}

	public void transpose() {
		String newImage = "";

		// for each line j, get char at column i and add it to our new
		// line. Then add this new line to the image
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				newImage += getPixel(i, j);
			}
		}

		// boilerplate to switch height and width,
		// create new image and cache old one
		int temp = height;
		height = width;
		width = temp;

		image = newImage;
	}

	public void fill(int x, int y, char c) {
		// cache the char which was here previously for later comparisons
		char old = getPixel(x, y);
		// replace it in the image, so subsequent calls work on correct data
		setPixel(x, y, c);

		// For each of the 4 chars surrounding our char, check if it's a valid
		// position. If so, recursively call fill with the new position
		if(x + 1 < width && old == getPixel(x+1, y) )
			fill(x+1, y, c);
		if(x - 1 >= 0 && old == getPixel(x-1, y) )
			fill(x-1, y, c);
		if(y + 1 < height && old == getPixel(x, y+1) )
			fill(x, y+1, c);
		if(y - 1 >= 0 && old == getPixel(x, y-1) )
			fill(x, y-1, c);
	}

	public String toString() {
		String out = "";
		for(int i = 0; i < height; i++)
			out += getLine(i) + "\n";
		return out;
	}

	public Boolean isSymmetricH() {
		for(int i = 0; i < height; i++)
			if(!isPalindrome(getLine(i)))
				return false;
		return true;
	}

	private Boolean isPalindrome(String str) {
		if(str.length() == 0 || str.length() == 1)
			return true;
		return (str.charAt(0) == str.charAt(str.length() - 1) && 
				isPalindrome(str.substring(1, str.length() - 1)));
	}
}

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