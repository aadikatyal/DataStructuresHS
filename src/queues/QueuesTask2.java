package queues;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QueuesTask2 
{
	private Queue<Passenger> queue;
	private PriorityQueue<Passenger> pq;
	
	public QueuesTask2() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/queues/QueuesInput2.txt");
		BufferedReader input = null;
		
		queue = new LinkedList<Passenger>();
		pq = new PriorityQueue<Passenger>();
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			
			String text = "";
			int counter = 0;

			String lastName = "", firstName = "", flightCity = "", flightTime = "";
			long diffHours = 0, diffMinutes = 0;
			
			while((text = input.readLine()) != null)
			{
				if(counter == 3)
				{
					Passenger passenger = new Passenger(lastName, firstName, flightCity, flightTime, diffHours, diffMinutes);
					queue.add(passenger);
					
					if(diffHours == 0)
					{
						pq.add(passenger);
					}
					counter = 0;
				}
				counter++;
				switch(counter)
				{
					case 1:
					{
						firstName = text.split(" ")[0];
						lastName = text.split(" ")[1];
						break;
					}
					case 2:
					{
						flightCity = text;
						break;
					}
					case 3:
					{
						flightTime = text;
						
						SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
						
						long currentTime = format.parse("9:03 AM").getTime();
						long expectedDepartureTime = format.parse(flightTime).getTime();
						long timeDifference = expectedDepartureTime - currentTime;
						
						diffMinutes = timeDifference / (60 * 1000) % 60;
						diffHours = timeDifference / (60 * 60 * 1000) % 24;
						
						break;
					}
					default:
					{
						break;
					}
				}
			}
			
			printQueue();
			System.out.println();
			printPQ();
			
			input.close();
		}
		catch (IOException io) 
		{
			System.err.println("No file bruh");
		}
	}
	
	public void printQueue()
	{
		System.out.println("Queue:");
		System.out.println("------------------------------------------------------------");
		
		while(!queue.isEmpty())
		{
			System.out.println(queue.poll());
		}
	}
	
	public void printPQ()
	{
		System.out.println("Priority Queue:");
		System.out.println("------------------------------------------------------------");
		while(pq.peek() != null)
		{
			System.out.println(pq.poll());
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		QueuesTask2 q1 = new QueuesTask2();
	}
}
