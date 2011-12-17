import java.util.Scanner;

public class FilterFactory implements Factory {

	public FilterFactory() {
	}

	private BlockGenerator getBorderMode(String s, int size) throws FactoryException {
		if("x".equals(s))
			return new XBlockGenerator(size);
		else if("replicate".equals(s))
			return new ReplicateBlockGenerator(size);
		else if("circular".equals(s))
			return new CircularBlockGenerator(size);
		else if("symmetric".equals(s))
			return new SymmetricBlockGenerator(size);
		else
			throw new FactoryException("Unknown border mode: " + s);
	}

	public Operation create(Scanner scanner) throws FactoryException {
		Scanner sc = new Scanner(scanner.nextLine());
		String command = sc.next();

		int size = 3;
		BlockGenerator borderMode = new XBlockGenerator(size);

		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				size = sc.nextInt();
				if ( (size + 1) % 2 != 0)
					throw new FactoryException("Size needs to be odd");
			} else {
				borderMode = getBorderMode(sc.next().trim(), size);
			}
		}

		if("median".equals(command))
			return new MedianOperation(borderMode);
		else if("average".equals(command))
			return new AverageOperation(borderMode);
		else
			throw new FactoryException();
	}

}
