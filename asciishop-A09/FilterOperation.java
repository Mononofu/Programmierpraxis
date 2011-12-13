import java.util.ArrayList;
import java.util.Collections;

abstract public class FilterOperation implements Operation {

	public FilterOperation() { }

	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		AsciiImage result = new AsciiImage(img);
		char clearChar = cs.charAt(cs.length() - 1);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++) {
				int[] pixels = new int[]();
				int z=0;

				for(int a = i-1; a <= i+1; a++)
					for(int b = j-1; b <= j+1; b++) 
						if( img.isValidPixel(a, b) )
							pixels[z++] = cs.indexOf(img.getPixel(a, b));
						else
							pixels[z++] = cs.length() - 1;

				result.setPixel(i, j, cs.charAt( filter(pixels) ));	
			}

		return result;
	}

	public abstract int filter(int[] values);

}