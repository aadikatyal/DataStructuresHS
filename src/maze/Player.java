package maze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
public class Player
{

	private int row;
	private int col;
	private int dim;
	private int dir;
	private String maze[][];
	private int moveCounter;
	
	public Player(int row, int col, int dim, int dir, String[][] maze)
	{
		this.row = row;
		this.col = col;
		this.dim = dim;
		this.maze = maze;
		this.dir = dir;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}

	public int getDimension()
	{
		return dim;
	}
	
	public int getStarCount()
	{
		if(moveCounter < 20)
		{
			return 3;
		}
		else if(moveCounter < 30)
		{
			return 2;
		}
		return 1;
	}

	public int getDir()
	{
		return dir;
	}
	
	public int getMoveCount()
	{
		return moveCounter;
	}

	public void move(int key)
	{
		if(key == 38)
		{
			moveCounter++;
		}
				
		if(maze[row][col].equals("*"))
		{
			JOptionPane.showMessageDialog(null, "Finished!", "Message", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "Stars Earned: " + getStarCount(), "Message", JOptionPane.INFORMATION_MESSAGE);
			
			MazeProgram mp;
			try 
			{
				mp = new MazeProgram();
				//mp.displayStars();
			} 
			catch (Exception e) {}
			
		}
		
		if(key == 39)
		{
			if(dir == 270)
			{
				dir = 0;
			}
			else
			{
				dir += 90;
			}
				
		}
		
		if(key == 37)
		{
			if(dir==0)
			{
				dir = 270;
			}
			else
			{
				dir -= 90;
			}
		}

		if(key == 38)
		{
			if(dir == 0)
			{
				if(!maze[row-1][col].equals("#"))
				{
					row--;
				}	
			}
			
			if(dir == 90)
			{
				if(!maze[row][col+1].equals("#"))
				{
					col++;
				}
			}

			if (dir == 180)
			{
				if(!maze[row+1][col].equals("#"))
					row++;
			}

			if (dir == 270)
			{
				if(!maze[row][col-1].equals("#"))
					col--;
			}
		}

	}
}



