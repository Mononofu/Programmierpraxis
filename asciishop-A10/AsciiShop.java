import java.util.Scanner;
import java.util.HashMap;

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

		MetricSet<AsciiImage> saves = new MetricSet<AsciiImage>();

		HashMap<String, Factory> commands = new HashMap<String, Factory>();
		commands.put("clear", new ClearFactory());
		commands.put("binary", new BinaryFactory());
		commands.put("filter", new FilterFactory());
		commands.put("load", new LoadFactory());
		commands.put("replace", new ReplaceFactory());
		commands.put("create", new CreateFactory());
		commands.put("save", new SaveFactory(saves));
		commands.put("search", new SearchFactory(saves));


		while(sc.hasNextLine()) {
			String command = sc.next();

			if("print".equals(command)) {
					System.out.println(img);
					sc.nextLine();
			} 
			else if("printsaved".equals(command)) {
				if(saves.isEmpty())
					System.out.println("NO SAVED IMAGES");
				else
					for(AsciiImage i : saves) 
						System.out.println(i);
				
				sc.nextLine();
			}
			else if("undo".equals(command)) {
				sc.nextLine();
				if(stack.size() >= 1) {
					img = stack.pop();
				}
				else {
					System.out.println("STACK EMPTY");
				}
			}
			else {
				try {
					stack.push(new AsciiImage(img));
					img = commands.get(command).create(sc).execute(img);	
				} 
				catch (NullPointerException e) {
					panic("UNKNOWN COMMAND");
				}
				catch (FactoryException e) {
					panic("factory ex: " + e);
				}
				catch (OperationException e) {
					panic("OPERATION FAILED");
				}
			}
		}

	}
	static void panic() { panic("INPUT MISMATCH"); }
	static void panic(String msg) {
		System.out.println(msg);
		System.exit(0);
	}

	// obviously can't name this 'assert'
	static void enforce(boolean condition) {
		if(!condition)
			panic();
	}

	
}