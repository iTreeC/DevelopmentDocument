import java.io.*;

public class TextManager
{
	public static String Read(String filename)
	{
		File f = new File(filename);
	    String text="";
	    try 
	    {
	    	FileReader fs = new FileReader(f);
	    	BufferedReader br = new BufferedReader(fs);
	    	String temp;
	    	while ((temp=br.readLine())!= null)
	    	{
	    		text+=temp;
	    	}
	    	br.close();
	    }
	    catch (Exception ex) 
	    {
	    	ex.printStackTrace();
	    }
	    return text;
	}
	
	public static void Write(String filename,String text)
	{
		File f = new File(filename);
	    try 
	    {
	    	FileWriter fw = new FileWriter(f);
	    	BufferedWriter bw = new BufferedWriter(fw);	
	    	bw.write(text);
	    	bw.close();
	    	fw.close();
	    }
	    catch (Exception ex) 
	    {
	    	ex.printStackTrace();
	    } 
	}
}
