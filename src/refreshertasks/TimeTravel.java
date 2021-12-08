package refreshertasks;


import java.io.*;
import java.util.Locale;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeTravel
{
	public TimeTravel()
	{
		File name = new File("/Users/aadikatyal/Downloads/timeData.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text;
			int tripCounter = 1;
			while((text = input.readLine()) != null)
			{
				System.out.println("Trip " + tripCounter + ":");
				tripCounter++;
				
				String additions[] = text.split(" ");
				
				//Gets instance of Calendar object and auto set to current date time
				Calendar c = Calendar.getInstance();
				
				//To set custom date time:
				//c.setTime(timeTravel.convertStringToDate("01-01-2021", "MM-dd-yyyy"));
				
				//c.getTime() means Date object (date AND time)
				
				System.out.println("\tDeparture Date and Time: " + convertDateToString(c.getTime(), "h:m aa") + " on " + convertDateToString(c.getTime(), "M/d/yyyy"));
				
				c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(additions[0]));
				c.add(Calendar.HOUR, Integer.parseInt(additions[1]));
				c.add(Calendar.MINUTE, Integer.parseInt(additions[2]));
				
				System.out.println("\tArrival Date and Time: " + convertDateToString(c.getTime(), "h:m aa") + " on " + convertDateToString(c.getTime(), "M/d/yyyy"));
			}
		}
		catch (IOException io)
		{
			System.err.println("File does not exist");
		}
	}

	public String convertDateToString(Date date, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	//Not using, just for learning
	public Date convertStringToDate(String strDate, String format) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		//parse converts String to Date
		return sdf.parse(strDate);
	}

	public static void main(String args[]) throws Exception
	{
		TimeTravel timeTravel = new TimeTravel();
	}
}

