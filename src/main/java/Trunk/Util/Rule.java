package Trunk.Util;

public class Rule {
	public java.util.List<Edge> r = new java.util.ArrayList<Edge>();

	public void add(Edge e) {
		r.add(e);
	}

	public String toString() {
		String str = "";

		for (Edge e : r) {
			str += e + "\n";
		}

		return str;
	}
}
