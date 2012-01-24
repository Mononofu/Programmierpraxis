import java.util.LinkedHashSet;
import java.util.Collection;

public class MetricSet<E> extends LinkedHashSet<E> {
	public MetricSet() {
	}

	public MetricSet(Collection<? extends E> c)  {
		for(E item : c) {
			this.add(item);
		}
	}

	// liefert ein neues MetricSet zurück, in dem nur die Elemente enthalten
	// sind, die die minimale Distanz zum spezifizierten Element e haben. 
	// Das kann auch nur ein Element sein. m ist die Metrik, die als Distanzmaß 
	// benutzt werden soll.
	public MetricSet<E> search(E e, Metric<? super E> m) {
		int curDistance = Integer.MAX_VALUE;
		MetricSet<E> result = new MetricSet<E>();

		for(E item : this) {
			int dist = m.distance(item, e);
			if(dist < curDistance) {
				result.clear();
				curDistance = dist;
			}
			if(dist == curDistance)
				result.add(item);
		}

		return result;
	}
}