package Ansj.Split;

import java.util.List;

import org.ansj.domain.Term;

public interface IToken {	
	public List<Term> splitTags(String str);
	public String getStr();
	public List<Term> getList();
}
