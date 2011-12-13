import java.util.Arrays;

public class MedianOperation extends FilterOperation {
	int size;

	public MedianOperation(int size, BorderMode borderMode) { 
		super(size, borderMode); 
		this.size = size;
	}

	public int filter(int[] values) {
		Arrays.sort(values);
		return values[(size*size) / 2];
	}

}