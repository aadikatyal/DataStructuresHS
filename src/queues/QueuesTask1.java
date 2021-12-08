package queues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QueuesTask1 
{
	private Queue<String> queue;
	private PriorityQueue<Word> pq;
	private PriorityQueue<Word> reversePQ;
	
	public QueuesTask1() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/queues/QueuesInput1.txt");
		BufferedReader input = null;
		
		queue = new LinkedList<String>();
		pq = new PriorityQueue<Word>();
		reversePQ = new PriorityQueue<Word>(Collections.reverseOrder());
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			String line = input.readLine();
			String words[] = line.split(" ");
			
			for(int i = 0; i < words.length; i++)
			{
				queue.add(words[i]);
				pq.add(new Word(words[i]));
				reversePQ.add(new Word(words[i]));
			}
			
			printWord();
		}
		catch (IOException io) 
		{
			System.err.println("No file bruh");
		}
		finally
		{
			input.close();
		}
	}
	
	public void printWord()
	{
		System.out.println(String.format("%-15s%-15s%s", "Queue", "PQ", "Reverse PQ"));
		System.out.println("--------------------------------------------");
				
		while(!queue.isEmpty() && !pq.isEmpty())
		{
			Word pqWord = (Word) pq.poll();
			String queueWord = queue.poll();
			Word reverseWord = (Word) reversePQ.poll();
			
			System.out.println(String.format("%-15s%-15s%s", queueWord, pqWord, reverseWord));
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		QueuesTask1 q1 = new QueuesTask1();
	}
}
