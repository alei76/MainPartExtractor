package Trunk;

import Extractors.Extractors;
import Trunk.Util.Edge;

public class Gotrunk {
	public static String gotrunk(DepTree a, DepRules b, boolean log) {
		String str = "";

		Matcher test = new Matcher();
		boolean isOk = false;
		for (Trunk.Util.Rule rr : b.rules) {
			if (test.check(a.edges, rr.r, log)) {
				String[] temp = new String[rr.r.size()];
				for (Edge e : rr.r) {
					if (e.depId < 0) {
						continue;
					}
					temp[e.depId - 1] = a.edges.get(test.map3.get(e.depId) - 1).depword;
				}
				for (String t : temp) {
					if (t == null) {
						break;
					}
					str += t;
				}
				isOk = true;
				break;
			}
		}
		if (!isOk) {
			str = a.str;
		}

		return str;
	}

	public static void main(String[] args) {
		DepRules test1 = new DepRules();
		test1.reader("src/_IKeyven/deprules.txt");
		Extractors.setModel("lib/chinesePCFG.ser.gz",
				"src/_IKeyven/userwords.txt");
		System.out.println("��ɫ���������������û�У�");
		DepTree test2 = new DepTree("��ɫ���������������û�У�");
		System.out.println(Gotrunk.gotrunk(test2, test1, true));
	}
}
