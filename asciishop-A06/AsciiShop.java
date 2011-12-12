import java.util.Scanner;

public class AsciiShop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AsciiImage img = new AsciiImage(1, 1);
		AsciiStack stack = new AsciiStack(3);

		// first command has to be create
		enforce(sc.hasNextLine() && sc.next().equals("create"));

		int width = sc.nextInt();
		int height = sc.nextInt();
		enforce(width > 0 && height > 0);
		img = new AsciiImage(height, width);

		while(sc.hasNextLine()) {
			String command = sc.next();

			if("centroid".equals(command)) {
				char c = sc.next().charAt(0);
				System.out.println(img.getCentroid(c));
			}
			else if("clear".equals(command)) {
				stack.push(new AsciiImage(img));
				img.clear();
			}
			else if("grow".equals(command)) {
				stack.push(new AsciiImage(img));
				char c = sc.next().charAt(0);
				img.growRegion(c);
			}
			else if("fill".equals(command)) {
				// make sure that the command has correct syntax
				enforce(sc.hasNextInt());
				int x = sc.nextInt();

				enforce(sc.hasNextInt());
				int y = sc.nextInt();

				enforce(sc.hasNext());
				char c = sc.next().charAt(0);
				
				// now check bounds and execute if possible
				if(x >= 0 && x < img.getWidth() && y >= 0 && y < img.getHeight()) 
					img.fill(x, y, c);
				else {
					System.out.println("OPERATION FAILED");
					return;
				}
			}
			else if("flip-v".equals(command)) {
				img.flipV();
			}
			else if("line".equals(command)) {
				stack.push(new AsciiImage(img));
				int x0 = sc.nextInt();
				int y0 = sc.nextInt();
				int x1 = sc.nextInt();
				int y1 = sc.nextInt();
				char c = sc.next().charAt(0);
				img.drawLine(x0, y0, x1, y1, c);
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
			else if("straighten".equals(command)) {
				stack.push(new AsciiImage(img));
				char c = sc.next().charAt(0);
				img.straightenRegion(c);
			}
			else if("symmetric-h".equals(command)) {
				System.out.println(img.isSymmetricH());
			}
			else if("transpose".equals(command)) {
				stack.push(new AsciiImage(img));
				img.transpose();
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