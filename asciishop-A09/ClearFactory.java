import java.util.Scanner;

public class ClearFactory implements Factory {

	public ClearFactory() {
	}

	public Operation create(Scanner scanner) throws FactoryException {
		return new ClearOperation();
	}

}
