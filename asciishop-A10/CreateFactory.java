import java.util.Scanner;

public class CreateFactory implements Factory {
	// Diese Factory erzeugt CreateOperation.
	public CreateFactory() {
		
	}

	// liest mit Hilfe des Scanners Breite und Höhe und einen String ein und 
	// gibt eine damit initialisierte neue CreateOperation zurück. Tritt beim 
	// Einlesen ein Fehler (zu wenig Parameter, falsche Parameter), so wird 
	// eine FactoryException geworfen.
	public Operation create(Scanner scanner) throws FactoryException {
		if(!scanner.hasNextInt())
			throw new FactoryException();
		int width = scanner.nextInt();

		if(!scanner.hasNextInt())
			throw new FactoryException();
		int height = scanner.nextInt();

		if(!scanner.hasNext())
			throw new FactoryException();
		String charset = scanner.nextLine();

		return new CreateOperation(width, height, charset);
	}
}