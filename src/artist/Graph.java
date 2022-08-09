

import java.util.HashSet;

public class Graph 
{
	private HashSet<Artist> artists;
	private HashSet<Edge> edges;

	public Graph()
	{
		artists = new HashSet<>();
		edges = new HashSet<>();
	} 
	
	public HashSet<Artist> getArtists()
	{
		return artists;
	}
	
	public HashSet<Edge> getEdges()
	{
		return edges;
	}
	
	public void addArtist(Artist a)
	{
		artists.add(a);
	}
	
	public void addEdge(Artist source, Artist destination)
	{
		edges.add(new Edge(source, destination));
	}
}