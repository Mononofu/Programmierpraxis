public class SaveOperation implements Operation {
	private MetricSet<AsciiImage> saved;
	// erzeugt eine neue SaveOperation. saved ist eine Referenz auf ein 
	// MetricSet, in dem die Bilder gespeichert werden sollen.
	public SaveOperation(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}
	
	// speichert das spezifizierte Bild, d.h., stellt sicher, dass das 
	// spezifizierte Bild der dem Konstruktor übergebenen Set hinzugefügt wurde 
	// (Ist in diesem bereits ein Bild img2, so dass img2.equals(img), oder ist 
	// img == null wird kein neues Element hinzugefügt). Die Rückgabe der 
	// Methode ist eine Kopie des spezifizierten Bildes img.

	public AsciiImage execute(AsciiImage img) throws OperationException {
		saved.add(img);
		return new AsciiImage(img);
	}

	// liefert die Collection mit gespeicherten Bildern.
	public MetricSet<AsciiImage> getSaved() {
		return saved;		
	}
}
