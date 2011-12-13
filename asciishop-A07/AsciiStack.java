public class AsciiStack {
	// Diese Klasse implementiert einen Stack (vgl. Stapelspeicher), der seine Größe dynamisch anpasst. Es kann eine beliebige Anzahl an AsciiImage-Objekten gespeichert werden, wobei der Zugriff immer nur auf das oberste Element möglich ist. Diese Implementierung nutzt intern die Klasse AsciiStackNode um mehrere Bilder in einer Liste zu verketten.

	private AsciiStackNode head;
	// erzeugt einen leeren Stack.
	public AsciiStack() {
		head = null;
	}

	// überprüft, ob zumindest ein Element am Stack liegt.
	public boolean empty() {
		return null == head;
	}
	
	// gibt das oberste Element am Stack zurück und entfernt dieses. Liegt kein Element am Stack, so soll null zurückgegeben werden.
	public AsciiImage pop() {
		if(empty()) 
			return null;
		AsciiImage img = head.getImage();
		head = head.getNext();
		return img;
	}

	// gibt das oberste Element am Stack zurück ohne es zu entfernen. Liegt nichts am Stack, so soll null zurückgegeben werden.
	public AsciiImage peek() {
		return head.getImage();
	}

	// legt ein AsciiImage oben auf den Stack.
	public void push(AsciiImage img) {
		head = new AsciiStackNode(img, head);
	}

	// gibt die Anzahl der Elemente im Stack zurück.
	public int size() {
		return empty() ? 0 : head.size();
	}

	private class AsciiStackNode {
	//	Diese Klasse implementiert einen Knoten des Stacks. Beachten Sie untenstehende Hinweise zu dieser Klasse.
	private AsciiStackNode next;
	private AsciiImage image;

	// inizialisiert den Listenknoten.
	public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
		this.image = image;
		this.next = next;
	}

	// liefert das vom Knoten referenzierte AsciiImage zurück.
	public AsciiImage getImage() {
		return image;
	}

	// liefert eine Referenz auf den nächsten Knoten zurück.
	public AsciiStackNode getNext() {
		return next;
	}

	// liefert die Anzahl der Knoten in der von diesem Knoten referenzierten Restliste plus eins (fÃŒr diesen Knoten).
	public int size() {
		return (next == null) ? 1 : 1 + next.size();		// maybe Java has tail-call optimization. probably not ...
	}
}
}