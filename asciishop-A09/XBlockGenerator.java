public class XBlockGenerator extends BlockGenerator {
	XBlockGenerator(int s) { super(s); }
	
	public int getPixel(int a, int b, AsciiImage img) {
		String cs = img.getCharset();
		
		if( img.isValidPixel(a, b) )
			return cs.indexOf(img.getPixel(a, b));
		else
			return cs.length() - 1;
	}
}