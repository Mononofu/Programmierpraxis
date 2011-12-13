import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class AsciiImage {
	char[][] image;
	int height;
	int width;
	String charset;

	private boolean onlyUniqueChars(String str) {
		if(str.length() >= 1)
			return true;
		return !str.substring(1).contains("" + str.charAt(0)) && onlyUniqueChars(str.substring(1));
	}

    // erzeugt ein ASCII-Bild der spezifizierten Größe und mit dem angegebenen 
    // Zeichensatz. Anfangs sind alle Pixel auf den hellsten Wert des 
    // Zeichensatzes (also dem letzten Zeichen des Strings) gesetzt. Überprüfen 
    // Sie an dieser Stelle ob Breite und Höhe beide größer 0 sind und werfen 
    // Sie andernfalls eine IllegalArgumentException. Werfen Sie auch eine 
    // IllegalArgumentException, falls das charset ein Zeichen doppelt enthält 
    // oder gar keine Zeichen umfasst.
	public AsciiImage(int h, int w, String cs) {
		if(h <=0 || w <= 0 || 0 == cs.length() || !onlyUniqueChars(cs))
			throw new IllegalArgumentException();
		height = h;
		width = w;
		charset = cs;
		image = new char[width][height];

		char bg = cs.charAt(cs.length() - 1);
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				image[i][j] = bg;
	}

	public AsciiImage(AsciiImage img) {
		height = img.height;
		width = img.width;
		charset = img.charset;
		image = new char[width][height];

		// perform deep-copy of old array
		// since char is a primitive, so we can assign directly
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				image[i][j] = img.image[i][j];
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public String getCharset() { return charset; }

	public char getPixel(int x, int y) { return image[x][y]; }

	public char getPixel(AsciiPoint p) { return getPixel(p.getX(), p.getY()); }

	public boolean isValidPixel(int x, int y) { return x >= 0 && x < width && y >= 0 && y < height; }

	public ArrayList<AsciiPoint> getPointList(char c) {
		ArrayList<AsciiPoint> pixels = new ArrayList<AsciiPoint>();

		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				if(image[i][j] == c) pixels.add(new AsciiPoint(i, j));
		
		return pixels;
	}

	public void setPixel(int x, int y, char c) {
		image[x][y] = c;
	}

	public void setPixel(AsciiPoint p, char c) { setPixel(p.getX(), p.getY(), c); }

	public String toString() {
		String out = "";

		for(int i = 0; i < height; i++)
		{ 
			for(int j = 0; j < width; j++)
				out += image[j][i];
			out += "\n";
		} 
		return out;
	}


}