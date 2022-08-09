package music_box;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class MusicBox extends JFrame implements ActionListener, AdjustmentListener, Runnable
{
	JFrame frame;
	JScrollPane scrollPane;
	JPanel panel, otherPanel, labelPanel, tempoPanel, menuButtonsPanel;
	JMenuBar menuBar;
	JButton startStop, clear, reset;
	JToggleButton[][] toggleButtons;
	JFileChooser fileChooser;
	Font font = new Font("Times New Roman", Font.PLAIN, 10);
	String[] clipNames = { "C3", "B3", "ASharp3", "A3", "GSharp3", "G3", "FSharp3", "F3", "E3", "DSharp3", "D3",
			"CSharp3", "C2", "B2", "ASharp2", "A2", "GSharp2", "G2", "FSharp2", "F2", "E2", "DSharp2", "D2", "CSharp2",
			"C1", "B1", "ASharp1", "A1", "GSharp1", "G1", "FSharp1", "F1", "E1", "DSharp1", "D1", "CSharp1", "C0" };
	Thread timing;
	Clip[] clips;
	boolean currentlyPlaying = false;
	String[] instruments;
	JMenu instrumentsMenu, file, addRemoveCol;
	JMenuItem save, load, addCol, removeCol, addNCol, removeNCol;
	JMenuItem[] instrumentItems;
	int column = 0, tempo = 200;
	JScrollBar tempoBar;
	JLabel tempoLabel;
	
	public MusicBox()
	{
		frame = new JFrame("Music Box");
		frame.setSize(1000, 800);
		
		toggleButtons = new JToggleButton[37][50];
		panel = new JPanel();
		clips = new Clip[clipNames.length];
		instruments = new String[] {"Bell", "Glockenspiel", "Marimba", "Oboe", "Oh_Ah", "Piano"};
		instrumentItems = new JMenuItem[instruments.length];
		panel.setLayout(new GridLayout(toggleButtons.length, toggleButtons[0].length, 2, 5));
		
		tempoBar = new JScrollBar(JScrollBar.HORIZONTAL, 200, 0, 50, 350);
		tempoBar.addAdjustmentListener(this);
		tempoLabel = new JLabel("Tempo: " + tempo);
		tempoPanel = new JPanel(new BorderLayout());
		tempoPanel.add(tempoBar, BorderLayout.CENTER);
		tempoPanel.add(tempoLabel, BorderLayout.WEST);
		
		addButtons();
		
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		String currentDirectory = System.getProperty("user.dir");
		fileChooser = new JFileChooser(currentDirectory);
		
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		save = new JMenuItem("Save");
		save.addActionListener(this);
		file.add(save);
		load = new JMenuItem("Load");
		load.addActionListener(this);
		file.add(load);
		menuBar.add(file);
		
		loadSounds(instruments[0]);
		
		timing = new Thread(this);
		timing.start();
		
		addRemoveCol = new JMenu("Add/Remove Col");
		addCol = new JMenuItem("Add Col");
		removeCol = new JMenuItem("Remove Col");
		addNCol = new JMenuItem("Add N Col");
		removeNCol = new JMenuItem("Remove N Col");
		addRemoveCol.add(addCol);
		addRemoveCol.add(removeCol);
		addRemoveCol.add(addNCol);
		addRemoveCol.add(removeNCol);
		addCol.addActionListener(this);
		removeCol.addActionListener(this);
		addNCol.addActionListener(this);
		removeNCol.addActionListener(this);
		
		menuBar.add(addRemoveCol);
		
		menuButtonsPanel = new JPanel();
		menuButtonsPanel.setLayout(new GridLayout(1, 2));
		
		instrumentsMenu = new JMenu("Instruments");
		instrumentsMenu.addActionListener(this);
		
		for(int i = 0; i < instruments.length; i++)
		{
			instrumentItems[i] = new JMenuItem(instruments[i], KeyEvent.VK_T);
			instrumentsMenu.add(instrumentItems[i]);
			instrumentItems[i].addActionListener(this);
			instrumentItems[i].getClientProperty(instrumentItems[i]);
			instrumentItems[i].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		}
		
		startStop = new JButton("Start");
		startStop.addActionListener(this);
		clear = new JButton("Clear");
		clear.addActionListener(this);
		
		menuBar.add(instrumentsMenu);
		menuButtonsPanel.add(startStop);
		menuButtonsPanel.add(clear);
		menuBar.add(menuButtonsPanel);
		
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(tempoPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void addButtons()
	{
		for(int i = 0; i < toggleButtons.length; i++)
		{
			String note = clipNames[i].replaceAll("Sharp", "#");
			for(int j = 0; j < toggleButtons[i].length; j++)
			{
				toggleButtons[i][j] = new JToggleButton();
				toggleButtons[i][j].setText(note);
				toggleButtons[i][j].setFont(font);
				toggleButtons[i][j].setPreferredSize(new Dimension(30, 30));
				toggleButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
				panel.add(toggleButtons[i][j]);
			}
		}
	}

	public void loadSounds(String instrument)
	{
		try 
		{
			for(int i = 0; i < clipNames.length; i++)
			{
				File file = new File("/Users/aadikatyal/Desktop/tones/" + instrument + "/" + instrument + " - " + clipNames[i] + ".wav");
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
				clips[i] = AudioSystem.getClip();
				clips[i].open(audioIn);
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				if(!currentlyPlaying)
				{
					timing.sleep(0);
				}
				else
				{
					for(int i = 0; i < toggleButtons.length; i++)
					{
						if(column >= 0)
						{
							if(toggleButtons[i][column].isSelected())
							{
								clips[i].start();
								scrollPane.getHorizontalScrollBar().setValue(column * 30);
								toggleButtons[i][column].setForeground(Color.MAGENTA);
							}
						}
					}
					
					timing.sleep(tempo);
					
					for(int i = 0; i < toggleButtons.length; i++)
					{
						if(column >= 0)
						{
							if(toggleButtons[i][column].isSelected())
							{
								clips[i].stop();
								clips[i].setFramePosition(0);
								toggleButtons[i][column].setForeground(Color.DARK_GRAY);
							}
						}
					}
					
					column++;
					
					if(column == toggleButtons[0].length)
					{
						column = 0;
					}
				}
			}
			catch(InterruptedException e) {}
		}
	}
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		if(e.getSource() == tempoBar)
		{
			tempo = tempoBar.getValue();
			tempoLabel.setText("Tempo: " + tempo);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == startStop)
		{
			currentlyPlaying = !currentlyPlaying;
			
			if(currentlyPlaying)
			{
				startStop.setText("Stop");
			}
			else
			{
				startStop.setText("Play");
			}
		}
		else if(source == clear)
		{
			for(int i = 0; i < toggleButtons.length; i++)
			{
				for(int j = 0; j < toggleButtons[i].length; j++)
				{
					toggleButtons[i][j].setSelected(false);
				}
			}
			
			reset();
		}
		else if(source == save) 
		{
			reset();
			saveSong();
		}
		else if(source == load) 
		{
			reset();
			loadFile();
		}
		else if(source == addCol)
		{
			addCols(1);
		}
		else if(source == removeCol)
		{
			removeCol(1);
		}
		else if(source == addNCol)
		{
			int numCols = Integer.parseInt(JOptionPane.showInputDialog("Enter # of Cols to Add"));
			addCols(numCols);
		}
		else if(source == removeNCol)
		{
			int numCols = Integer.parseInt(JOptionPane.showInputDialog("Enter # of Cols to Remove"));
			removeCol(numCols);
		}
		else
		{
			for(int i = 0; i < instrumentItems.length; i++)
			{
				if(source == instrumentItems[i])
				{
					loadSounds(instrumentItems[i].getText());
				}
			}
		}
	}
	
	public void addCols(int numCols)
	{
		int c = toggleButtons[0].length;
		for(int i = 0; i < toggleButtons.length; i++)
		{
			String note = clipNames[i].replaceAll("Sharp", "#");
			{
				toggleButtons[i][c] = new JToggleButton();
				toggleButtons[i][c].setText(note);
				toggleButtons[i][c].setFont(font);
				toggleButtons[i][c].setPreferredSize(new Dimension(30, 30));
				toggleButtons[i][c].setMargin(new Insets(0, 0, 0, 0));
				panel.add(toggleButtons[i][c]);
			}
		}
	}
	
	public void removeCol(int numCols)
	{
		for(int i = 0; i < toggleButtons.length; i++)
		{
			String note = clipNames[i].replaceAll("Sharp", "#");
			for(int j = 0; j < toggleButtons[i].length; j++)
			{
				toggleButtons[i][j] = new JToggleButton();
				toggleButtons[i][j].setText(note);
				toggleButtons[i][j].setFont(font);
				toggleButtons[i][j].setPreferredSize(new Dimension(30, 30));
				toggleButtons[i][j].setMargin(new Insets(0, 0, 0, 0));
				panel.add(toggleButtons[i][j]);
			}
		}
	}
	
	public void saveSong()
	{
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", ".txt");
		fileChooser.setFileFilter(filter);
		
		if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			
			try
			{
				String absolutePath = file.getAbsolutePath();
				
				if(absolutePath.contains(".txt"))
				{
					absolutePath.replace(".txt", "");
				}
				
				String currSong = tempo + " " + toggleButtons[0].length + "\n";
				String[] noteNames = {"c ", "b ", "a-", "a ", "g-", "g ", "f-", "f ", "e ", "d-", "d ", "c-", "c ",
						"b ", "a-", "a ", "g-", "g ", "f-", "f ", "e ", "d-", "d ", "c-", "c ", "b ", "a-", "a ", "g-",
						"g ", "f-", "f ", "e ", "d-", "d ", "c-", "c"};
				
				for(int i = 0; i < toggleButtons.length; i++)
				{
					currSong += noteNames[i];
					
					for(int j = 0; j < toggleButtons[0].length; j++)
					{
						if(toggleButtons[i][j].isSelected())
						{
							currSong += "x";
						}
						else
						{
							currSong += "-";
						}
					}
					currSong += "\n";
					
					BufferedWriter outputStream = new BufferedWriter(new FileWriter(absolutePath));
					outputStream.write(currSong.substring(0, currSong.length() - 1));
					outputStream.close();
				}
			}
			catch(IOException e) {}
		}
	}
	
	public void loadFile()
	{
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				File loadFile = fileChooser.getSelectedFile();
				BufferedReader input = new BufferedReader(new FileReader(loadFile));
				
				String temp = input.readLine();
				String[] tempoCol = temp.split(" ");
				tempo = Integer.parseInt(tempoCol[0]);
				tempoBar.setValue(tempo);
				int numCols = Integer.parseInt(tempoCol[1]);
				
				Character[][] song = new Character[toggleButtons.length][numCols];
				
				int r = 0;
				System.out.println("hi1");
				
				int count = 0;
				while((temp = input.readLine()) != null)
				{
					for(int c = 2; c < numCols + 2; c++)
					{
						song[r][c - 2] = temp.charAt(c);
					}
					r++;
					count++;
					
					if(count == 36)
					{
						break;
					}
				}
				
				System.out.println("hi2");
				
				for(int i = 0; i < song.length; i++)
				{
					for(int j = 0; j < song[0].length; j++)
					{
						System.out.println(song[i][j]);
					}
				}
				
				setNotes(song);
				
			}
			catch(/*IOException*/ Exception e) {}
		}
	}
	
	public void setNotes(Character[][] song)
	{
		System.out.println("hi");
		for(int i = 0; i < song.length; i++)
		{
			for(int j = 0; j < song[0].length; j++)
			{
				if(song[i][j] == 'x')
				{
					try
					{
						toggleButtons[i][j].setSelected(true);
					}
					catch(NullPointerException|ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
	}
	
	public void reset()
	{
		column = -1;
		currentlyPlaying = false;
		startStop.setText("Play");
	}
	
	public static void main(String[] args)
	{
		new MusicBox();
	}
}