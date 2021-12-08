package queues_demo;

import java.util.*;

public class QueuesDemo 
{
	public QueuesDemo()
	{
		//Queue (FIFO)
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(1);
		queue.add(3);
		queue.add(2);
		
		while(!queue.isEmpty())
		{
			System.out.println(queue.poll());
		}
		
		System.out.println();
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		pq.add(1);
		pq.add(3);
		pq.add(2);
		
		while(!pq.isEmpty())
		{
			System.out.println(pq.poll());
		}
		
		System.out.println();
		
		PriorityQueue<Person> pq2 = new PriorityQueue<Person>();
		
		pq2.add(new Person(17, "Aadi"));
		pq2.add(new Person(98, "John"));
		pq2.add(new Person(35, "Grover"));
		pq2.add(new Person(4, "Drew"));
		
		while(pq2.peek() != null)
		{
		    Person person = (Person) pq2.poll();
		    System.out.println (person.toString());
		}
	}
	
	public static void main(String[] args)
	{
		QueuesDemo q1 = new QueuesDemo();
	}
}
