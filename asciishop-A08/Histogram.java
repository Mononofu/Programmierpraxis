public class Histogram {
	public static AsciiImage getHistogram(AsciiImage img) {
		String oldCharset = img.getCharset();
		String newCharset = img.getCharset();
		if(!newCharset.contains("#"))
			newCharset += "#";
		// make sure first char is '.' so image will be cleared with it
		if(!newCharset.contains("."))
			newCharset = newCharset + ".";
		else {
			int i = newCharset.indexOf(".");
			newCharset = newCharset.substring(0, i) + newCharset.substring(1+i) + ".";
		}

		AsciiImage hist = new AsciiImage(16, 3 + oldCharset.length(), newCharset);

		double pixelCount = img.getWidth() * img.getHeight();
		double maxPer = 0.0;

		// find max percentage
		for(char c : oldCharset.toCharArray()) {
			double per = img.getPointList(c).size() / pixelCount;
			maxPer = (per > maxPer)	? per : maxPer;		
		}

		// label axis
		for(int i = 0; i < 16; i+=2) {
			String s = String.format("%3d", (int) Math.round(100 * maxPer * (15-i) / 15 )).replace(' ', '.');
			for(int j = 0; j < 3; j++)
				hist.setPixel(j, i, s.charAt(j));
		}

		// now draw each char
		int numChar = 0;
		for(char c : oldCharset.toCharArray()) {
			// which char is it?
			hist.setPixel(3+numChar, 15, c);

			double per = img.getPointList(c).size() / pixelCount;
			for(int i = (int) Math.ceil(15 * per / maxPer); i > 0; i--) 
				hist.setPixel(3+numChar, 15 - i, '#');
			numChar++;
		}

		return hist;
	}
}