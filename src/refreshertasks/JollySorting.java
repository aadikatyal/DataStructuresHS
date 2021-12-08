package refreshertasks;


import java.io.*;
import java.util.ArrayList;

public class JollySorting
{
	private int arr[];
	private boolean isAscending = true;
	public JollySorting()
	{
		File name = new File("/Users/aadikatyal/Downloads/JollyInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			
			String text;
			while((text = input.readLine()) != null)
			{
				String strArr[] = text.split(" ");
				arr = new int[strArr.length];
				for(int i = 0; i < strArr.length; i++)
				{
					arr[i] = Integer.parseInt(strArr[i]);
				}
				//sort();
				sort2();
				printArr();
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}
	
	public boolean checkAscending(int pos)
	{
		return pos % 2 == 0;
	}
	
	public void sort()
	{
		for(int i = 0; i < arr.length - 1; i++)
		{
			int j = i;
			while(j >= 0 && arr[j] > arr[j + 1])
			{
				swap(j, j + 1);
				j--;
			}
		}
	}
	
	public void sort2()
	{
		if(arr.length == 1)
		{
			return;
		}
		
		for(int i = 0; i < arr.length - 1; i++)
		{
			boolean isAscending = checkAscending(i);
			if(isAscending)
			{
				if(arr[i] > arr[i + 1])
				{
					swap(i, i + 1);
				}
			}
			
			if(!isAscending)
			{
				if(arr[i] < arr[i + 1])
				{
					swap(i, i + 1);
				}
			}
		}
	}
	
	public void swap(int pos1, int pos2)
	{
		int temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
	
	public void printArr()
	{
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String args[])
	{
		JollySorting ln = new JollySorting();
	}
}

