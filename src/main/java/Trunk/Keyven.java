package Trunk;

public class Keyven {
	public static String keyven(String str, DepTree tree) {
		for (Trunk.Util.Edge e : tree.edges) {
			if (e.edgename.equals("nn") || e.edgename.equals("neg")) {
				if (str.indexOf(e.depword) < 0) {
					str = str.replace(e.govword, e.depword + e.govword);
				}
			}
		}
		return str;
	}
}
