package Ansj.Split;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import FileUtil.Log;

public class TokenImpl implements IToken {
	private String str = "";
	private List<Term> list = null;

	public TokenImpl(String str) {
		list = splitTags(str);
	}

//	@Override
	public List<Term> splitTags(String str) {
		// TODO Auto-generated method stub
		this.str = str;
		return list = ToAnalysis.parse(str);
	}

	public void log() {
		// TODO Auto-generated method stub
		Log.log("ԭ��:" + str);
		Log.log("�ִ�:" + list);
	}

	public static void main(String[] args) {
		TokenImpl tl = new TokenImpl("��û���ֻ�");
		tl.log();
	}

//	@Override
	public String getStr() {
		// TODO Auto-generated method stub
		return this.str;
	}

//	@Override
	public List<Term> getList() {
		// TODO Auto-generated method stub
		return this.list;
	}
}
