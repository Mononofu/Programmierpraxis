public class PixelCountMetric implements Metric<AsciiImage> {
	public int distance(AsciiImage i1, AsciiImage i2) {
		return Math.abs(i1.getWidth() * i1.getHeight() - i2.getWidth() * i2.getHeight());
	}
}