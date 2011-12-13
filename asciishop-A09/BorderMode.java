abstract public class BorderMode {
	public int[] getPixels(int x, int y, AsciiImage img, int regionSize){
		int[] pixels = new int[regionSize * regionSize];
		int z=0;

		for(int a = x-(regionSize/2); a <= x+(regionSize/2); a++)
			for(int b = y-(regionSize/2); b <= y+(regionSize/2); b++) 
				pixels[z++] = getPixel(a, b, img);

		return pixels;
	}

	abstract public int getPixel(int a, int b, AsciiImage img);
}