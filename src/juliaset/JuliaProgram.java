package julia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JuliaProgram extends JPanel implements AdjustmentListener, ActionListener, MouseListener
{
	private JFrame frame;
	private double aVal, bVal, zoom, brightnessLevel, saturationLevel, hueLevel;
	private JPanel sliderPanel, labelPanel, bigPanel, btnPanel;
	private JScrollBar aBar, bBar, zoomBar, brightnessBar, saturationBar, hueBar;
	private JLabel aLabel, bLabel, zoomLabel, brightnessLabel, saturationLabel, hueLabel;
	private JButton btnReset, btnSaveImage;
	private JFileChooser fileChooser;
	
	private BufferedImage image;
	private int pixelSize = 1;
	
	public JuliaProgram()
	{
		frame = new JFrame("Julia Set");
		frame.add(this);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		
		btnReset = new JButton("Reset Image");
		btnSaveImage = new JButton("Save Image");
		btnReset.addActionListener(this);
		
		btnSaveImage.setEnabled(true);
		btnSaveImage.addActionListener(this);
		
		labelPanel = new JPanel();
		sliderPanel = new JPanel();
		bigPanel = new JPanel();
		btnPanel = new JPanel();
		
		aBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		aBar.addAdjustmentListener(this);
		aBar.addMouseListener(this);
		aVal = aBar.getValue();
		
		bBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
		bBar.addAdjustmentListener(this);
		bBar.addMouseListener(this);
		bVal = bBar.getValue() / 1000.0;
		
		zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 10, 0, 0, 100);
		zoomBar.addAdjustmentListener(this);
		zoomBar.addMouseListener(this);
		zoom = zoomBar.getValue() / 10.0;
		
		brightnessBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
		brightnessBar.addAdjustmentListener(this);
		brightnessBar.addMouseListener(this);
		brightnessLevel = brightnessBar.getValue() / 1000.0;
		
		saturationBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
		saturationBar.addAdjustmentListener(this);
		saturationBar.addMouseListener(this);
		saturationLevel = saturationBar.getValue() / 1000.0;
		
		hueBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
		hueBar.addAdjustmentListener(this);
		hueBar.addMouseListener(this);
		hueLevel = hueBar.getValue() / 1000.0;
		
		aLabel = new JLabel("A: " + aVal);
		bLabel = new JLabel("B: " + bVal);
		zoomLabel = new JLabel("Zoom: " + zoom);
		brightnessLabel = new JLabel("Brightness: " + brightnessLevel);
		saturationLabel = new JLabel("Saturation: " + saturationLevel);
		hueLabel = new JLabel("Hue: " + hueLevel);
				
		GridLayout grid1 = new GridLayout(6, 1);
		GridLayout grid2 = new GridLayout(3, 2);
		
		labelPanel.setLayout(grid1);
		sliderPanel.setLayout(grid1);
		btnPanel.setLayout(grid2);
		
		labelPanel.add(aLabel);
		sliderPanel.add(aBar);
		
		labelPanel.add(bLabel);
		sliderPanel.add(bBar);
		
		labelPanel.add(zoomLabel);
		sliderPanel.add(zoomBar);
		
		labelPanel.add(brightnessLabel);
		sliderPanel.add(brightnessBar);
		
		labelPanel.add(saturationLabel);
		sliderPanel.add(saturationBar);
		
		labelPanel.add(hueLabel);
		sliderPanel.add(hueBar);
		
		bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(labelPanel, BorderLayout.WEST);
		bigPanel.add(sliderPanel, BorderLayout.CENTER);
		bigPanel.add(btnPanel, BorderLayout.EAST);
		
		frame.add(bigPanel, BorderLayout.SOUTH);

	    btnReset.setSize(10, 10);
	    btnReset.setText("Reset");
	    btnPanel.add(btnReset, BorderLayout.EAST);
	    
	    btnSaveImage.setSize(10, 10);
	    btnSaveImage.setText("Save Image");
	    btnPanel.add(btnSaveImage, BorderLayout.WEST);
	    		
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(drawJulia(), 0, 0, null);
	}
	
	public BufferedImage drawJulia()
	{
		int width = frame.getWidth();
		int height = frame.getHeight();
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i += pixelSize)
		{
			for(int j = 0; j < height; j += pixelSize)
			{
				float maxIterations = 300f;
				double zx = 1.5 * ((i - width / 2.0) / (0.5 * zoom * width));
				double zy = (j - height / 2.0) / (0.5 * zoom * height);
				
				while(((zx * zx) + (zy * zy)) < 6 && maxIterations > 0)
				{
					double val = ((zx * zx) - (zy * zy)) + aVal;
					zy = 2 * zx * zy + bVal;
					zx = val;
					maxIterations--;
				}
				
				int c;
				if(maxIterations > 0)
				{
					c = Color.HSBtoRGB((float) hueLevel * (maxIterations / 300f) % 1, (float) saturationLevel, (float) brightnessLevel);
				}
				else
				{
					c = Color.HSBtoRGB(maxIterations / 300f, 1, 0);
				}
				image.setRGB(i, j, c);
			}
		}
		
		return image;
	}
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource() == aBar)
		{
			aVal = aBar.getValue() / 1000.0;
			aLabel.setText("A: " + aVal + "\t\t");
		}
		else if(e.getSource() == bBar)
		{
			bVal = bBar.getValue() / 1000.0;
			bLabel.setText("B: " + bVal + "\t\t");
		}
		else if(e.getSource() == zoomBar)
		{
			zoom = zoomBar.getValue() / 10.0;
			zoomLabel.setText("Zoom: " + zoom + "\t\t");
		}
		else if(e.getSource() == brightnessBar)
		{
			brightnessLevel = brightnessBar.getValue() / 1000.0;
			brightnessLabel.setText("Brightness: " + brightnessLevel + "\t\t");
		}
		else if(e.getSource() == saturationBar)
		{
			saturationLevel = saturationBar.getValue() / 1000.0;
			saturationLabel.setText("Saturation: " + saturationLevel + "\t\t");
		}
		else if (e.getSource() == brightnessBar)
        {
			brightnessLevel = brightnessBar.getValue() / 1000.0;
			brightnessLabel.setText("Brightness: " + brightnessLevel + "\t\t");
        }
		else if(e.getSource() == hueBar)
		{
			hueLevel = hueBar.getValue() / 1000.0;
			hueLabel.setText("Hue: " + hueLevel + "\t\t");
		}
		
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnReset)
		{
			aVal = 0.0;
			bVal = 0.0;
			zoom = 1.0;
			
			brightnessLevel = 0.5;
			saturationLevel = 0.5;
			hueLevel = 0.5;
			
			aBar.setValue(0);
			bBar.setValue(0);
			zoomBar.setValue(10);
			brightnessBar.setValue(500);
			saturationBar.setValue(500);
			hueBar.setValue(500);
			
			repaint();
		}
		
		if(e.getSource() == btnSaveImage)
		{
			FileFilter filter = new FileNameExtensionFilter("*.png","png");
			fileChooser.setFileFilter(filter);
            if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                try
                {
                    String st = file.getAbsolutePath();
                    if(st.indexOf(".png") >= 0)
                    {
                    	st = st.substring(0,st.length()-4);
                    }
                    ImageIO.write(image, "png", new File(st + ".png"));
                }
                catch(IOException exception) {}
            }
		}
	}
	
	
	public static void main(String[] args)
	{
		JuliaProgram juliaProgram = new JuliaProgram();
	}

	@Override public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		pixelSize = 3;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		pixelSize = 1;
		repaint();
	}

	@Override public void mouseEntered(MouseEvent e) {}

	@Override public void mouseExited(MouseEvent e) {}
}
