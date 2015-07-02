package Stsg.Util;

import java.util.ArrayList;

public class OutputResult {
	public ArrayList<String> ans = new ArrayList<String>();

	public void visit(Node node) {
		if (node.list.size() == 0) {
			ans.add(node.att.text);
			return;
		}
		for (int i = 0; i < node.list.size(); i++) {
			visit(node.list.get(i));
		}
	}

	public String toString() {
		String o = "";

		for (String s : ans) {
			o += s;
		}

		return o;
	}
}
