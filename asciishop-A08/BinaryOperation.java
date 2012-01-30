public class BinaryOperation implements Operation {
	char thresh;

	public BinaryOperation(char threshold) { thresh = threshold; }

	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		char darkest = cs.charAt(0);
		char brightest = cs.charAt(cs.length() - 1);

		int iThresh = cs.indexOf(thresh);

		if(!cs.contains(""+thresh))
			throw new OperationException("threshold not in charset");

		AsciiImage result = new AsciiImage(img);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++)
				if(cs.indexOf(result.getPixel(i, j)) < iThresh )
					result.setPixel(i, j, darkest);
				else
					result.setPixel(i, j, brightest);

		return result;
	}

}