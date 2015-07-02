package Trunk;

import java.util.*;
import java.util.concurrent.Executors;

import Trunk.Util.Edge;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.international.pennchinese.ChineseTreebankLanguagePack;

import FileUtil.*;
import Extractors.Extractors;
import Extractors.KTree;

public class DepTree {
	public Collection<TypedDependency> tdls = null;
	public List<Edge> edges = null;
	public String str = null;

	public DepTree(String str) {
		this.str = str;
		this.tdls = dependencyTree(str);
		this.edges = edgelist(this.tdls);
	}

	public Collection<TypedDependency> dependencyTree(String str) {
		Ansj.Split.IToken sw = Ansj.Split.TokenFactory.getInstance(str);
		Tree ptree = KTree.getStanfordTree(sw.getList());
		TreebankLanguagePack tlp = new ChineseTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(ptree);
		Collection<TypedDependency> ptdls = gs.typedDependencies();
		return this.tdls = ptdls;
	}

	public List<Edge> edgelist(Collection<TypedDependency> tdls) {
		ArrayList<Edge> list = new ArrayList<Edge>();

		for (TypedDependency v : tdls) {
			Edge e = new Edge();
			e.edgename = v.reln().getShortName();
			e.depword = v.dep().value();
			e.depinfo = v.dep().tag();
			e.depId = v.dep().index();
			e.govword = v.gov().value();
			e.govinfo = v.gov().tag();
			e.govId = v.gov().index();
			list.add(e);
		}

		return list;
	}

	public String infoTdls() {
		String str = "";
		for (TypedDependency v : tdls) {
			str += infoTypedDependency(v) + "\n";
		}
		return str;
	}

	public String infoTypedDependency(TypedDependency v) {
		String str = "";
		str = "[name:" + v.reln().getShortName() + " dep:" + v.dep().toString()
				+ "-" + v.dep().index() + " gov:" + v.gov().toString() + "-"
				+ v.gov().index() + "]";
		return str;
	}

	public String infoEdges() {
		String str = "";

		for (Edge e : this.edges) {
			str += e.toString() + "\n";
		}

		return str;
	}

	public static void main(String[] args) {
		Extractors.setModel("lib/chinesePCFG.ser.gz",
                "src/_IKeyven/userwords.txt");
        DepTree test = new DepTree("����������û�У�");
		Log.log(test.infoTdls());
		Log.log(test.infoEdges());
	}
}
