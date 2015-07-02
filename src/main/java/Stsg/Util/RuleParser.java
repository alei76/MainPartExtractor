package Stsg.Util;

import java.util.Stack;

public class RuleParser {
	public final static Node createTree(String rule) {
		Node root = null;

		String[] words = rule.split(" ");
		Stack<Node> stack = new Stack<Node>();

		Node var = new Node();
		var.att.gender = words[0];
		var.father = null;
		root = var;
		stack.push(var);

		for (int i = 1; i < words.length; i++) {
			if (words[i].contains("(")) {
				var = new Node();
				var.att.gender = words[i].substring(1);
				var.father = stack.peek();
				stack.peek().list.add(var);
				stack.push(var);
			} else if (words[i].contains("(")) {
				var.att.text = words[i].substring(0, words[i].indexOf(")"));
				for (int j = 0; j < words[i].length() - words[i].indexOf(")"); j++) {
					stack.pop();
				}
			}
		}

		return root;
	}

	public static boolean isEqualTree(Node r1, Node r2) {
		if (!r1.att.gender.equals(r2.att.gender)) {
			return false;
		}
		if (r1.list.size() != r2.list.size()) {
			return false;
		}
		for (int i = 0; i < r1.list.size(); i++) {
			if (!isEqualTree(r1.list.get(i), r2.list.get(i))) {
				return false;
			}
		}

		return true;
	}
}
