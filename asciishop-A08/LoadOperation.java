public class LoadOperation implements Operation {
	private String data;

	public LoadOperation(String data) { this.data = data; }

	// gibt ein neues AsciiImage zurück, das von Größe und Zeichensatz dem 
	// übergebenen AsciiImage entspricht und in das die Daten geladen wurden. 
	// Tritt beim Laden ein Fehler auf (zu wenige oder zu viele Daten bzw. 
	// ungültige Zeichen), so wird eine OperationException mit einer 
	// entsprechenden Fehlermeldung geworfen.
	public AsciiImage execute(AsciiImage img) throws OperationException {
		AsciiImage result = new AsciiImage(img);

		int lineNo = 0;
		for(String line : data.split("\n")) {
			if(line.length() != result.getWidth())
				throw new OperationException("line length mismatch in line: " + lineNo + " - " + line.length() + " instead of " + img.getWidth());

			for(int i = 0; i < line.length(); i++) {
				result.setPixel(i, lineNo, line.charAt(i));
			}

			lineNo++;
		}

		if(lineNo != result.getHeight()) 
			throw new OperationException("line number mismatch!");

		return result;
	}

}