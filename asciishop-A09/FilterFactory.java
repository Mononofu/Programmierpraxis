import java.util.Scanner;

public class FilterFactory implements Factory {

	public FilterFactory() {
	}

	private BorderMode getBorderMode(String s) throws FactoryException {
		if("x".equals(s))
			return new BorderX();
		else if("replicate".equals(s))
			return new BorderReplicate();
		else if("circular".equals(s))
			return new BorderCircular();
		else if("symmetric".equals(s))
			return new BorderSymmetric();
		else
			throw new FactoryException("Unknown border mode: " + s);
	}

	public Operation create(Scanner scanner) throws FactoryException {
		Scanner sc = new Scanner(scanner.nextLine());
		String command = sc.next();

		int size = 3;
		BorderMode borderMode = new BorderX();

		while(sc.hasNext()) {
			if(sc.hasNextInt()) {
				size = sc.nextInt();
				if ( (size + 1) % 2 != 0)
					throw new FactoryException("Size needs to be odd");
			} else {
				borderMode = getBorderMode(sc.next().trim());
			}
		}

		if("median".equals(command))
			return new MedianOperation(size, borderMode);
		else if("average".equals(command))
			return new AverageOperation(size, borderMode);
		else
			throw new FactoryException();
	}

}
