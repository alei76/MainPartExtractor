package Extractors;
// Created by ZG on 15/7/2.
//

import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;

import Stsg.Stsgcom;
import Stsg.Learner;
import Trunk.DepRules;
import Trunk.DepTree;
import Trunk.Gotrunk;
import FileUtil.*;

import java.io.IOException;

public class Extractors {
    public static String compress(String str, String rulefile, boolean log) {
        if (log) {
            Log.log("_IKeyven.Function.compress");
        }
        Stsgcom sg = new Stsgcom();
        String temp = str, result = str;
        while (true) {
            temp = result;
            result = sg.compression(result, rulefile, log);
            if (temp.equals(result)) {
                break;
            }
        }
        ;
        return result;
    }

    public static void learn(String corpustxt, String ruletxt) {
        Log.log("_IKeyven.Function.learn");
        Learner learner = new Learner();
        learner.learnRule(corpustxt, ruletxt);
    }

    public static void setModel(String model, String userwordstxt) {
        insertuserwords(userwordstxt);
        ToAnalysis.parse("");
        Log.log("_IKeyven.Function.setModel");
        Init.StanfordParser.getInstance().setModel(model);
    }

    public static void insertuserwords(String userwordstxt) {
        if (userwordstxt.trim().length() == 0) {
            return;
        }
        Log.log("_IKeyven.Function.insertuserwords");
        try {
            java.util.ArrayList<String> list = FileUtil.readFile(
                    userwordstxt, "utf8");
//            java.util.ArrayList<String> list = userwordstxt;
            for (String str : list) {
                UserDefineLibrary.insertWord(str, "user's", 1000);
            }
        } catch (IOException e) {
            Log.log("load userwordstxt faield!");
            Log.log(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String trunkhankey(String str, String depruletxt, boolean log) {
        if (log) {
            Log.log("_IKeyven.Function.trunkhankey");
        }
        DepRules rulefile = new DepRules();
        rulefile.reader(depruletxt);
        DepTree tree = new DepTree(str);

//		System.out.print("tree.infoEdges:");
//		System.out.println(tree.infoEdges());

        String result = Gotrunk.gotrunk(tree, rulefile, log);

//		System.out.println(result);

        result = Trunk.Keyven.keyven(result, tree);
        return result;
    }
}
