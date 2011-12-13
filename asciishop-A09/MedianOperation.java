import java.util.Arrays;

public class MedianOperation extends FilterOperation {

	public MedianOperation() { }

	public int filter(int[] values) {
		Arrays.sort(values);
		return values[4];
	}

}