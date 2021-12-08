package maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;

public class MazeProgram extends JPanel implements KeyListener
{
	private Player player;
	private int dir;
	private boolean draw3D = false;
	private ArrayList<Wall> walls;
	private JFrame frame;
	private String[][] maze = new String[11][11];

	public MazeProgram() throws Exception
	{
		walls = new ArrayList<Wall>();
		awtDeclarations();	
		setMaze();
		draw3D = false;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		if(!draw3D)
		{
			g.setColor(Color.BLACK);
			for(int r = 0; r < maze.length; r++)
			 {
				for(int c = 0; c < maze[r].length; c++)
				{
					if(maze[r][c].equals("#"))
					{
						g.setColor(new Color(119, 136, 153));
						g.fillRect(c * 20, r * 20, 20, 20);
					}
					else
					{
						g.setColor(Color.BLACK);
						g.fillRect(c * 20, r * 20, 20, 20);
						
						
					}
					g.setColor(new Color(40,40,40));
					g.drawRect(c * 20, r * 20, 20, 20);
				}
	
			}
			g.setColor(Color.CYAN);
			g.fillOval(player.getCol() * 20, player.getRow() * 20, player.getDimension(), player.getDimension());			
		}
		else
		{
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

			Graphics2D g2d = (Graphics2D) g;
			for(Wall wall: walls)
			{
				g2d.setPaint(wall.getPaint());
				g2d.fillPolygon(wall.getPoly());
				g2d.setColor(Color.WHITE);
				g2d.drawPolygon(wall.getPoly());
			}
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		
		switch(player.getDir()) 
		{
			case 90: 
			{
				g.drawString("Direction: East", 800, 400);
				break;
			}
			case 270:
			{
				g.drawString("Direction: West", 800, 400);
				break;
			}
			case 180:
			{
				g.drawString("Direction: South", 800, 400);
				break;
			}
			case 0: 
			{
				g.drawString("Direction: North", 800, 400);
				break;
			}
			default:
			{
				break;
			}
		}
		
		g.drawString("Moves Made: " + player.getMoveCount(), 800, 450);
		//g.drawImage(image, 800, 500, 300, 100, frame);
	}
	
	public void displayStars(Graphics g)
	{
		BufferedImage image = null;
		try 
		{
			image = ImageIO.read(new File("/Users/aadikatyal/eclipse-workspace/DS/star1.png"));
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void awtDeclarations() 
	{
		frame = new JFrame("Aadi's Maze!");
		frame.add(this);
		frame.addKeyListener(this);
		frame.setSize(1100, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void setMaze() throws Exception 
	{
		File name = new File("/Users/aadikatyal/eclipse-workspace/DS/src/maze/Maze.txt");
		BufferedReader input = null;

		player = new Player(1, 3, 20, dir, maze);
		
		try
		{
			input = new BufferedReader(new FileReader(name));

			String text;
			int i = 0;
			while ((text = input.readLine()) != null) 
			{
				String mazeItems[] = text.split("");
				maze[i] = mazeItems;
				i++;
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

		if (draw3D) 
		{
			createwalls();
		}
	}
	
	public void createwalls() 
	{
		walls = new ArrayList<Wall>();

		int hr = player.getRow();
		int hc = player.getCol();
		dir = player.getDir();

		for(int wallNum = 0; wallNum < 5; wallNum++) 
		{
			initialWalls(wallNum);
			leftWalls(hr, hc, wallNum);
			rightWalls(hr, hc, wallNum);
		}

		for(int wallNum = 5; wallNum >= 0; wallNum--) 
		{
			sideAndEndWalls(hr, hc, wallNum);
		}
	}

	public void initialWalls(int wallNum) 
	{
		walls.add(rightRect(wallNum));
		walls.add(leftRect(wallNum));
		walls.add(floor(wallNum));
		walls.add(ceiling(wallNum));
	}

	public void sideAndEndWalls(int row, int col, int wallNum)
	{
		switch(dir)
		{
			case 0:
			{
				try
				{
					if(maze[row - wallNum][col].equals("#"))
					{
						addSideWalls(wallNum);
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 90:
			{
				try
				{
					if(maze[row][col + wallNum].equals("#"))
					{
						addSideWalls(wallNum);
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 180:
			{
				try
				{
					if(maze[row + wallNum][col].equals("#"))
					{
						addSideWalls(wallNum);
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 270:
			{
				try
				{
					if(maze[row][col - wallNum].equals("#"))
					{
						addSideWalls(wallNum);
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			default:
			{
				break;
			}
		}
	}
	
	public void addSideWalls(int wallNum) 
	{
		walls.add(endwall(wallNum));
		walls.add(topRwall(wallNum));
		walls.add(topLwall(wallNum));
	}

	public void rightWalls(int row, int col, int wallNum)
	{
		switch(dir)
		{
			case 0:
			{
				try
				{
					if(maze[row - wallNum][col + 1].equals("#"))
					{
						walls.add(rightWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 90:
			{
				try
				{
					if(maze[row + 1][col + wallNum].equals("#"))
					{
						walls.add(rightWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 180:
			{
				try
				{
					if(maze[row + wallNum][col - 1].equals("#"))
					{
						walls.add(rightWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 270:
			{
				try
				{
					if(maze[row - 1][col - wallNum].equals("#"))
					{
						walls.add(rightWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			default:
			{
				break;
			}
		}
	}

	private void leftWalls(int row, int col, int wallNum)
	{
		switch(dir)
		{
			case 0:
			{
				try
				{
					if(maze[row - wallNum][col - 1].equals("#"))
					{
						walls.add(leftWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 90:
			{
				try
				{
					if(maze[row - 1][col + wallNum].equals("#"))
					{
						walls.add(leftWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 180:
			{
				try
				{
					if(maze[row + wallNum][col + 1].equals("#"))
					{
						walls.add(leftWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			case 270:
			{
				try
				{
					if(maze[row + 1][col - wallNum].equals("#"))
					{
						walls.add(leftWall(wallNum));
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {}
				break;
			}
			
			default:
			{
				break;
			}
		}
	}
	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == 32) 
		{
			draw3D = !draw3D;
		} 
		else 
		{
			player.move(e.getKeyCode());
		}

		if (draw3D) 
		{
			createwalls();
		}
		repaint();
	}
	
	public Wall leftRect(int n) 
	{
		int rows[] = {100 + 50 * n, 150 + 50 * n, 150 + 50 * n, 100 + 50 * n};
		int cols[] = {150 + 50 * n, 150 + 50 * n, 650 - 50 * n, 650 - 50 * n};

		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "LeftHall", 50);
	}

	public Wall leftWall(int n) 
	{
		int rows[] = { 100 + 50 * n, 150 + 50 * n, 150 + 50 * n, 100 + 50 * n };
		int cols[] = { 100 + 50 * n, 150 + 50 * n, 650 - 50 * n, 700 - 50 * n };
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "Left", 50);
	}

	public Wall rightRect(int n) {
		int rows[] = { 700 - 50 * n, 650 - 50 * n, 650 - 50 * n, 700 - 50 * n };
		int cols[] = { 150 + 50 * n, 150 + 50 * n, 650 - 50 * n, 650 - 50 * n };
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "RightHall", 50);
	}

	public Wall rightWall(int n) {
		int rows[] = { 700 - 50 * n, 650 - 50 * n, 650 - 50 * n, 700 - 50 * n };
		int cols[] = { 100 + 50 * n, 150 + 50 * n, 650 - 50 * n, 700 - 50 * n };
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "Right", 50);
	}

	public Wall endwall(int n) 
	{
		int rows[] = { 100 + 50 * n, 700 - 50 * n, 700 - 50 * n, 100 + 50 * n };
		int cols[] = { 100 + 50 * n, 100 + 50 * n, 700 - 50 * n, 700 - 50 * n };
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "FrontWall", 50);
	}

	public Wall ceiling(int n) 
	{
		int rows[] = { 100 + 50 * n, 150 + 50 * n, 650 - 50 * n, 700 - 50 * n };
		int cols[] = { 100 + 50 * n, 150 + 50 * n, 150 + 50 * n, 100 + 50 * n };
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "Ceiling", 50);
	}

	public Wall floor(int n) 
	{
		int rows[] = { 100 + 50 * n, 150 + 50 * n, 650 - 50 * n, 700 - 50 * n };
		int cols[] = { 700 - 50 * n, 650 - 50 * n, 650 - 50 * n, 700 - 50 * n };
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "Floor", 50);
	}

	public Wall topRwall(int n) 
	{
		int rows[] = {700 - 50 * n, 750 - 50 * n, 750 - 50 * n};
		int cols[] = {100 + 50 * n, 100 + 50 * n, 50 + 50 * n};
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "TopRightWall", 50);
	}

	public Wall topLwall(int n) 
	{
		int rows[] = {50 + 50 * n, 100 + 50 * n, 50 + 50 * n};
		int cols[] = { 100 + 50 * n, 100 + 50 * n, 50 + 50 * n};
		
		return new Wall(rows, cols, new Color(255 - 50 * n, 255 - 50 * n, 255 - 50 * n), "TopLeftWall", 50);
	}


	public void keyReleased(KeyEvent e)
	{

	}

	public void keyTyped (KeyEvent e)
	{

	}

	public static void main(String[] args) throws Exception
	{
		MazeProgram app = new MazeProgram();
	}
}




