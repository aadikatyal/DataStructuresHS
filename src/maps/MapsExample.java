package maps;

import java.util.TreeMap;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsExample 
{
	public MapsExample()
	{
		String[] names = {"Federer", "Nadal", "Djokovic"};
		HashMap<String, ArrayList<Integer>> diceMap = new HashMap<>();
		
		for(int i = 0; i < 3; i++)
		{
			diceMap.put(names[i], new ArrayList<Integer>());
		}
		
		for(int j = 0; j < 100; j++)
		{
			int name = (int)(Math.random() * names.length);
			int die2 = (int)(Math.random() * 6) + 1;
			
			// .get --> gets ArrayList attached to die1 (1-6) in diceMap, .add adds new die value (die2) to that same list
			diceMap.get(names[name]).add(die2); 
		}
		
		// keySet = index
		
		Iterator<String> iterator = diceMap.keySet().iterator();
		
		while(iterator.hasNext())
		{
			String name = iterator.next();
			System.out.println(name);
		}
		
		System.out.println();
		
		// values = values attached to each index
		
		Iterator<ArrayList<Integer>> iterator2 = diceMap.values().iterator();
		
		while(iterator2.hasNext())
		{
			ArrayList<Integer> list = iterator2.next();
			System.out.println(list);
		}
		
		System.out.println();
		
		// entrySet = snapshot of everything
		
		Iterator<Map.Entry<String, ArrayList<Integer>>> iterator3 = diceMap.entrySet().iterator();
		
		while(iterator3.hasNext())
		{
			Map.Entry<String, ArrayList<Integer>> info = iterator3.next();
			System.out.println(info);
		}
	}
	
	public static void main(String[] args)
	{
		MapsExample maps = new MapsExample();
	}
}