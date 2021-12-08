package queues;

public class Passenger implements Comparable<Passenger>
{
	private String lastName, firstName, flightCity, flightTime;
	private long diffHours, diffMinutes;
	
	public Passenger(String lastName, String firstName, String flightCity, String flightTime, long diffHours, long diffMinutes)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.flightCity = flightCity;
		this.flightTime = flightTime;
		this.diffHours = diffHours;
		this.diffMinutes = diffMinutes;
	}
	
	public String getLastName() 
	{
		return lastName;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public String getFightCity()
	{
		return flightCity;
	}

	public String getFlightTime() 
	{
		return flightTime;
	}

	public String getEtdCalc() 
	{
		return diffHours + ":" + diffMinutes;
	}
	
	@Override
	public String toString()
	{		
		return lastName + ", " + firstName + " - " + flightCity + " - " + flightTime + " - " + getEtdCalc();
	}


	@Override
	public int compareTo(Passenger otherPassenger) 
	{
		// if first > second --> swap, else no change [if negative no change]
		
		long minutes = diffMinutes + diffHours * 60;
		long otherPassengerMinutes = otherPassenger.diffMinutes + otherPassenger.diffHours * 60;
		
		return (int)(minutes - otherPassengerMinutes);
		
	}
}