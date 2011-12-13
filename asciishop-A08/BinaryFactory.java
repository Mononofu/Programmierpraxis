import java.util.Scanner;

public class BinaryFactory implements Factory {

	public BinaryFactory() {
	}

	public Operation create(Scanner scanner) throws FactoryException {

		if (!scanner.hasNext()) {
			throw new FactoryException("Insufficient parameter");
		}

		return new BinaryOperation(scanner.next().charAt(0));	
	}

}
