public class AverageOperation extends FilterOperation {

	public AverageOperation(int size, BorderMode borderMode) { super(size, borderMode); }

	public int filter(int[] values) {
		double sum = 0.0;
		for(int i : values)
			sum += i;
		return (int) Math.round(sum / values.length);
	}

}