package queues_demo;

public class Person implements Comparable<Person>
{
	private int age;
	private String name;
	
	public Person(int a, String n)
	{
		age = a;
		name = n;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name + " " + age;
	}

	public int compareTo(Person otherPerson) 
	{
		int a = age;
		int b = otherPerson.age;

		if (a > b) 
			return 1;
		if (a < b) 
			return -1;
		if(getAge() > otherPerson.getAge())
			return 1;

		return -getName().compareTo(otherPerson.getName());
	}
}
