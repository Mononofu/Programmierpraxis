public class SymmetricBlockGenerator extends BlockGenerator{
	SymmetricBlockGenerator(int s) { super(s); }
	
	public int getPixel(int a, int b, AsciiImage img) {
		String cs = img.getCharset();

		if( img.isValidPixel(a, b) )
			return cs.indexOf(img.getPixel(a, b));
		else {
			int x = a;
			int y = b;
			if (x >= img.getWidth()) x = Math.abs(x - 2*img.getWidth() + 1);
			else if(x < 0) x = Math.abs(x+1);

			if (y >= img.getHeight()) y = Math.abs(y - 2*img.getHeight() + 1);
			else if(y < 0) y = Math.abs(y+1);

			return cs.indexOf(img.getPixel(x, y));
		}
	}
}