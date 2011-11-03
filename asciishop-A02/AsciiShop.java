import java.util.Scanner;

public class AsciiShop {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(sc.hasNext()) {
			String label = sc.next();
			if(sc.hasNextInt())
				System.out.println(drawBar(label, sc.nextInt()));
			else if(sc.hasNextDouble())
				System.out.println(drawBar(label, sc.nextDouble()));
			else 
				panic();
		}
	}

	static void panic() {
		System.out.println("INPUT ERROR");
		System.exit(1);
	}

	static String repeat(char c, int n) {
		if(n <= 0) return "";
		else return c + repeat(c, n-1);
	}

	static String drawLabel(String label, int n) {
		if(label.length() >= n) return label.substring(0, n);
		else return label + repeat(' ', n - label.length());
	}

	static String drawBar(String label, int value) {
		if(value >= 0 && value <= 30)
			return  drawLabel(label, 8) + '|' + repeat('#', value) + repeat(' ', 30-value) + '|';
		else 
			panic();
		return "never reached";
	}

	static String drawBar(String label, double value) {
		return drawBar(label, (int)Math.round(value * 30.0));
	}

}