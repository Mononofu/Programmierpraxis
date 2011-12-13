public class BorderCircular extends BorderMode{
	public int getPixel(int a, int b, AsciiImage img) {
		String cs = img.getCharset();
		
		return cs.indexOf(img.getPixel( (a+img.getWidth()) % img.getWidth(), (b+img.getHeight()) % img.getHeight()));
	}
}