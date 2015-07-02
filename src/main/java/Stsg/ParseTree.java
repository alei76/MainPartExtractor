package Stsg;

import java.util.*;

import org.ansj.domain.Term;
import Stsg.Util.*;
import edu.stanford.nlp.trees.*;

public class ParseTree {
	public Tree parseTree = null;
	public Node createTree(List<Term> list) {
		Tree parse = Extractors.KTree.getStanfordTree(list);
		this.parseTree = parse;
		String str = "";
		for (int i = 0; i < parse.toString().length(); i++) {
			if (parse.toString().charAt(i) == '[') {
				while (parse.toString().charAt(i) != ']') {
					i++;
				}
				i++;
			}
			str += parse.toString().charAt(i);
		}

		Stack<Node> stack = new Stack<Node>();
		String[] results = str.trim().split(" ");
		Node root = new Node();
		stack.push(root);
		for (String s : results) {
			if (s.trim().length() == 0) {
				continue;
			}
			if (s.charAt(0) == '(') {
				Node node = new Node();
				node.father = stack.peek();
				stack.peek().addChild(node);
				Attribute att = new Attribute();
				att.gender = s.substring(1);
				node.att = att;
				stack.push(node);
			} else if (s.indexOf(')') >= 0) {
				Node node = stack.pop();
				node.att.text = s.substring(0, s.indexOf(')'));
				for (int k = 0; k < s.length() - 1 - s.indexOf(')'); k++) {
					stack.pop();
				}
			}
		}

		return stack.peek();
	}

	public Node findNode(String text, Node node) {
		if (node.att.text.equals(text)) {
			return node;
		}
		for (int i = 0; i < node.list.size(); i++) {
			Node temp = findNode(text, node.list.get(i));
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}

	private String printTree1(Node node) {
		String str = "";
		if (node.att.gender.trim().length() == 0 && node.list.size() == 0) {
			return "";
		}
		if (node.att.text.trim().length() > 0) {
			str += "(" + node.att.gender + ":" + node.att.text.trim() + ")";
			return str;
		} else {
			str += "(" + node.att.gender;
		}
		java.util.List<Node> list = node.list;
		for (Node e : list) {
			str += printTree1(e);
		}
		str += ")";

		return str;
	}

	public String printTree(Node root) {
		String ans = "";

		ans = printTree1(root);
		if (ans.length() == 0) {
			return ans;
		}
		ans = ans.substring(1, ans.length() - 1);

		return ans;
	}

	public String extractrule(String word, Node root, int v) {
		Node node = findNode(word, root);
		String source = "";
		if (node != null) {
			for (int k = 0; k < v; k++) {
				node = node.father;
				if (node == null) {
					break;
				}
			}
			source += printTree(node);
		}
		return source;
	}
}