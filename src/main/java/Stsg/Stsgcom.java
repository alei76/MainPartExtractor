package Stsg;

import java.util.ArrayList;

import com.sun.org.apache.xml.internal.security.Init;
import org.ansj.splitWord.analysis.ToAnalysis;
import Stsg.Util.*;
import FileUtil.Log;
import Init.StanfordParser;

public class Stsgcom {
	public String compression(String str, String rulefile, boolean log) {
		// ��ʼ����
		ParseTree pt = new ParseTree();
		Ansj.Split.IToken sw = Ansj.Split.TokenFactory.getInstance(str);
		Node sroot = pt.createTree(sw.getList()); // ԭ�佨��
		if (log) {
			Log.log("�﷨��:" + pt.parseTree);
		}
		// ȡ��ԭ�佨���е����н��Node
		TreeParser facility = new TreeParser(sroot);
		ArrayList<Node> allNodes = facility.list;
		// ��ʼ�ȶԹ���
		Stsgml sm = new Stsgml();
		int cnt = 0;
		while (true) {
			boolean canExit = true;
			for (int i = 0; i < allNodes.size() - sw.getList().size(); i++) {
				// �������
				sm.rulesTreeList.clear();
				sm.ImportRules(rulefile);
				ArrayList<Rule> rlist = sm.rulesTreeList;
				for (Rule r : rlist) {
					if (RuleParser.isEqualTree(allNodes.get(i), r.source)) {
						facility.Visit(allNodes.get(i), r.source);
						facility.Visit_Target(r.target);
						allNodes.get(i).replace(r.target);
						canExit = false;
						if(log) {
							Log.log("����:");
							Log.log(r.sr);
							Log.log(r.tr);
						}
						break;
					}
				}
			}
			if (canExit) {
				break;
			}
			cnt++;
			if (cnt >= 100) {
				Log.log("����ʧ��!");
				Log.log("����:" + str);
				break;
			}
		}
		OutputResult outans = new OutputResult();
		outans.visit(sroot);
		return outans.toString();
	}

	public static void main(String[] args) {
		ToAnalysis.parse("");
		Stsgcom test = new Stsgcom();
		StanfordParser.getInstance().setModel("lib/chinesePCFG.ser.gz");
		System.out.println(test.compression("��2�����ҵĲ�ƥ��", "F:/2.txt", true));
	}
}
