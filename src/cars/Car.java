package cars;

import java.util.Comparator;

public class Car implements Comparable<Car>
{
	private int id, mpg, engineSize, horsePower, weight, acceleration, numCylinders;
	private String country;
	
	public Car(int id, int mpg, int engineSize, int horsePower, int weight, int acceleration, String country, int numCylinders)
	{
		this.id = id;
		this.mpg = mpg;
		this.engineSize = engineSize;
		this.horsePower = horsePower;
		this.weight = weight;
		this.acceleration = acceleration;
		this.country = country;
		this.numCylinders = numCylinders;
	}

	public int getId() 
	{
		return id;
	}

	public int getMpg() 
	{
		return mpg;
	}

	public int getEngineSize()
	{
		return engineSize;
	}

	public int getHorsePower() 
	{
		return horsePower;
	}

	public int getWeight() 
	{
		return weight;
	}

	public int getAcceleration() 
	{
		return acceleration;
	}

	public String getCountry()
	{
		return country;
	}

	public int getNumCylinders() 
	{
		return numCylinders;
	}

	@Override
	public String toString()
	{
		return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", id, mpg, engineSize, horsePower, weight, acceleration, country, numCylinders);
	}

	@Override
	public int compareTo(Car otherCar)
	{
		return Comparator.comparingInt(Car::getAcceleration).thenComparingInt(Car::getMpg).thenComparingInt(Car::getHorsePower).thenComparingInt(Car::getEngineSize).thenComparingInt(Car::getWeight).thenComparingInt(Car::getNumCylinders).thenComparingInt(Car::getId).compare(this, otherCar);
	}
}
