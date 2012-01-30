public class ReplicateBlockGenerator extends BlockGenerator {
	ReplicateBlockGenerator(int s) { super(s); }
	
	public int getPixel(int a, int b, AsciiImage img) {
		String cs = img.getCharset();

		if( img.isValidPixel(a, b) )
			return cs.indexOf(img.getPixel(a, b));
		else {
			// if a pixel is out of bounds, just take the nearest valid pixel
			// ie the one right next to the border
			int x = a;
			int y = b;
			if (x >= img.getWidth()) x = img.getWidth() - 1;
			else if(x < 0) x = 0;

			if (y >= img.getHeight()) y = img.getHeight() - 1;
			else if(y < 0) y = 0;

			return cs.indexOf(img.getPixel(x, y));
		}
	}
}