import java.util.Set;
import java.util.HashSet;

public class AsciiImage {
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

		// boilerplate to switch height and width
		int temp = height;
		height = width;
		width = temp;

		this.image = newImage;
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
		// it's horizontally symmetric if each line is a palindrome
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