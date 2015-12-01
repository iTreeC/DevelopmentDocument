import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
/**
* @author linly
* */

public class IKAnalyzerDemo
{
	public static void main(String[] args)
	{
		String filepathfrom=System.getProperty("user.dir")+"\\from\\text.txt";
		String filepathto=System.getProperty("user.dir")+"\\to\\resulttest.txt";
		
	    String text=TextManager.Read(filepathfrom);	
	    List<WordsCounter> wordsCountList=new ArrayList<WordsCounter>();
	    List<String> wordsList=new ArrayList<String>();
	    //�ִʲ��ִ���
		System.out.println(text); 
		IKSegmentation ikSeg = new IKSegmentation(new StringReader(text) , false);
		try 
		{
			Lexeme l = null;
			while( (l = ikSeg.next()) != null)
			{
				System.out.println(l);	
				wordsList.add(l.getLexemeText());
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("***************"); 
		
		//ͳ�ƴʻ�Ƶ��
		for(String word:wordsList)
		{
			boolean match=false;
			for(int i=0;i<wordsCountList.size();i++)
			{
				if(word.equals(wordsCountList.get(i).text))
				{
					wordsCountList.get(i).count++;
					match=true;
					break;
				}
			}
			if(match==false)
			{
				wordsCountList.add(new WordsCounter(word,1));
			}
		}
		
		
		//��ͳ�ƽ��д���ı��ĵ�
		String resultString="";
		for(WordsCounter wordCounter:wordsCountList)
		{
			resultString+=wordCounter.text+":"+wordCounter.count+"\r\n";
			System.out.println(wordCounter.text+":"+wordCounter.count);
		}	
		TextManager.Write(filepathto,resultString);
	}
}
