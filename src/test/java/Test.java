// Created by ZG on 15/7/2.
//

import Extractors.Extractors;

import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;

public class Test {
    public static void main(String[] args) throws IOException{

//		Resource res = new Resource();
//		String userwords = res.getResource("userwords.txt");
//
//		Resource res1 = new Resource();
//		String chinesePCFG = res1.getResource("chinesePCFG.ser");
//
//		Function.setModel(chinesePCFG,
//				userwords);

        Extractors.setModel("src/main/resources/chinesePCFG.ser",
                "src/main/resources/userwords.txt");

        // Function.learn("src/_IKeyven/sentence1.txt",
        // "src/_IKeyven/rules.txt");
        ArrayList<String> list = new ArrayList<String>();
        try {
            list = FileUtil.FileUtil
                    .readFile("src/main/resources/hankcs.txt", "utf8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String str : list) {

            // print origin strings
            System.out.println(str);

            // print strings processed with sentence compression
            System.out.println(Extractors.compress(str, "src/main/resources/rules.txt",
                    false));

            // print strings processed with dependence
            System.out.println(Extractors.trunkhankey(str,
                    "src/main/resources/deprules.txt", false));
            System.out.println();
        }
        System.out.println(Extractors.trunkhankey("这个项目由我改善",
                "src/main/resources/deprules.txt", false));
    }
}
