package class_competition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ColorTask
{
	private ArrayList<Integer> nums;
	public ColorTask() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/class_competition/input1.txt");
		BufferedReader input = null;
		
		nums = new ArrayList<Integer>();
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			String text = "";
			
			int index = 0;
			
			while ((text = input.readLine()) != null)
			{
				while(index < text.length())
				{
					int num = Integer.valueOf(text.substring(index, index + 3));
					index++;
					System.out.println(num);
					if(num >= 0 && num <= 255)
					{
						nums.add(num);
					}
				}
			}
			
			printList();
			
			input.close();
		}
		catch (IOException io) 
		{
			System.err.println("No file bruh");
		}
	}
	
	public void printList()
	{
		System.out.println("\nMethod:");
		for(Integer num: nums)
		{
			System.out.println(num);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		ColorTask q1 = new ColorTask();
	}
}
