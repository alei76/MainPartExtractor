package Stsg;
// Created by ZG on 15/7/2.
// 

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.ansj.splitWord.analysis.ToAnalysis;
import Stsg.Util.*;
import FileUtil.Log;
import FileUtil.FileUtil;

public class Learner {
    public ArrayList<String> rulepairs = new ArrayList<String>();
    public ArrayList<Rule> rulesTreeList = new ArrayList<Rule>();
    public HashSet<String> util1 = new HashSet<String>();

    public void learnRule(String corpusfile, String rulefile) {
        if (!(new java.io.File(corpusfile)).exists()) {
            Log.log("�����ʾ�Ŀ¼������");
            return;
        }
        ToAnalysis.parse("");
        Log.log("ƽ�����Ͽ�:" + corpusfile);
        FileInputCorpus(corpusfile, rulefile);
        Log.log("���ɹ�����:" + rulefile);
    }

    public void FileInputCorpus(String corpusfile, String rulefile) {
        try {
            ArrayList<String> corpus = FileUtil.readFile(corpusfile, "gbk");
            if (corpus.size() % 2 != 0) {
                Log.log("ƽ�����Ͽ�û�гɶ�");
                return;
            }
            double[] p = { 0.05, 0.1, 0.25, 0.5, 0.75, 1 };
            int in = 0;
            for (int i = 0; i < corpus.size(); i += 2) {
                Extracter(corpus.get(i), corpus.get(i + 1));
                int percent = (int) Math.ceil(p[in] * corpus.size());
                if (percent <= i) {
                    Log.log(String.format("����:%.2f ", p[in]));
                    in++;
                }
            }
            FileUtil.writeFile(rulefile, this.rulepairs, "gbk");
            Log.log("�ܹ���" + this.rulepairs.size() / 2 + "������");
        } catch (IOException e) {
            e.printStackTrace();
            Log.log(e.getMessage());
        }
    }

    public void Extracter(String s, String t) {
        ParseTree parse = new ParseTree();
        Ansj.Split.IToken sw = Ansj.Split.TokenFactory.getInstance(s);
        Node sroot = parse.createTree(sw.getList()); //
        Ansj.Split.IToken tw = Ansj.Split.TokenFactory.getInstance(t);
        Node troot = parse.createTree(tw.getList());

        for (int i = 0; i < tw.getList().size(); i++) {
            int sh = 0, th = 0;
            for (int k = 1; k <= 3; k++) {
                boolean flag = true;
                if (k == 1) { //
                    sh = 1;
                    th = 1;
                } else if (k == 2) {
                    sh = 2;
                    th = 1;
                } else if (k == 3) {
                    sh = 2;
                    th = 2;
                }
                //
                String s1 = parse.extractrule(tw.getList().get(i).getName(),
                        sroot, sh);
                String t1 = parse.extractrule(tw.getList().get(i).getName(),
                        troot, th);
                //
                //
                int id = 1;
                for (int j = 0; j < tw.getList().size(); j++) {
                    String temp = tw.getList().get(j).getName();
                    if (s1.contains(temp) && t1.contains(temp)) {
                        flag = false;
                        break;
                    }
                    if (t1.contains(temp) && s1.contains(temp)) {
                        flag = false;
                        break;
                    }
                    s1 = s1.replace(temp, "#" + (id));
                    t1 = t1.replace(temp, "#" + (id++));
                }
                //
                if (flag
                        && !s1.equals(t1)
                        && (s1.charAt(0) == t1.charAt(0) && s1.charAt(1) == t1
                        .charAt(1))) {
                    if (s1.contains("ROOT(") || t1.contains("ROOT(")) {
                        // doNothing
                    } else {
                        for (int jj = 0; jj < sw.getList().size(); jj++) {
                            s1 = s1.replace(sw.getList().get(jj).getName(),
                                    "#0");
                        }

                        String rules = "", rulet = "";
                        rules = s1.trim();
                        rules = rules.replace("(", " (");
                        rules = rules.replace(":", " ");
                        rulet = t1.trim();
                        rulet = rulet.replace("(", " (");
                        rulet = rulet.replace(":", " ");
                        if (!util1.contains(rules)
                                && rules.length() != rulet.length()) {
                            Node ruleS = RuleParser.createTree(rules);
                            Node ruleT = RuleParser.createTree(rulet);
                            if (!RuleParser.isEqualTree(ruleS, ruleT)) {
                                this.rulepairs.add(rules);
                                this.rulepairs.add(rulet);
                                util1.add(rules);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Learner learner = new Learner();
        Init.StanfordParser.getInstance().setModel("lib/chinesePCFG.ser.gz");
        learner.learnRule("F:/1.txt", "F:/2.txt");
    }
}