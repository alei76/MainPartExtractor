package Stsg.Util;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public Attribute att = new Attribute();
	public List<Node> list = new ArrayList<Node>();
	public Node father = null;

	public void addChild(Node node) {
		list.add(node);
	}

	public void removeChild(Node node) {
		list.remove(node);
	}

	public void removeAllChildren() {
		list.clear();
	}

	public void replace(Node node) {
		this.att.gender = node.att.gender;
		this.att.text = node.att.text;
		this.list = node.list;
	}
}
