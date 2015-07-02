package Trunk;

import java.io.IOException;
import java.util.*;

import FileUtil.*;

public class DepRules {
	public List<Trunk.Util.Rule> rules = null;

	public List<Trunk.Util.Rule> reader(String deprulestxt) {
        this.rules = new ArrayList<Trunk.Util.Rule>();
		
		try {
			List<String> list = FileUtil.readFile(deprulestxt, "gbk");
			Trunk.Util.Rule r = null;
			for(String str : list) {
				if(str.matches("begin.*")) {
					r = new Trunk.Util.Rule();
					this.rules.add(r);
					continue;
				}
				// nsubj .* .* 4 �� VE 2
                Trunk.Util.Edge e = new Trunk.Util.Edge();
				String[] temp = str.split(" ");
				e.edgename = temp[0];
				e.depword = temp[1];
				e.depinfo = temp[2];
				e.depId = Integer.valueOf(temp[3]);
				e.govword = temp[4];
				e.govinfo = temp[5];
				e.govId = Integer.valueOf(temp[6]);
				r.add(e);
			}
		} catch (IOException e) {
			Log.log("�����������ȡʧ��:");
			Log.log(e.getMessage());
			e.printStackTrace();
		}
		
		return this.rules;
	}
	
	public static void main(String[] args) {
		DepRules test = new DepRules();
		test.reader("src/_IKeyven/deprules.txt");
		for(Trunk.Util.Rule r : test.rules) {
			System.out.println(r);
		}
	}
}
