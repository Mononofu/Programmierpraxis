import java.util.ArrayList;
import java.util.Collections;

abstract public class FilterOperation implements Operation {
	BlockGenerator borderMode;

	public FilterOperation(BlockGenerator borderMode) { 
		this.borderMode = borderMode;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		AsciiImage result = new AsciiImage(img);
		char clearChar = cs.charAt(cs.length() - 1);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++) {
				int[] pixels = borderMode.getBlock(img, i, j);
				result.setPixel(i, j, cs.charAt( filter(pixels) ));	
			}

		return result;
	}

	public abstract int filter(int[] values);

}