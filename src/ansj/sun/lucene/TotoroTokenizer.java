package ansj.sun.lucene;

import java.io.IOException;
import java.io.Reader;

import org.ansj.domain.Term;
import org.ansj.splitWord.Analysis;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class TotoroTokenizer extends Tokenizer {

	Analysis analysis = null;

	private CharTermAttribute termAtt;
	private OffsetAttribute offsetAtt;
	private TypeAttribute typeAtt;

	public TotoroTokenizer(Reader input, Analysis analysis) {
		super(input);
		termAtt = (CharTermAttribute) addAttribute(CharTermAttribute.class);
		offsetAtt = (OffsetAttribute) addAttribute(OffsetAttribute.class);
		typeAtt = (TypeAttribute) addAttribute(TypeAttribute.class);
		this.analysis = analysis;
	}

	public static final String TYPE_WORD = "word";

	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		clearAttributes();
		Term term = analysis.next();
		if (term != null) {
			termAtt.copyBuffer(term.getName().toCharArray(), 0, term.getName().length());
			offsetAtt.setOffset(term.getOffe(), term.getOffe() + term.getName().length());
			typeAtt.setType(TYPE_WORD);
			return true;
		} else {
			end();
			return false;
		}
	}

}
