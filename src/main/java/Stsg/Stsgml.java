package Stsg;

import java.io.IOException;
import java.util.ArrayList;
import FileUtil.FileUtil;
import Stsg.Util.*;

public class Stsgml {
	public ArrayList<String> rulepairs = new ArrayList<String>();
	public ArrayList<Rule> rulesTreeList = new ArrayList<Rule>();

	public void ImportRules(String rulefile) {
		try {
			this.rulesTreeList.clear();
			this.rulepairs = FileUtil.readFile(rulefile, "gbk");
			for (int i = 0; i < rulepairs.size(); i += 2) {
				Node source = RuleParser.createTree(rulepairs.get(i));
				Node target = RuleParser.createTree(rulepairs.get(i + 1));
				Rule r = new Rule();
				r.source = source;
				r.target = target;
				r.sr = rulepairs.get(i);
				r.tr = rulepairs.get(i+1);
				rulesTreeList.add(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
