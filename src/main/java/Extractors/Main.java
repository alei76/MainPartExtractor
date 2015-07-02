package Extractors;
// Created by ZG on 15/7/3.
// 

import Extractors.Extractors;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static String main(String string) throws IOException{
        Extractors.setModel("src/main/resources/chinesePCFG.ser",
                "src/main/resources/userwords.txt");
        String result = Extractors.trunkhankey(string,
                "src/main/resources/deprules.txt", false);
        System.out.println(result);
        return result;
    }
}
