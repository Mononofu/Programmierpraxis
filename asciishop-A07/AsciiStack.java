public class AsciiStack {
	private AsciiImage[] stack;
	private int increment = 0;
	private int pTopElement = -1;

	public AsciiStack(int increment) {
		stack = new AsciiImage[increment];
		this.increment = increment;
	}

	public int capacity() { return stack.length; }

	public boolean empty() { return pTopElement == -1; }

	public AsciiImage pop() {
		if(pTopElement >= 0) {
			// get element, then decrement
			AsciiImage top = stack[pTopElement--];
			// null it to allow memory collection
			stack[pTopElement + 1] = null;
			// shrink if possible
			if(capacity() - pTopElement - 1 > increment && capacity() > increment) {
				resize(capacity() - increment);
			}
			return top;
		}
		else
			return null;
	}

	public AsciiImage peek() { 
		if(pTopElement >= 0) return stack[pTopElement]; 
		else return null;
	}

	public void push(AsciiImage img) {
		// if stack is too small make it bigger
		if(pTopElement + 1 >= stack.length) {
			resize(stack.length + increment);
		}
		// need to increment before inserting - otherwise, we would overwrite
		// the old entry
		stack[++pTopElement] = img;
	}

	public int size() { return pTopElement + 1; }

	private void resize(int targetSize) {
		AsciiImage[] newStack = new AsciiImage[targetSize];
		int stackEnd = targetSize;
		// make sure that we don't iterate over the end of our old stack when
		// we increase in size
		if(targetSize > stack.length)
			stackEnd = stack.length;
		for(int i = 0; i < stackEnd; i++)
			newStack[i] = stack[i];
		this.stack = newStack;
	}

}