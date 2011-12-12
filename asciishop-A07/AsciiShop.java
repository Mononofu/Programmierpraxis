import java.util.Scanner;

public class AsciiShop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AsciiImage img = new AsciiImage(1, 1);
		AsciiStack stack = new AsciiStack(3);

		// first command has to be create
		enforce(sc.hasNextLine() && sc.next().equals("create"));

		enforce(sc.hasNextInt());
		int width = sc.nextInt();
		enforce(sc.hasNextInt());
		int height = sc.nextInt();
		enforce(sc.hasNext());
		String charset = sc.next();
		
		img = new AsciiImage(height, width);

		while(sc.hasNextLine()) {
			String command = sc.next();

			if("clear".equals(command)) {
				stack.push(new AsciiImage(img));
				img.clear();
			}
			else if("filter".equals(command)) {
				panic();
			}
			else if("load".equals(command)) {
				stack.push(new AsciiImage(img));
				String eof = sc.next();
				sc.nextLine();
				int lineNo = 0;
				while(sc.hasNextLine()) {
					String line = sc.next();
					if(line.equals(eof))
						break;	
					sc.nextLine();
					enforce(line.length() == img.getWidth());
					for(int i = 0; i < line.length(); i++) {
						img.setPixel(i, lineNo, line.charAt(i));
					}
					lineNo++;
				}
				enforce(lineNo == img.getHeight());
			}
			else if("print".equals(command)) {
				System.out.println(img);
			}
			else if("replace".equals(command)) {
				stack.push(new AsciiImage(img));
				img.replace(sc.next().charAt(0), sc.next().charAt(0));
			}
			else if("undo".equals(command)) {
				if(stack.size() >= 1) {
					img = stack.pop();
					System.out.println("STACK USAGE " + stack.size() + "/" + stack.capacity());
				}
				else {
					System.out.println("STACK EMPTY");
				}
			}
			else {
				System.out.println("UNKNOWN COMMAND");
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