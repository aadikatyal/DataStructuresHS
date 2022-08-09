package LambdaExpressions;

public class Runner 
{
	public static void main(String[] args)
	{
		Cat cat = new Cat();
		
		cat.print();
		
		printThing(cat);
	}
	
	static void printThing(InterfaceDemo interfaceDemo)
	{
		interfaceDemo.print();
	}
}