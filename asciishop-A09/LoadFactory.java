import java.util.Scanner;

public class LoadFactory implements Factory {

	public LoadFactory() {
	}

	public Operation create(Scanner scanner) throws FactoryException {
		String eof = scanner.next();
		
		boolean didEncounterEOF = false;
		scanner.nextLine();
		String data = "";

		while(scanner.hasNextLine()) {
			String line = scanner.next();
			if(line.equals(eof)) {
				didEncounterEOF = true;
				break;
			}
			data += line + "\n";
			scanner.nextLine();
		}
		scanner.nextLine();

		if(!didEncounterEOF)
			throw new FactoryException();

		return new LoadOperation(data);
	}

}
