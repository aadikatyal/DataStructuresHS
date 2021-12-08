package cars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CarTask 
{
	private Queue<Car> queue;
	private Stack<Car> stack;
	private PriorityQueue<Car> pq;
	
	public CarTask() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/cars/CarData.txt");
		BufferedReader input = null;
		
		queue = new LinkedList<Car>();
		stack = new Stack<Car>();
		pq = new PriorityQueue<Car>();
		
		try
		{
			input = new BufferedReader(new FileReader(file));
			String line = input.readLine();
			
			while ((line = input.readLine()) != null)
			{
				String carInfo[] = line.split("\t");
				
				int countryNum = Integer.valueOf(carInfo[6]);
				String countryName = "";
				
				switch(countryNum)
				{
					case 1:
					{
						countryName = "American";
						break;
					}
					
					case 2:
					{
						countryName = "German";
						break;
					}
					
					case 3:
					{
						countryName = "Japanese";
						
					}
					
					default:
					{
						break;
					}
				}
				
				Car car = new Car(Integer.valueOf(carInfo[0]), Integer.valueOf(carInfo[1]), Integer.valueOf(carInfo[2]), Integer.valueOf(carInfo[3]), Integer.valueOf(carInfo[4]), Integer.valueOf(carInfo[5]), countryName, Integer.valueOf(carInfo[7]));
				
				queue.add(car);
			}
			
			printQueue();
			
			printStack();
			
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
		System.out.println("Queue:\n");
		
		System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "ID", "MPG", "Engine Size", "Horse Power", "Weight", "Acceleration", "Country", "# of Cylinders"));
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		while(!queue.isEmpty())
		{
			Car car = queue.poll();
			System.out.println(car);
			stack.push(car);
		}
		
		System.out.println();
	}
	
	public void printStack()
	{
		System.out.println("Stack:\n");
		
		System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "ID", "MPG", "Engine Size", "Horse Power", "Weight", "Acceleration", "Country", "# of Cylinders"));
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");		
		
		while(!stack.isEmpty())
		{
			Car car = stack.pop();
			System.out.println(car);
			pq.add(car);
		}
		
		System.out.println();
	}
	
	public void printPQ()
	{
		System.out.println("PQ:\n");
		
		System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "ID", "MPG", "Engine Size", "Horse Power", "Weight", "Acceleration", "Country", "# of Cylinders"));
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

		while(pq.peek() != null)
		{
			System.out.println(pq.poll());
		}
	}

	public static void main(String[] args) throws Exception
	{
		CarTask q1 = new CarTask();
	}
}
