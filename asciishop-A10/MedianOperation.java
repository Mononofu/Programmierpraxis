import java.util.Arrays;

public class MedianOperation extends FilterOperation {

	public MedianOperation(BlockGenerator borderMode) { super(borderMode); }

	public int filter(int[] values) {
		Arrays.sort(values);
		return values[values.length / 2];
	}

}