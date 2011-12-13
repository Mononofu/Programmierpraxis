import java.util.Scanner;

public class AsciiShop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AsciiImage img = new AsciiImage(1, 1, "a");
		AsciiStack stack = new AsciiStack();

		// first command has to be create
		enforce(sc.hasNextLine() && sc.next().equals("create"));

		enforce(sc.hasNextInt());
		int width = sc.nextInt();
		enforce(sc.hasNextInt());
		int height = sc.nextInt();
		enforce(sc.hasNext());
		String charset = sc.next();
		
		img = new AsciiImage(height, width, charset);

		while(sc.hasNextLine()) {
			try {
			String command = sc.next();

			if("clear".equals(command)) {
				stack.push(new AsciiImage(img));
				img = (new ClearOperation()).execute(img);
			}
			else if("filter".equals(command)) {
				stack.push(new AsciiImage(img));
				enforce("median".equals(sc.next()));

				img = (new MedianOperation()).execute(img);
			}
			else if("load".equals(command)) {
				stack.push(new AsciiImage(img));

				String eof = sc.next();
				sc.nextLine();
				String data = "";
				while(sc.hasNextLine()) {
					String line = sc.next();
					if(line.equals(eof))
						break;	
					data += line + "\n";
					sc.nextLine();
				}

				img = (new LoadOperation(data)).execute(img);
			}
			else if("print".equals(command)) {
				System.out.println(img);
			}
			else if("replace".equals(command)) {
				stack.push(new AsciiImage(img));
				img = (new ReplaceOperation( sc.next().charAt(0), sc.next().charAt(0) )).execute(img);
			}
			else if("undo".equals(command)) {
				if(stack.size() >= 1) {
					img = stack.pop();
				}
				else {
					System.out.println("STACK EMPTY");
				}
			}
			else {
				System.out.println("UNKNOWN COMMAND");
				return;
			}

			}
			catch (OperationException e) {
				System.out.println("OPERATION FAILED" );
				return;
			}

			sc.nextLine(); // again, manually advance to next line
		}

	}

	static void panic() {
		System.out.println("INPUT MISMATCH");
		System.exit(0);
	}

	// obviously can't name this 'assert'
	static void enforce(boolean condition) {
		if(!condition)
			panic();
	}

	
}