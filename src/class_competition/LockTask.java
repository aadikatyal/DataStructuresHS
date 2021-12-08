package class_competition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LockTask
{
	private String nums;
	public LockTask() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/class_competition/lock.txt");
		BufferedReader input = null;
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			String text = "";
			
			while ((text = input.readLine()) != null)
			{				
				for(int i = 0; i < text.length(); i++)
				{
					char num = text.charAt(i);
					if(num == '0' || num == '1' || num == '2' || num == '3' || num == '4' || num == '5' || num == '6' || num == '7' || num == '8' || num == '9')
					{
						nums += text.charAt(i);
					}
				}
			}
			
			for(int i = 0; i < nums.length() - 2; i++)
			{
				int num = Integer.valueOf(nums.substring(0, i + 2));
				
				if(num >= 0 && num <= 35)
				{
					System.out.println(num);
				}
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
		LockTask q1 = new LockTask();
	}
}
