abstract public class BlockGenerator {
	private int regionSize;
	
	BlockGenerator(int s) { regionSize = s; }

	// get the surrounding pixels, amount specified in regionSize
	public int[] getBlock(AsciiImage img, int x, int y){
		int[] pixels = new int[regionSize * regionSize];
		int z=0;

		for(int a = x-(regionSize/2); a <= x+(regionSize/2); a++)
			for(int b = y-(regionSize/2); b <= y+(regionSize/2); b++) 
				pixels[z++] = getPixel(a, b, img);

		return pixels;
	}

	// subclasses override this to control what happens when a pixel is out of
	// bounds - wrap around, extend current pixel, etc
	abstract public int getPixel(int a, int b, AsciiImage img);
}