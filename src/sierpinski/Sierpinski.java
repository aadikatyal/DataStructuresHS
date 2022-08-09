import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sierpinski extends JPanel implements KeyListener 
{
    JFrame frame;
    ArrayList<Point> pointArrayList;
    Polygon polygon;

    public Sierpinski() 
	{
        frame = new JFrame("Sierpinski");
        frame.add(this);

        frame.setSize(1200,700);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        pointArrayList = new ArrayList<>();

        sierpinskiProcess();
        buildPoints(5);
    }

    public void sierpinskiProcess() 
	{
        pointArrayList.add(new Point(500, 100, Color.WHITE));
        pointArrayList.add(new Point(100, 600, Color.WHITE));
        pointArrayList.add(new Point(900, 600, Color.WHITE));

        polygon = new Polygon(new int[] {500,100,900}, new int[] {100,700,700}, 3);

        int x, y;

        do 
		{
            x = (int)(Math.random() * 801) + 100;
            y = (int)(Math.random() * 501) + 100;
        } while (!polygon.contains(x,y));
        
		pointArrayList.add(new Point(x,y, Color.RED));
    }

    public void buildPoints(int count) 
	{
        for (int i = 0; i < count; i++) 
		{
            Point vertex = pointArrayList.get((int) (Math.random() * 3));
            int newX = (vertex.getX() + pointArrayList.get(pointArrayList.size() - 1).getX()) / 2;
            int newY = (vertex.getY() + pointArrayList.get(pointArrayList.size() - 1).getY()) / 2;

            pointArrayList.add(new Point(newX, newY, Color.RED));
        }

        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) 
	{
        buildPoints(e.getKeyCode() - 48);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1200,700);

        for (Point p : pointArrayList) 
		{
            g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(),2,2);
        }
	}

    public class Point 
	{
        int x, y;
        Color color;

        public Point(int xCord, int yCord, Color col) 
		{
            x = xCord;
            y = yCord;
            color = col;
        }

        public int getX()
		{
            return x;
        }
        public int getY()
		{
            return y;
        }
        public Color getColor()
		{
            return color;
        }
    }

	public static void main (String[] args) 
	{
        new Sierpinski();
    }
}