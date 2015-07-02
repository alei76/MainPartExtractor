package Init;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import FileUtil.Log;

public class StanfordParser {
	public LexicalizedParser lp = null;
	public String model = "";

	private StanfordParser() {
	}

	public void setModel(String model) {
		this.lp = LexicalizedParser.loadModel(model);
		Log.log("�����ķ�:" + model);
	}

	private static class StanfordParserFactory {
		private static StanfordParser instance = new StanfordParser();
	}

	public static StanfordParser getInstance() {
		return StanfordParserFactory.instance;
	}

	public Object readResolve() {
		return getInstance();
	}
}
