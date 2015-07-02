package Ansj.Split;

public class TokenFactory {
	public static IToken getInstance(String str) {
		return new TokenImpl(str);
	}
}
