import java.util.ArrayList;
import java.util.Collections;

abstract public class FilterOperation implements Operation {
	BlockGenerator borderMode;

	public FilterOperation(BlockGenerator borderMode) { 
		// pass in different BlockGenerators for different behavior
		this.borderMode = borderMode;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		AsciiImage result = new AsciiImage(img);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++) {
				// use our BlockGenerator to get the surrounding pixels
				int[] pixels = borderMode.getBlock(img, i, j);
				// then filter them with 'filter', overriden by subclasses
				result.setPixel(i, j, cs.charAt( filter(pixels) ));	
			}

		return result;
	}

	// subclasses override this to provide own functionality
	public abstract int filter(int[] values);

}