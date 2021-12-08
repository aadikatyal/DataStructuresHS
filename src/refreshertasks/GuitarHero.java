package refreshertasks;

import java.io.*;

public class GuitarHero 
{
    public GuitarHero() 
    {
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader("/Users/aadikatyal/eclipse-workspace/DS/src/refreshertasks/GuitarInput.txt"));
            String text;
            String[][] outputGrid = null;

            String[] notes = {"G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G","F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E"};
            int[][] helper = { {29, 24, 19, 14, 10, 5}, {28, 23, 18, 13, 9, 4}, {27, 22, 17, 12, 8, 3}, {26, 21, 16, 11, 7, 2}, {25, 20, 15, 10, 6, 1} };

            int row = 0;

            while((text=input.readLine()) != null) 
            {
                String[] song = text.split(",");

                if(outputGrid == null) 
                {
                    outputGrid = new String[30][song.length + 1];
                    outputGrid[0][0] = "Measure";

                    for (int i = 1; i <= notes.length; i++) 
                    {
                        outputGrid[i][0] = notes[i-1];
                    }
                    for (int j = 0; j <= song.length; j++) 
                    {
                        outputGrid[0][j] = ""+j;
                    }
                }

                for (int measure = 0; measure < song.length; measure++)
                {
                    for (int col = 0; col < 6; col++) 
                    {
                        if(song[measure].charAt(col) == '*' || song[measure].charAt(col) == 'o') 
                        {
                            outputGrid[helper[row][col]][measure + 1] = "O";
                        }
                    }
                }
                row++;
            }
            output(outputGrid);

        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("No file bruh");
        } 
        catch (IOException e) {}
    }

    public void output(String[][] output) 
    {
        for (int row = 0; row < output.length; row++) 
        {
            for (int col = 0; col < output[0].length; col++)
            {
                if (output[row][col] == null) 
                {
                    System.out.print("\t\t");
                } 
                else
                {
                    System.out.print(output[row][col] + "\t\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        GuitarHero guitarHero = new GuitarHero();
    }
}
