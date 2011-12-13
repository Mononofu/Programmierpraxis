import java.util.Scanner;

public class FilterFactory implements Factory {

	public FilterFactory() {
	}

	public Operation create(Scanner scanner) throws FactoryException {
		if(!"median".equals(scanner.next()))
			throw new FactoryException();

		return new MedianOperation();
	}

}
