package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

@FunctionalInterface
interface State
{
	public int getState(int row, int col);
}

public class MineSweeper extends JPanel implements ActionListener, MouseListener
{
	JFrame frame;
	JToggleButton[][] grid;
	JPanel gridPanel;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu mode = new JMenu("Mode");
	JMenuItem easy, medium, hard;
	JButton reset;
	
	int xDim, yDim, mineCount, numNotClicked, buttonSize;
	boolean firstClick, gameOn;
	
	GraphicsEnvironment graphicsEnvironment;

	ImageIcon mineIcon, flag, smile, wait;
	ImageIcon[] numbers = new ImageIcon[9];
	
	State state = (row, col) -> (int) grid[row][col].getClientProperty("state");
	
	int time;
	Timer timer;
	JTextField timeText;
	Font clockFont;

	public MineSweeper()
	{
		frame = new JFrame("Mine Sweeper");
		frame.add(this);
		
		try 
		{
            graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            clockFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/aadikatyal/Desktop/ms-images/digital-7.ttf"));
            graphicsEnvironment.registerFont(clockFont);
        } 
		catch (IOException | FontFormatException e) {}
		
		buttonSize = 40;

		mineIcon = new ImageIcon("/Users/aadikatyal/Desktop/ms-images/mine.png");
		mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
		
		flag = new ImageIcon("/Users/aadikatyal/Desktop/ms-images/flag.png");
		flag = new ImageIcon(flag.getImage().getScaledInstance(buttonSize, buttonSize,Image.SCALE_SMOOTH));
		
		smile = new ImageIcon("/Users/aadikatyal/Desktop/ms-images/smile0.png");
		smile = new ImageIcon(smile.getImage().getScaledInstance(buttonSize,buttonSize,Image.SCALE_SMOOTH));
		
		wait = new ImageIcon("/Users/aadikatyal/Desktop/ms-images/wait0.png");
		wait = new ImageIcon(wait.getImage().getScaledInstance(buttonSize,buttonSize,Image.SCALE_SMOOTH));
		
		for(int x = 1; x <= 8; x++)
		{
			numbers[x-1] = new ImageIcon("/Users/aadikatyal/Desktop/ms-images/" + x + ".png");
			numbers[x-1] = new ImageIcon(numbers[x-1].getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
		}
		
		mineCount = 10;
		xDim = 9;
		yDim = 9;
		firstClick = true;
		gameOn = true;
		
		setGrid(xDim, yDim);
		
		menuBar.setLayout(new GridLayout(1, 3));
		graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		modeSetUp();

		timeText = new JTextField("0");
		menuBar.add(timeText);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setIcon(smile);
		menuBar.add(reset);

		frame.setJMenuBar(menuBar);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void modeSetUp()
	{
		mode = new JMenu("Mode");
		mode.setFont(clockFont.deriveFont(22f));
		menuBar.add(mode);
		
		easy = new JMenuItem("Easy");
		easy.addActionListener(this);
		mode.add(easy);
		
		medium = new JMenuItem("Medium");
		medium.addActionListener(this);
		mode.add(medium);
		
		hard = new JMenuItem("Hard");
		hard.addActionListener(this);
		mode.add(hard);
	}
	
	public void setGrid(int dimR, int dimC)
	{
		if(gridPanel!=null)
		{
			frame.remove(gridPanel);
		}

		gridPanel = new JPanel();
		grid = new JToggleButton[dimR][dimC];
		gridPanel.setLayout(new GridLayout(dimR, dimC));
		
		for(int r = 0; r < dimR; r++)
		{
			for(int c = 0; c < dimC; c++)
			{
				grid[r][c] = new JToggleButton();
				grid[r][c].addMouseListener(this);
				grid[r][c].setFocusable(false);
				grid[r][c].putClientProperty("row", r);
				grid[r][c].putClientProperty("column", c);
				grid[r][c].putClientProperty("state", 0);
				gridPanel.add(grid[r][c]);
			}
		}

		frame.setSize(buttonSize * dimC, buttonSize * dimR);
		frame.add(gridPanel, BorderLayout.CENTER);
		frame.revalidate();
	}

	public void actionPerformed(ActionEvent e)
	{
		Object difficulty = e.getSource();
		
		if(difficulty == easy) 
		{
		    xDim = 9;
		    yDim = 9;
		    mineCount = 10;
		}
		
		if(difficulty == medium) 
		{
		    xDim = 15;
		    yDim = 15;
		    mineCount = 40;
		}
		
		if(difficulty == hard) 
		{
		    xDim = 16;
		    yDim = 40;
		    mineCount = 100;
		}
		
		if (timer != null)
		{
		    timer.cancel();	
		}
		
		time = 0;
		timeText.setText(" " + time);
		gameOn = true;
		firstClick = true;
		setGrid(xDim, yDim);
		
		timer.schedule(new UpdateTimer(), 0, 1000);
	}
	
	public void dropMines(int currRow, int currCol)
	{
		int count = mineCount;
		while(count > 0)
		{
			int row = (int)(Math.random() * xDim);
			int col = (int)(Math.random() * yDim);
			
			if(state.getState(row, col) == 0 && Math.abs(row - currRow) > 1 && Math.abs(col - currCol) > 1)
			{
				grid[row][col].putClientProperty("state", -1);
				count--;
			}
		}

		for(int r = 0; r < xDim; r++)
		{
			for(int c = 0;c < yDim; c++)
			{
				int mineCount = 0;
				if(state.getState(r, c) != -1)
				{
					for(int rr = r - 1; rr <= r + 1; rr++)
					{
						for(int cc = c - 1; cc <= c + 1; cc++)
						{
							try
							{
								if(state.getState(rr, cc) == -1)
								{
									mineCount++;
								}
							}
							catch(ArrayIndexOutOfBoundsException e) {}
						}
					}
					grid[r][c].putClientProperty("state", mineCount);
				}
			}
		}
		numNotClicked = xDim * yDim;

	}
	public void expand(int row, int col)
	{
		if(!grid[row][col].isSelected())
		{
			grid[row][col].setContentAreaFilled(false);
			grid[row][col].setOpaque(true);
			grid[row][col].setBackground(Color.cyan);
			grid[row][col].setSelected(true);
			grid[row][col].setEnabled(false);
			numNotClicked--;
		}
		
		if(state.getState(row, col) > 0)
		{
			grid[row][col].setIcon(numbers[state.getState(row, col) - 1]);
			grid[row][col].setDisabledIcon(numbers[state.getState(row, col) - 1]);
		}
		else
		{
			for(int r = row - 1; r <= row + 1; r++)
			{
				for(int c = col - 1; c <= col + 1; c++)
				{
					try
					{
						if(!grid[r][c].isSelected())
						{
							expand(r, c);
						}
					}
					catch(ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		int row = (int)((JToggleButton) e.getComponent()).getClientProperty("row");
		int column = (int)((JToggleButton) e.getComponent()).getClientProperty("column");
		if(gameOn)
		{
			if(e.getButton() == MouseEvent.BUTTON1 && grid[row][column].isEnabled())
			{
				grid[row][column].setContentAreaFilled(false);
				grid[row][column].setOpaque(true);
				grid[row][column].setBackground(Color.cyan);
				if(firstClick)
				{
					timer = new Timer();
                    timer.schedule(new UpdateTimer(), 0, 1000);

					dropMines(row, column);
					firstClick = !firstClick;
				}

				if(state.getState(row, column) == -1)
				{
					gameOn = false;
					JOptionPane.showMessageDialog(null, "Lose!");
					displayMine();
				}
				else
				{
					numNotClicked--;
					grid[row][column].setSelected(true);
					expand(row, column);
					checkWin();
				}
			}
			
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				if(!grid[row][column].isSelected())
				{
					if(grid[row][column].getIcon() == null)
					{
						grid[row][column].setIcon(flag);
						grid[row][column].setDisabledIcon(flag);
						grid[row][column].setEnabled(false);
					}
					else
					{
						grid[row][column].setIcon(null);
						grid[row][column].setDisabledIcon(null);
						grid[row][column].setEnabled(true);
					}
				}
			}
		}
	}
	
	public void displayMine()
	{
		for(int r = 0; r < grid.length; r++)
		{
			for(int c = 0; c < grid[r].length; c++)
			{
				if(state.getState(r, c) == -1)
				{
					grid[r][c].setIcon(mineIcon);
					grid[r][c].setDisabledIcon(mineIcon);
				}
				grid[r][c].setEnabled(false);
			}
		}
	}
	
	public void checkWin()
	{
		if(mineCount == numNotClicked)
		{
			gameOn = false;
			JOptionPane.showMessageDialog(null, "Win!");
			disableButton();
		}
	}

	public void disableButton() 
	{
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				grid[x][y].setEnabled(false);
			}
		}
	}
	public void mousePressed(MouseEvent e)
	{
		reset.setIcon(wait);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		reset.setIcon(smile);
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}

	public static void main(String[] args)
	{
		MineSweeper mineSweeper = new MineSweeper();
	}
	
	public class UpdateTimer extends TimerTask
	{
        public void run() 
        {
            if(gameOn)
            {
                time++;
                timeText.setText(" " + time);
            }
            else
            {
            	timer.cancel();
            }
        }
    }
}