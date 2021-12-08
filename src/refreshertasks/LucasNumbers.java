package refreshertasks;


import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class LucasNumbers
{
	private ArrayList<BigInteger> list;
	public LucasNumbers()
	{
		list = new ArrayList<BigInteger>();
		File name = new File("/Users/aadikatyal/eclipse-workspace/DS/src/refreshertasks/LucasInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			
			String text;
			addToList();
			while((text = input.readLine()) != null)
			{
				System.out.println(list.get(Integer.parseInt(text)));
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}

	public void addToList()
	{
		list = new ArrayList<BigInteger>();
		list.add(new BigInteger("2"));
		list.add(new BigInteger("1"));
		for(int i = 1; i < 200; i++)
		{
			BigInteger nextNum = (list.get(i - 1)).add(list.get(i));
			list.add(nextNum);
		}
	}

	public static void main(String args[])
	{
		LucasNumbers ln = new LucasNumbers();
	}
}

