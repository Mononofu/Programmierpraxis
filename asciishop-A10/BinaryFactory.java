import java.util.Scanner;

public class BinaryFactory implements Factory {

	public BinaryFactory() {
	}

	public Operation create(Scanner scanner) throws FactoryException {

		if (!scanner.hasNext()) {
			throw new FactoryException("Insufficient parameter");
		}

		// when using nextLine(), leading whitespace is included
		return new BinaryOperation(scanner.nextLine().trim().charAt(0));	
	}

}
