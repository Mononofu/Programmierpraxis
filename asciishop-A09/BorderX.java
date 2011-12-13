public class BorderX extends BorderMode {
	public int getPixel(int a, int b, AsciiImage img) {
		String cs = img.getCharset();
		
		if( img.isValidPixel(a, b) )
			return cs.indexOf(img.getPixel(a, b));
		else
			return cs.length() - 1;
	}
}