package Trunk.Util;

public class Edge {
	public String edgename = "";
	public String depword = "";
	public String depinfo = "";
	public int depId = 0;
	public String govword = "";
	public String govinfo = "";
	public int govId = 0;

	public boolean isEqual(Edge e) {
		return this.edgename.equals(e.edgename)
				&& this.depword.matches(e.depword)
				&& this.depinfo.matches(e.depinfo)
				&& this.govword.matches(e.govword)
				&& (this.govword.equals("ROOT") || this.govinfo
						.matches(e.govinfo));
	}

	public String toString() {
		return this.edgename + " " + this.depword + " "
				+ this.depinfo + " " + this.depId + " " + this.govword
				+ " " + this.govinfo + " " + this.govId;
	}
}
