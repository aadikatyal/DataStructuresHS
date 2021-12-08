package stacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class ReverseStringStacks 
{
	private String str;
	private Stack<String> stack;

	public ReverseStringStacks()
	{	
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/stacks/StrInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));
			
			String text;
			while((text = input.readLine()) != null)
			{
				stack = new Stack<String>();
				str = String.valueOf(text);
				
				addLettersToStack();
				popStack();
				System.out.println();
			}
		}
		catch (IOException io)
		{
			System.err.println("No file bruh");
		}
	}
	
	public void addLettersToStack()
	{
		for(int i = 0; i < str.length(); i++)
		{
			stack.push(str.substring(i, i + 1));
		}
	}
	
	public void popStack() 
	{
		System.out.print(str + ": ");
		while(!stack.isEmpty())
		{
			System.out.print(stack.pop());
		}
	}

	public static void main(String[] args)
	{
		ReverseStringStacks rss = new ReverseStringStacks();
	}
}