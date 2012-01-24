public class ClearOperation implements Operation {

	public ClearOperation() { }

	// gibt ein neues AsciiImage zurück, das dem übergebenen AsciiImage entspricht, wobei alle Zeichen auf das hellste Zeichen, sprich dem letzten Zeichen im Zeichensatz des Bildes, gesetzt sind.
	public AsciiImage execute(AsciiImage img) throws OperationException {
		String cs = img.getCharset();

		AsciiImage result = new AsciiImage(img);
		char clearChar = cs.charAt(cs.length() - 1);

		for(int i = 0; i < result.getWidth(); i++)
			for(int j = 0; j < result.getHeight(); j++)
				result.setPixel(i, j, clearChar);

		return result;
	}

}