package stacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class StarWarsStacks 
{
	private Stack<Character> males, females, droids, validBdays;

	public StarWarsStacks() throws Exception
	{
		males = new Stack<Character>();
		females = new Stack<Character>();
		droids = new Stack<Character>();
		validBdays = new Stack<Character>();
		
		File file = new File("/Users/aadikatyal/eclipse-workspace/DS/src/stacks/StarWarsCharacters.csv");
		BufferedReader input = null;
		
		try 
		{
			input = new BufferedReader(new FileReader(file));
			String line;
			while ((line = input.readLine()) != null)
			{
				populateStacks(line);
			}
			
			print("Male Characters", males);
			print("Female Characters", females);
			print("Droids", droids);
			print("Ages", validBdays);
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

	public void populateStacks(String line) 
	{
		String items[] = line.split(",");
		String name = items[0], birthYear = items[5], gender = items[6], homeWorld = items[7], species = items[8];
		Character character = new Character(name, birthYear, gender, homeWorld, species);

		switch (species) 
		{
			case "Droid": 
			{
				droids.push(character);
			}

			case "Human": 
			{
				if (gender.equals("male")) 
				{
					males.push(character);
				} 
				else if (gender.equals("female")) 
				{
					females.push(character);
				}
			}
		}
		
		if(birthYear.contains("BBY"))
		{
			validBdays.push(character);
		}
	}
	
	public void print(String characterType, Stack<Character> stack)
	{
		System.out.println(characterType);
		boolean includeAge = false;
		
		if(characterType.equals("Ages"))
		{
			includeAge = true;
			
			String spaceFormat1 = "%" + spaceToBeAdded("Name") + "s";
			String spaceFormat2 = "%" + spaceToBeAdded("Homeworld") + "s";
			System.out.format("Name" + spaceFormat1 + "Homeworld" + spaceFormat2 + "Birth Year (BBY)\n", "", "");
		}
		else
		{
			String spaceFormat = "%" + spaceToBeAdded("Name") + "s";
			System.out.format("Name" + spaceFormat + "Homeworld\n", "");
		}
		
		while(!stack.isEmpty())
		{
			Character character = stack.pop();
			String name = character.getName(), homeWorld = character.getHomeWorld(), ages = character.getBirthYear();
			
			if(homeWorld.equals("NA"))
			{
				homeWorld = "Unknown";
			}
			
			if(includeAge)
			{
				String spaceFormat1 = "%" + spaceToBeAdded(name) + "s";
				String spaceFormat2 = "%" + spaceToBeAdded(homeWorld) + "s";
				System.out.format(name + spaceFormat1 + homeWorld + spaceFormat2 + ages.substring(0, ages.indexOf("BBY")) + "\n", "", "");
			}
			else
			{
				String spaceFormat = "%" + spaceToBeAdded(name) + "s";
				System.out.format(name + spaceFormat + homeWorld + "\n", "");
			}
		}
		
		System.out.println();
	}
	
	public int spaceToBeAdded(String str)
	{
		return 30 - str.length();
	}

	public class Character
	{
		private String name, birthYear, gender, homeWorld, species;

		public Character(String name, String birthYear, String gender, String homeWorld, String species)
		{
			this.name = name;
			this.birthYear = birthYear;
			this.gender = gender;
			this.homeWorld = homeWorld;
			this.species = species;
		}
		
		public String getName()
		{
			return name;
		}
		
		public String getBirthYear()
		{
			return birthYear;
		}

		public String getHomeWorld()
		{
			return homeWorld;
		}
	}

	public static void main(String[] args) throws Exception
	{
		StarWarsStacks rss = new StarWarsStacks();
	}
}