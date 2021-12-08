package refreshertasks;

import java.io.*;

import java.util.ArrayList;

public class Spiraling 
{
	private String[][] arr;
	private int length;
	
	public Spiraling()
	{
		File name = new File("/Users/aadikatyal/eclipse-workspace/DS/src/refreshertasks/SpiralInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			
			String text;
			while((text = input.readLine()) != null)
			{
				length = Integer.parseInt(text);
				arr = new String[length][length];
				
				fillDashes();
				fillStars();
				printArr();
			}
		}
		catch (IOException io)
		{
			System.err.println("File ain't exist");
		}
	}
	
	public void fillDashes()
	{
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				arr[i][j] = "-";
			}
		}
	}
	
	public void fillStars()
	{
		int startRow = 0;
		int startCol = 0;
		int endRow = length - 1;
		int endCol = length - 1;
		int k = 0;
		
		while(startRow <= endRow && startCol <= endCol)
		{
			int x = 0;
			if (k > 0)
			{
				x = startCol + k - 1;
			}
			else 
			{
				x = startCol + k;
			}
					
			for (int i = x; i <= endCol; i++)
			{
				arr[startRow][i] = "*";
			}
						
			for (int i = startRow; i <= endRow; i++)
			{
				arr[i][endCol] = "*";
			}
			
			for (int i = endCol; i > startCol + k; i--)
			{
				arr[endRow][i] = "*";
			}
		
			for (int i = endRow; i > startRow + 1; i--)
			{
				arr[i][startCol + k] = "*";
			}

			startCol += 1;
			startRow += 2;
			endRow -= 2;
			endCol -= 2;
			
			k++;
												
		}		
	}
	
	public void printArr()
	{
		for(int i = 0; i < arr[0].length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		Spiraling spiraling = new Spiraling();
	}
	
}