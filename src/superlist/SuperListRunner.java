package superlist;

import java.util.Collections;

public class SuperListRunner
{	
	public SuperListRunner()
	{
		SuperList<Integer> list = new SuperList<Integer>();
		
		for(int i = 0; i < 30; i++)
		{
			int randNum = (int)(Math.random() * 1000) + 1;
			list.add(randNum);
		}
		
		System.out.println("List: " + list);
		System.out.println("List Size: " + list.size());
		
		SuperList<Integer> stack = new SuperList<Integer>();
		
		while(list.size() > 0)
		{
			stack.push(list.remove(0));
		}
		
		System.out.println("Stack: " + stack);
		
		SuperList<Integer> queue = new SuperList<Integer>();
		
		while(!stack.isEmpty())
		{
			queue.add(stack.pop());
		}
		
		System.out.println("Queue: " + queue);
		
		while(!queue.isEmpty())
		{
			list.add(0, queue.poll());
		}
		
		System.out.println("List: " + list);
		
		int sum = 0;
		
		for(int i = 0; i < list.size(); i++)
		{
			sum += list.get(i);
		}
		
		System.out.println("Sum: " + sum);
		
		int evenIndexSum = 0;
		
		for(int i = 0; i < list.size(); i += 2)
		{
			evenIndexSum += list.get(i);
		}
		
		System.out.println("Even Index Sum: " + evenIndexSum);
		
		int oddIndexSum = 0;
		
		for(int i = 1; i < list.size(); i += 2)
		{
			oddIndexSum += list.get(i);
		}
		
		System.out.println("Odd Index Sum: " + oddIndexSum);
		
		int originalLength = list.size();
		
		for(int i = 0; i < originalLength; i ++)
		{
			if(list.get(i) % 2 == 0)
			{
				list.add(list.get(i));
			}
		}
		
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i) % 3 == 0)
			{
				list.remove(i);
			}
		}
		
		list.add(3, 55555);
		
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = i + 1; j < list.size(); j++)
			{
				if(list.get(j) > list.get(i))
				{
					int temp = list.get(j);
					list.remove(j);
					list.add(j, list.get(i));
					list.remove(i);
					list.add(i, temp);
				}
			}
		}
		
		System.out.println("Descending Order: " + list);
		
		int median = 0;
		
		if(list.size() % 2 == 1)
		{
			median = list.get(list.size() / 2);
		}
		else
		{
			median = (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
		}
		
		System.out.println("Median: " + median);
		
		SuperList<String> strList = new SuperList<String>();
		String sentence = "O’Connor utilizing a metaphor to present students tackling literary analysis like a laboratory experiment showcases the chore-like intentions of students to expedite the process of analyzation rather than them enjoying the text for its enriching and thought-provoking essence";
		
		String strArr[] = sentence.split(" ");
		
		for(int i = 0; i < strArr.length; i++)
		{
			strList.add(strArr[i]);
		}
		
		for(int i = 0; i < strList.size(); i++)
		{
			if(strList.get(i).length() <= 3)
			{
				strList.remove(i);
				i--;
			}
		}
		
		for(int i = 1; i < strList.size(); i++)
		{
			int j = i - 1;
			
			while(j >= 0 && strList.get(j).compareTo(strList.get(j + 1)) > 0)
			{
				String temp = strList.get(j + 1);
				strList.remove(j + 1);
				strList.add(j + 1, strList.get(j));
				strList.remove(j);
				strList.add(j, temp);
				j--;
			}
		}
		
		System.out.println("Sorted Word List: " + strList);
		
		int wordLengthSum = 0;
		
		for(int i = 0; i < strList.size(); i++)
		{
			wordLengthSum += strList.get(i).length();
		}
		
		System.out.println("Average Word Length: " + wordLengthSum / strList.size()); 
	}
	
	public static void main(String[] args)
	{
		SuperListRunner slr = new SuperListRunner();
	}
}