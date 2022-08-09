package sets;

import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;

public class HashTree1 
{
	private ArrayList<TreeSet<Integer>> list = new ArrayList<TreeSet<Integer>>();
	private TreeSet<Integer> set1;
	private TreeSet<Integer> set2;
	
	public HashTree1()
	{
		addToList();
		printList();
		
		set1 = list.get(0);
		set2 = list.get(1);
		
		intersection();
		union();
		evenIntersection();
		evenUnion();
	}
	
	public void addToList()
	{
		for(int i = 0; i < (int)(Math.random() * 11) + 2; i++)
		{
			TreeSet<Integer> set = new TreeSet<Integer>();
			
			while(set.size() < 10)
			{
				set.add((int)(Math.random() * 30) + 1);
			}
			list.add(set);
		}
	}
	
	public void printList()
	{
		int counter = 1;
		for(TreeSet<Integer> i : list)
		{
			System.out.println("Set #" + counter + ": " + i);
			counter++;
		}
		System.out.println();
	}
	
	public void intersection()
	{	
		TreeSet<Integer> intersectionSet = getIntersection(set1, set2);
		
		for(int i = 2; i < list.size(); i++)
		{
			intersectionSet = getIntersection(intersectionSet, list.get(i));
		}
		System.out.println("\nIntersection Set: " + intersectionSet);
	}
	
	public TreeSet<Integer> getIntersection(TreeSet<Integer> set1, TreeSet<Integer> set2)
	{
		TreeSet<Integer> intersectionSet = new TreeSet<Integer>();
		
		for(Integer i : set1)
		{
			if(set2.contains(i))
			{
				intersectionSet.add(i);
			}
		}
		
		return intersectionSet;
	}
	
	public void union()
	{
		TreeSet<Integer> unionSet = getUnion(set1, set2);
		
		for(int i = 2; i < list.size(); i++)
		{
			unionSet = getUnion(unionSet, list.get(i));
		}
		System.out.println("Union Set: " + unionSet);
	}
	
	public TreeSet<Integer> getUnion(TreeSet<Integer> set1, TreeSet<Integer> set2)
	{
		TreeSet<Integer> unionSet = new TreeSet<Integer>();
		
		for(Integer i : set1)
		{
			unionSet.add(i);
		}
		
		return unionSet;
	}
	
	public void evenIntersection()
	{
		TreeSet<Integer> evenIntersectionSet = getEvenIntersection(set1, set2);
		
		for(int i = 2; i < list.size(); i++)
		{
			evenIntersectionSet = getEvenIntersection(evenIntersectionSet, list.get(i));
		}
		System.out.println("Even Intersection Set: " + evenIntersectionSet);
	}
	
	public TreeSet<Integer> getEvenIntersection(TreeSet<Integer> set1, TreeSet<Integer> set2)
	{
		TreeSet<Integer> evenIntersectionSet = new TreeSet<Integer>();
		
		for(Integer i : set1)
		{
			if(set2.contains(i) && i % 2 == 0)
			{
				evenIntersectionSet.add(i);
			}
		}
		
		return evenIntersectionSet;
	}
	
	public void evenUnion()
	{
		TreeSet<Integer> evenUnionSet = getEvenUnion(set1, set2);
		
		for(int i = 2; i < list.size(); i++)
		{
			evenUnionSet = getEvenUnion(evenUnionSet, list.get(i));
		}
		System.out.println("Even Union Set: " + evenUnionSet);
	}
	
	public TreeSet<Integer> getEvenUnion(TreeSet<Integer> set1, TreeSet<Integer> set2)
	{
		TreeSet<Integer> evenUnionSet = new TreeSet<Integer>();
		
		for(Integer i : set1)
		{
			if(i % 2 == 0)
			{
				evenUnionSet.add(i);
			}
		}
		
		return evenUnionSet;
	}
	
	public static void main(String[] args)
	{
		HashTree1 hashTree1 = new HashTree1();
	}
}