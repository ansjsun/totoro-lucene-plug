package ansj.sun.lucene;

import java.io.Reader;

import org.ansj.splitWord.analysis.FilterAnalysis;
import org.ansj.splitWord.analysis.UserDefinedAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

/**
 * totoro 分词lucene实现
 * @author ansj
 *
 */
public class TotoroAnalyzer extends Analyzer{

	@Override
	public TokenStream tokenStream(String arg0, Reader reader) {
		// TODO Auto-generated method stub
		TokenStream ts = new TotoroTokenizer(reader , new FilterAnalysis(new UserDefinedAnalysis(reader, true)));
		return ts;
	}

}
