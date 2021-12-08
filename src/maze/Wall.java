package maze;

import java.awt.*;

public class Wall
{
	private int[] rows, columns;
	private Color color;
	private String wallType;
	private int distanceFromCurrentPos;
	
	public Wall(int columns[], int rows[], Color color, String wallType, int distanceFromCurrentPos)
	{
		this.rows = rows;
		this.columns = columns;
		this.color = color;
		this.wallType = wallType;
		this.distanceFromCurrentPos = distanceFromCurrentPos;
	}

	public String getType()
	{
		return wallType;
	}

	public Polygon getPoly()
	{
		return new Polygon(columns, rows, columns.length);
	}

	public Paint getPaint()
	{
		int r = color.getRed();
		int g = color.getBlue();
		int b = color.getBlue();
		
		int endR = r - distanceFromCurrentPos;
		int endG = g - distanceFromCurrentPos;
		int endB = b - distanceFromCurrentPos;
		
		if(r < 0)
		{
			r = 0;
		}
			
		if(g < 0)
		{
			g = 0;
		}
			
		if(b < 0)
		{
			b = 0;
		}
			
		if(endR < 0)
		{
			endR = 0;
		}
			
		if(endG < 0)
		{
			endG = 0;
		}
		
		if(endB < 0)
		{
			endB = 0;
		}
		
		if(wallType.equals("Left"))
		{
			return new GradientPaint(columns[0],rows[1], new Color(r,g,b), columns[1], rows[1], new Color(endR, endG, endB));
		}

		if(rows.length == 4)
		{
			return new GradientPaint(columns[0],rows[0], new Color(r,g,b), columns[0], rows[1], new Color(endR, endG, endB));
		}
		return new GradientPaint(columns[0],rows[0], new Color(r,g,b), columns[2], rows[0], new Color(endR, endG, endB));
	}

}




