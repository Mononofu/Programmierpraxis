import java.util.ArrayList;
import java.util.Collections;

abstract public class FilterOperation implements Operation {
	int size;
	BorderMode borderMode;

	public FilterOperation(int size, BorderMode borderMode) { 
		this.size = size;
		this.borderMode = borderMode;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		AsciiImage result = new AsciiImage(img);
		char clearChar = cs.charAt(cs.length() - 1);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++) {
				int[] pixels = borderMode.getPixels(i, j, img, size);
				result.setPixel(i, j, cs.charAt( filter(pixels) ));	
			}

		return result;
	}

	public abstract int filter(int[] values);

}