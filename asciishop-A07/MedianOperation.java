import java.util.ArrayList;
import java.util.Collections;

public class MedianOperation implements Operation {

	public MedianOperation() { }

	// führt auf einer Kopie des Bildes den Medianfilter aus. Dabei werden immer
	// 3 mal 3 Größe Blöcke des Bildes betrachtet, die Pixel nach ihrem 
	// `Helligkeitswert' sortiert und dann der Median (also das in der 
	// sortierten Liste in der Mitte stehende Zeichen) als neues Pixel im 
	// Mittelpunkt des Blocks gesetzt.
	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		AsciiImage result = new AsciiImage(img);
		char clearChar = cs.charAt(cs.length() - 1);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++) {
				ArrayList<Integer> pixels = new ArrayList<Integer>();

				for(int a = i-1; a <= i+1; a++)
					for(int b = j-1; b <= j+1; b++) 
						if( img.isValidPixel(a, b) )
							pixels.add(cs.indexOf(img.getPixel(a, b)));
						else
							pixels.add(cs.length() - 1);

				Collections.sort(pixels);
				result.setPixel(i, j, cs.charAt( pixels.get( 4 )));	
			}

		return result;
	}

}