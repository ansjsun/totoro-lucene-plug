import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ansj.sun.lucene.TotoroAnalyzer;

public class QueryTest {
	public static void main(String[] args) throws ParseException, IOException {
		Analyzer analyzer = new TotoroAnalyzer();
		QueryParser tq = new QueryParser(Version.LUCENE_32, "body", analyzer);
		Query parse = tq.parse("追寻理想、心的地图——海贼王文章音乐");
		System.out.println(parse);
		
		
		File testIndex = new File("/Users/ansj/Documents/temp/data/blog/0");
		FSDirectory open = FSDirectory.open(testIndex);
		
		
		IndexSearcher searcher = new IndexSearcher(open);
		
		
		
		TopDocs hits = searcher.search(parse, 20);
		List<Explanation> all = new ArrayList<Explanation>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < hits.scoreDocs.length; i++) {
			int doc = hits.scoreDocs[i].doc;
			Document document = searcher.doc(doc);
			 Explanation explain = searcher.explain(parse, doc) ;
//			 System.out.println(explain);
			// all.add(explain) ;
			System.out.println(hits.scoreDocs[i].score + ":" + document.get("path"));
		}
		System.out.println(System.currentTimeMillis() - start);
		// sortResult(all) ;
		System.out.println("共查出记录" + hits.totalHits + "条");
	}
}
