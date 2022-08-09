import java.util.*;

public class Graph
{
    private HashSet<City> cities;
    private HashSet<Edge> edges;
    
    public Graph(HashSet<City> cities, HashSet<Edge> edges)
    {
        this.cities = cities;
        this.edges = edges;
    }

    public HashSet<City> getCities() 
    {
        return cities;
    }

    public HashSet<Edge> getEdges()
    {
        return edges;
    }
}