import java.util.Set;
import java.util.HashSet;

public class AsciiImage {
	char[][] image;
	int height;
	int width;

	AsciiImage(int h, int w) {
		height = h;
		width = w;
		image = new char[width][height];
		clear();
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void clear() {
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				image[i][j] = '.';
	}

	public void drawLine(int x0, int y0, int x1, int y1, char c) {
		int dx = x1 - x0;
		int dy = y1 - y0;

		double slope = dx == 0 ? 0 : 1.0*dy / dx;

		if(dx >= 0 && Math.abs(dy) <= Math.abs(dx))
			for(int i = 0; i <= dx; i++)
				setPixel(x0 + i, (int) Math.round(y0 + i*slope), c);
		else if(dx < 0 && Math.abs(dy) <= Math.abs(dx))
			drawLine(x1, y1, x0, y0, c);
		else { 
			transpose();
			if(dy >= 0 && Math.abs(dy) > Math.abs(dx))
				drawLine(y0, x0, y1, x1, c);
			else
				drawLine(y1, x1, y0, x0, c);
			transpose();
		}
	}

	public void flipV() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height / 2; j++) {
				char temp = image[i][j];
				image[i][j] = image[i][height - j - 1];
				image[i][height - j - 1] = temp;
			}
		}
	}

	public char getPixel(int x, int y) {
		return image[x][y];
	}

	private String getLine(int n) {
		String out = "";
		for(int i = 0; i < width; i++)
			out += image[i][n];
		return out;
	}

	public void setPixel(int x, int y, char c) {
		//System.out.println("setting " + x + " " + y );
		image[x][y] = c;
	}


	public void transpose() {
		char[][] newImage = new char[height][width];

		// for each line j, get char at column i and add it to our new
		// line. Then add this new line to the image
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				newImage[j][i] = image[i][j];
			}
		}

		// boilerplate to switch height and width
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

	public void replace(char oldChar, char newChar) {
		for(int i = 0; i < width; i++) 
			for(int j = 0; j < height; j++) 
				if(image[i][j] == oldChar)
					image[i][j] = newChar;
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