public class CreateOperation implements Operation {
	private int width;
	private int height;
	private String charset;

	// erzeugt eine neue CreateOperation, die ein neues Bild mit angegebener 
	// Bildgöße und Zeichensatz erzeugt. Alle Pixel werden mit dem "hellsten" 
	// Zeichen, d.h. dem Zeichen mit größten Index in charset initialisiert.
	public CreateOperation(int width, int height, String charset) {
		this.width = width;
		this.height = height;
		this.charset = charset;
	}

	// gibt ein neues AsciiImage zurück. Der Parameter wird ignoriert 
	// (beispielsweise kann auch null übergeben werden).
	public AsciiImage execute(AsciiImage img) throws OperationException {
		return new AsciiImage(height, width, charset);
	}
}