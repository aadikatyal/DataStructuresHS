package stacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class DecBinStacks 
{
	private int num;
	private Stack<Integer> stack;

	public DecBinStacks()
	{		
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/stacks/Stacks.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));
			
			String text;
			while((text = input.readLine()) != null)
			{
				stack = new Stack<Integer>();
				num = Integer.valueOf(text);
				
				addBin();
				popStack();
				System.out.println();
			}
		}
		catch (IOException io)
		{
			System.err.println("No file bruh");
		}
	}
	
	public void addBin()
	{
		int x = num;
		
		if(x == 0)
		{
			stack.push(0);
		}
		
		while(x != 0)
		{
			stack.push(x % 2);
			x /= 2;
		}
	}
	
	public void popStack()
	{	
		System.out.print(num + ":\t");
		while(!stack.isEmpty())
		{
			System.out.print(stack.pop() + " ");
		}
	}

	public static void main(String[] args)
	{
		DecBinStacks dbc = new DecBinStacks();
	}
}