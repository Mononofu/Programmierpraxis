public class UniqueCharsMetric implements Metric<AsciiImage> {
	public int distance(AsciiImage i1, AsciiImage i2) {
		return Math.abs(i1.getUniqueChars() - i2.getUniqueChars());
	}
}