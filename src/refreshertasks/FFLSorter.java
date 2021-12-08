package refreshertasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FFLSorter 
{
	private List<Player> players;
	public FFLSorter() throws Exception
	{
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/refreshertasks/FFLInput.txt");
		BufferedReader input = null;
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			String line;
			players = new ArrayList<Player>();
			while ((line = input.readLine()) != null)
			{
				String playerInfo[] = line.split(";");
				players.add(new Player(Double.parseDouble(playerInfo[0]), playerInfo[1], playerInfo[2], playerInfo[3], Integer.parseInt(playerInfo[4]), Double.parseDouble(playerInfo[5]), Double.parseDouble(playerInfo[6]), Double.parseDouble(playerInfo[7]), Double.parseDouble(playerInfo[8]), Integer.parseInt(playerInfo[9])));
			}
			Collections.sort(players);
			for(Player player: players)
			{
				player.line();
			}
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
	
	public class Player implements Comparable<Player>
	{
		private double pickNumber, overall, stdDev, highRd, lowRd, difference;
		private String name, team, position;
		private int bye, draftedTime;
		
		public Player(double pickNumber, String name, String position, String team, int bye, double overall, double stdDev, double highRd, double lowRd, int draftedTime)
		{
			this.pickNumber = pickNumber;
			this.name = name;
			this.team = team;
			this.position = position;
			this.bye = bye;
			this.overall = overall;
			this.stdDev = stdDev;
			this.highRd = highRd;
			this.lowRd = lowRd;
			this.draftedTime = draftedTime;
			
			this.difference = getDifference();
		}
		
		public double getOverall()
		{
			return overall;
		}
		
		public int getDifference() 
		{
			String[] high = (highRd + "").split("\\.");
			int highRd = Integer.parseInt(high[0]);
			int highPick = Integer.parseInt(high[1]);

			String[] low = (lowRd + "").split("\\.");
			int lowRd = Integer.parseInt(low[0]);
			int lowPick = Integer.parseInt(low[1]);

			if (high[1].equals("1"))
			{
				highPick = 10;
			}
			
			if (low[1].equals("1"))
			{
				lowPick = 10;
			}
			
			return (lowRd - highRd) * 12 + (lowPick - highPick);
		}
		
		public void line()
		{
			String nameSpace = "%" + spaceToBeAdded("Name: " + name) + "s";
			String positionSpace = "%" + (15 - ("Pos: " + position).length()) + "s";
			String teamSpace = "%" + (10 - (team).length()) + "s";
			String highRdSpace = "%" + (10 - (highRd + "").length()) + "s";
			String lowRdSpace = "%" + (10 - (lowRd + "").length()) + "s";
			String differenceSpace = "%" + (10 - (getDifference() + "").length()) + "s";
			System.out.format("Name: " + name + nameSpace + "Pos: " + position + positionSpace + team + teamSpace + lowRd + lowRdSpace + highRd + highRdSpace + getDifference() + differenceSpace + getOverall() + "\n", "", "", "", "", "", "", "");
		}
		
		public int spaceToBeAdded(String str)
		{
			return 35 - str.length();
		}

		@Override
		public int compareTo(Player other) 
		{
			double overall1 = getOverall();
			double overall2 = other.getOverall();

			if (getDifference() - other.getDifference() > 0) 
			{
				return 1;
			}
			
			if (getDifference() - other.getDifference() < 0) 
			{
				return -1;
			}
			
			if (getOverall() > other.getOverall()) 
			{
				return 1;
			}
			
			if (getOverall() < other.getOverall())
			{
				return -1;
			}

			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		FFLSorter fflSorter = new FFLSorter();
	}
}
