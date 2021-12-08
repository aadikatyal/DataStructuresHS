package refreshertasks;


import java.io.*;
import java.util.ArrayList;

public class AmicableNumbers
{
	public AmicableNumbers()
	{
		File name = new File("AmicableInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text,output="";
			while( (text=input.readLine())!= null)
			{
				String[] nums = text.split(" ");
				int num1 = Integer.parseInt(nums[0]);
				int num2 = Integer.parseInt(nums[1]);
				amicable(num1, num2);
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}
	
	
	public String amicable(int num1, int num2)
	{
		ArrayList<Integer> factors1 = new ArrayList<Integer>();
		ArrayList<Integer> factors2 = new ArrayList<Integer>();
		
		//boolean amicable = false;
		
		for(int i = 0; i < num1; i++)
		{
			if(num1 % i == 0)
			{
				factors1.add(i);
			}
		}
		
		for(int i = 0; i < num1; i++)
		{
			if(num1 % i == 0)
			{
				factors2.add(i);
			}
		}
		
		int sum1 = sum(factors1);
		int sum2 = sum(factors2);
		
		int sum1Compare = 0;
		int sum2Compare = 0;
		
		for(int i = 0; i < factors1.size(); i++)
		{
			sum2Compare += factors1.get(i);
		}
		
		for(int i = 0; i < factors2.size(); i++)
		{
			sum1Compare += factors2.get(i);
		}
		
		if(sum1 == sum1Compare && sum2 == sum2Compare)
		{
			return "The numbers " + num1 + " and " + num2 + " are amicable.";
		}
		return "The numbers " + num1 + " and " + num2 + " are not amicable.";
		
	}
	
	public int sum(ArrayList<Integer> list)
	{
		int sum = 0;
		for(int i = 0; i < list.size(); i++)
		{
			sum += i;
		}
		return sum;
	}
	
	public static void main(String args[])
	{
		AmicableNumbers app = new AmicableNumbers();
	}
}