import java.util.Scanner;

public class AsciiShop {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AsciiImage image = new AsciiImage(1, 1);

		while(sc.hasNextLine()) {
			String command = sc.next();

			if("create".equals(command)) {
				int width = sc.nextInt();
				int height = sc.nextInt();
				enforce(width > 0 && height > 0);
				image = new AsciiImage(height, width);
			}
			else if("clear".equals(command)) {
				image.clear();
			}
			else if("line".equals(command)) {
				int x0 = sc.nextInt();
				int y0 = sc.nextInt();
				int x1 = sc.nextInt();
				int y1 = sc.nextInt();
				char c = sc.next().charAt(0);
				image.drawLine(x0, y0, x1, y1, c);
			}
			else if("load".equals(command)) {
				String eof = sc.next();
				sc.nextLine();
				int lineNo = 0;
				while(sc.hasNextLine()) {
					String line = sc.next();
					if(line.equals(eof))
						break;	
					sc.nextLine();
					enforce(line.length() == image.getWidth());
					for(int i = 0; i < line.length(); i++) {
						image.setPixel(i, lineNo, line.charAt(i));
					}
					lineNo++;
				}
				enforce(lineNo == image.getHeight());
			}
			else if("print".equals(command)) {
				System.out.println(image);
			}
			else if("replace".equals(command)) {
				image.replace(sc.next().charAt(0), sc.next().charAt(0));
			}
			else if("flip-v".equals(command)) {
				image.flipV();
			}
			else if("transpose".equals(command)) {
				image.transpose();
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
				if(x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) 
					image.fill(x, y, c);
				else {
					System.out.println("OPERATION FAILED");
					return;
				}
			}
			else if("symmetric-h".equals(command)) {
				System.out.println(image.isSymmetricH());
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