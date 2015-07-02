package Extractors;
// Created by ZG on 15/7/2.
// 

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;

import FileUtil.Log;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;

public class KTree {
    public static Tree getStanfordTree(List<Term> list) {
        List<TaggedWord> wordList = new ArrayList<TaggedWord>();
        for (Term tmp : list) {
            wordList.add(new TaggedWord(tmp.getName().trim(), null));
        }
        LexicalizedParser lp = Init.StanfordParser.getInstance().lp;
        if (lp == null) {
            Log.log("��ǰû�������ķ�!");
            return null;
        }
        Tree parse = (Tree) lp.parse(wordList);
        return parse;
    }
}
