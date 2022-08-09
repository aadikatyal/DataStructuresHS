package maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

public class MapsTask2Runner
{
	public static void main(String[] args) throws Exception
	{
		TreeSet<Citizen> citizens = new TreeSet<Citizen>();
		ArrayList<Citizen> citizensList = new ArrayList<Citizen>();
				
		File file = new File("/Users/aadikatyal/eclipse-workspace/DataStructures/src/maps/census.txt");
		try
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));			
			String fileRecord = "";
			
			while((fileRecord = bufferedReader.readLine()) != null)
			{
				boolean extra = false;
				
				while(fileRecord.length() == 0)
				{
					fileRecord = bufferedReader.readLine();
					extra = true;
				}
				
				if(extra)
				{
					for(int i = 0; i < 7; i++)
					{
						fileRecord = bufferedReader.readLine();
					}
					extra = false;
				}
				
				Citizen citizen = new Citizen(fileRecord);
				
				citizens.add(citizen);
				citizensList.add(citizen);
			}
		}
		catch(IOException io)
		{
			System.err.print("No file bruh");
		}
		
		// Note: Ages/property values containing odd characters marked as -1.0 (Double)
		
		streetKey(citizens);
		System.out.println();
		birthplaceKey(citizens);
		System.out.println();
		motherTongueKey(citizens);
		System.out.println();
		occupationKey(citizens);
		System.out.println();
		genderKey(citizens);
		System.out.println();
		rentKey(citizens);
		System.out.println();
		custom(citizens);
	}

	public static void streetKey(TreeSet<Citizen> citizens) 
	{
		TreeMap<String, TreeSet<Citizen>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			TreeSet<Citizen> streetMembers = map.get(citizen.getStreet());
			
			if(streetMembers == null)
			{
				streetMembers = new TreeSet<Citizen>();
				streetMembers.add(citizen);
				map.put(citizen.getStreet(), streetMembers);
			}
			else
			{
				streetMembers.add(citizen);
			}
		}
		
		Iterator<Map.Entry<String, TreeSet<Citizen>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, TreeSet<Citizen>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Street Address");
			}
			System.out.println(info);
		}
	}
	
	public static void birthplaceKey(TreeSet<Citizen> citizens) 
	{
		TreeMap<String, PriorityQueue<Double>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		int pennsylvaniaCounter = 0;
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			PriorityQueue<Double> birthplaceCitizens = map.get(citizen.getBirthplace());
			
			if(citizen.getBirthplace().equals("Pennsylvania"))
			{
				pennsylvaniaCounter++;
			}
			else if(birthplaceCitizens == null)
			{
				birthplaceCitizens = new PriorityQueue<Double>();
				birthplaceCitizens.add(citizen.getAge());
				map.put(citizen.getBirthplace(), birthplaceCitizens);
			}
			else
			{
				birthplaceCitizens.add(citizen.getAge());
			}
		}
		
		Iterator<Map.Entry<String, PriorityQueue<Double>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, PriorityQueue<Double>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Birthplace");
			}
			System.out.println(info);
		}
		
		System.out.println("# of Pennsylvania Citizens: " + pennsylvaniaCounter);
	}
	
	public static void motherTongueKey(TreeSet<Citizen> citizens)
	{
		TreeMap<String, ArrayList<String>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			String fullName = citizen.getFirstName() + " " + citizen.getLastName();
			ArrayList<String> list = map.get(citizen.getMotherTongue());
			
			if(list == null)
			{
				list = new ArrayList<String>();
			}

			list.add(fullName);
			map.put(citizen.getMotherTongue(), list);
		}
		
		Iterator<Map.Entry<String, ArrayList<String>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, ArrayList<String>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Mother Tongue");
			}
			System.out.println(info.getKey() + ": " + info.getValue().size());
		}
	}
	
	public static void occupationKey(TreeSet<Citizen> citizens)
	{
		TreeMap<String, HashSet<String>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			String fatherBirthplace = citizen.getFatherBirthplace();
			HashSet<String> set = map.get(citizen.getOccupation());
			
			if(set == null)
			{
				set = new HashSet<String>();
			}
			
			set.add(fatherBirthplace);
			map.put(citizen.getOccupation(), set);
		}
		
		Iterator<Map.Entry<String, HashSet<String>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, HashSet<String>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Occupation");
			}
			System.out.println(info.getKey() + ": " + info.getValue());
		}
	}
	
	public static void genderKey(TreeSet<Citizen> citizens) 
	{
		TreeMap<String, HashSet<String>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			String remarks = citizen.getTranscriberRemarks();
			HashSet<String> set = map.get(citizen.getGender());
			
			if(set == null)
			{
				set = new HashSet<String>();
			}
			
			set.add(remarks);
			map.put(citizen.getGender(), set);
		}
		
		Iterator<Map.Entry<String, HashSet<String>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, HashSet<String>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Gender");
			}
			System.out.println(info.getKey() + ": " + info.getValue());
		}
	}
	
	public static void rentKey(TreeSet<Citizen> citizens) 
	{
		TreeMap<String, TreeSet<Double>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			TreeSet<Double> rentCitizens = map.get(citizen.getOwnership());
			
			if(rentCitizens == null)
			{
				rentCitizens = new TreeSet<Double>();
			}
			
			rentCitizens.add(citizen.getPropertyValue());
			map.put(citizen.getOwnership(), rentCitizens);
		}
		
		Iterator<Map.Entry<String, TreeSet<Double>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, TreeSet<Double>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Ownership");
			}
			System.out.println(info);
		}
	}
	
	// Fills new TreeMap (with marital status as key) with TreeSet of all Citizen ages (Double) who are each of the respective marital statuses
	public static void custom(TreeSet<Citizen> citizens) 
	{
		TreeMap<String, TreeSet<Double>> map = new TreeMap<>();
		Iterator<Citizen> iterator = citizens.iterator();
		
		while(iterator.hasNext())
		{
			Citizen citizen = iterator.next();
			TreeSet<Double> agesSet = map.get(citizen.getMaritalStatus());
			
			if(agesSet == null)
			{
				agesSet = new TreeSet<Double>();
			}
			
			agesSet.add(citizen.getAge());
			map.put(citizen.getMaritalStatus(), agesSet);
		}
		
		Iterator<Map.Entry<String, TreeSet<Double>>> iterator2 = map.entrySet().iterator();
		
		while(iterator2.hasNext())
		{
			Map.Entry<String, TreeSet<Double>> info = iterator2.next();
			if(info.getKey().length() == 0)
			{
				System.out.print("No Marital Status");
			}
			System.out.println(info);
		}
	}
}