

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * ��ϰʹ�ü򵥵ķִʷ�ʽ
 * @author Fei
 *
 */
public class TestAnalyzer {
	@Test
	public void test() {
		try {
			// ʵ����IKAnalyzer�ִ���
			Analyzer analyzer = new IKAnalyzer();
			// ��������
			String text = "����iTree�ĳ�Ա����ҹ�ͬ����";
			// ������
			StringReader reader = new StringReader(text);
			// �����ִ���
			TokenStream ts = analyzer.tokenStream(text, reader);
			Token t = ts.next(new Token());
			// ��ӡ
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
