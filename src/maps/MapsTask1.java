package maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class MapsTask1 
{
	/* Write a program that will read in Strings worth of text from a text file and store the letters 
	as the keys of a HashMap/TreeMap and the frequencies of each letter as the values for each key. 
	
	Note: I did SEPARATE keys for lower/upper case letters
	
	*/
	
	public MapsTask1() throws IOException
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		
		File file = new File("/Users/aadikatyal/eclipse-workspace/DataStructures/src/maps/savage.txt");
		BufferedReader reader = null;
		
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String text = "";
			
			HashSet<String> set = new HashSet<String>();
			
			String arr[] = null;
			
			while((text = reader.readLine()) != null)
			{
				String characters[] = text.split("");
				
				for(int i = 0; i < characters.length; i++)
				{
					arr = characters;
					
					if(strictlyLetters(characters[i]))
					{
						set.add(characters[i]);
					}
				}
			}
			
			HashMap<String, Integer> map = new HashMap<>();
			
			for(String letter: set)
			{
				int counter = 0;
				
				for(int i = 0; i < arr.length; i++)
				{
					if(letter.equals(arr[i]))
					{
						counter++;
					}
					map.put(letter, counter);
				}
			}
			
			Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
			
			while(iterator.hasNext())
			{
				System.out.println(iterator.next());
			}
		}
		catch(IOException e)
		{
			System.err.print("No file bruh");
		}
		finally
		{
			reader.close();
		}
	}
	
	public boolean strictlyLetters(String character)
	{
		return Character.isLetter(character.charAt(0));
	}
	
	public static void main(String[] args) throws IOException
	{
		MapsTask1 task1 = new MapsTask1();
	}
}