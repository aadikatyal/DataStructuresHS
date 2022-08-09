import java.io.*;
import java.util.*;

public class Runner
{
    private HashMap<Artist, HashSet<Edge>> artistMap;
    private Graph graph;
    private Stack<Artist> currentPath;
    private HashSet<Artist> visited;

    public Runner(String file)
    {
        artistMap = new HashMap<>();
        graph = new Graph();
        BufferedReader input = null;

        try
        {
			input = new BufferedReader(new FileReader(file));
			String text;

            System.out.println("Edges - Connecting artists with similar");

			while ((text = input.readLine()) != null)
            {
                String[] info = text.split(", ");
                Artist a1 = new Artist(info[0]);
                Artist a2 = new Artist(info[1]);

                graph.addArtist(a1);
                graph.addArtist(a2);
                graph.addEdge(a1, a2);
                graph.addEdge(a2, a1);

                if(!artistMap.containsKey(a1))
                {
                    artistMap.put(a1, new HashSet<Edge>());
                }

                if(!artistMap.containsKey(a2))
                {
                    artistMap.put(a2, new HashSet<Edge>());
                }

                artistMap.get(a1).add(new Edge(a1, a2));
                artistMap.get(a2).add(new Edge(a2, a1));
            }
		} 
        catch (IOException io) 
        {
			System.err.println("No file bruh");
        }

        for(Edge edge: graph.getEdges())
        {
            System.out.println("\t" + edge);
        }

        for(Artist startingArtist: graph.getArtists())
        {
            System.out.println("\t" + startingArtist);

            for(Artist endArtist: graph.getArtists())
            {
                //if(!startingArtist.equals(endArtist))
                if(startingArtist != endArtist)
                {
                    currentPath = new Stack<Artist>();
                    visited = new HashSet<Artist>();
                    dft(startingArtist, endArtist);
                }
            }
        }
    }

    public void dft(Artist currentArtist, Artist destination)
    {
        currentPath.push(currentArtist);
        visited.add(currentArtist);

        if(currentArtist == destination)
        {
            printCurrentPath();
        }
        else
        {
            for(Edge edge: graph.getEdges())
            {
                Artist artist = edge.getArtist();
                Artist similar = edge.getSimilar();

                if(visited.contains(artist) && !visited.contains(similar))
                {
                    dft(similar, destination);
                }

                if(visited.contains(similar) && !visited.contains(artist))
                {
                    dft(artist, destination);
                }
            }
        }
    }

    public void printCurrentPath()
    {
        String output = "";
        while (!currentPath.isEmpty())
        {
            output = currentPath.pop() + output;
            if (!currentPath.isEmpty())
            {
                output = " → " + output;
            }
        }
        System.out.println("\t" + output);
    }

    public static void main(String[] args)
    {
        new Runner("SimilarArtists.txt");
    }
}