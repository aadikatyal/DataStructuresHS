package refreshertasks;

import java.io.*;
import java.util.ArrayList;

public class Matrix
{
	private int[][] matrix1, matrix2;
	public Matrix()
	{
		File name = new File("/Users/aadikatyal/eclipse-workspace/DS/src/refreshertasks/MatrixInput.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text;
			while((text = input.readLine()) != null)
			{
				String outerMatrices[] = text.split("\t");
				matrix1 = makeMatrix(outerMatrices[0]);
				matrix2 = makeMatrix(outerMatrices[1]);
				
				printMatrix(matrix1, 1);
				printMatrix(matrix2, 2);
				function("Sum");
				function("Difference");
				function("Product");
				System.out.println("_____________________________\n");
			}
		}
		catch (IOException io)
		{
			System.err.println("No file bruh");
		}
	}

	public int[][] makeMatrix(String str)
	{
		str = str.substring(2, str.length() - 2);
		String rows[] = str.split("},\\{");
		int numCols = rows[0].split(",").length;
		int matrix[][] = new int[rows.length][numCols];
		
		for(int r = 0; r < rows.length; r++) 
		{
		    for(int c = 0; c < numCols; c++) 
		    {
		    	matrix[r][c] = Integer.parseInt(rows[r].split(",")[c]);
		    }
		}
		return matrix;
	}
	
	public void printMatrix(int[][] matrix, int matrixNum) 
	{
		System.out.println("Matrix #" + matrixNum + ":");
		for(int r = 0; r < matrix.length; r++)
		{
			for(int c = 0; c < matrix[0].length; c++)
			{
				System.out.print(matrix[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void function(String operation) 
	{
		System.out.println(operation + ":");
		
		if(operation.equals("Sum") || operation.equals("Difference"))
		{
			if(matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length)
			{
				int newMatrix[][] = new int[matrix1.length][matrix1[0].length];
				
				for(int r = 0; r < matrix1.length; r++)
				{
					for(int c = 0; c < matrix1[0].length; c++)
					{
						if(operation.equals("Sum"))
						{
							newMatrix[r][c] = matrix1[r][c] + matrix2[r][c];
						}
						else
						{
							newMatrix[r][c] = matrix1[r][c] - matrix2[r][c];
						}
						System.out.print(newMatrix[r][c] + " ");
					}
					System.out.println();
				}
				System.out.println();
			}
			else
			{
				System.out.println(operation + " is not possible\n");
			}
		}
		else
		{
			if(matrix1[0].length == matrix2.length)
			{
				int newMatrix[][] = new int[matrix1.length][matrix2[0].length];
				
				for(int r = 0; r < newMatrix.length; r++)
				{
					for(int c = 0; c < newMatrix[0].length; c++)
					{
						for(int i = 0; i < matrix1[0].length; i++) 
						{
	                        newMatrix[r][c] += matrix1[r][i] * matrix2[i][c];
						}
						System.out.print(newMatrix[r][c] + " ");
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println(operation + " is not possible");
			}
		}
	}

	public static void main(String args[])
	{
		Matrix matrix = new Matrix();
	}
}