

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * 练习使用简单的分词方式
 * @author Fei
 *
 */
public class TestAnalyzer {
	@Test
	public void test() {
		try {
			// 实例化IKAnalyzer分词器
			Analyzer analyzer = new IKAnalyzer();
			// 检索内容
			String text = "我是iTree的成员，大家共同进步";
			// 输入流
			StringReader reader = new StringReader(text);
			// 创建分词流
			TokenStream ts = analyzer.tokenStream(text, reader);
			Token t = ts.next(new Token());
			// 打印
			while (t != null) {
				String s = t.term();
				System.out.println(s);
				t = ts.next(new Token());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
