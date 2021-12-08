package class_competition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ASCIITask 
{
	public ASCIITask() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/class_competition/asciiPhoto5.txt");
		BufferedReader input = null;
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			
			String text = input.readLine();
			int windowWidth = 156;
			int index = 0;
			
			while (index + windowWidth < text.length()) 
			{
			    String print = text.substring(index, index + windowWidth);
			    System.out.println(print);
			    index+=windowWidth;
			}
				
			input.close();
		}
		catch (IOException io) 
		{
			System.err.println("No file bruh");
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		ASCIITask q1 = new ASCIITask();
	}
}
