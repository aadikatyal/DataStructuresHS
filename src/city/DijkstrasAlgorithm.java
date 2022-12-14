import java.util.*;

public class DijkstrasAlgorithm
{
    private ArrayList<City> cities;
    private ArrayList<Edge> edges;
    private HashSet<City> visitedCities;
    private HashSet<City> unvisitedCities;
    private HashMap<City, City> predecessors;
    private HashMap<City, Integer> distance;

    public DijkstrasAlgorithm(Graph graph)
    {
        this.cities = new ArrayList<>(graph.getCities());
        this.edges = new ArrayList<>(graph.getEdges());
    }

    public void createTravelsPath(City source)
    {
        visitedCities = new HashSet<>();
        unvisitedCities = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();

        distance.put(source, 0);
        unvisitedCities.add(source);

        while(unvisitedCities.size() > 0)
        {
            City city = getMinimum(unvisitedCities);
            visitedCities.add(city);
            unvisitedCities.remove(city);
            findMinimalDistances(city);
        }
    }

    public HashMap<City, City> getPred()
    {
        return predecessors;
    }
    

    public void findMinimalDistances(City tempCity) 
    {
        ArrayList<City> adjacentNodes = getNeighbors(tempCity);
        for (City targetCity : adjacentNodes) 
        {
            if (getShortestDistance(targetCity) > getShortestDistance(tempCity) + getDistance(tempCity, targetCity)) 
            {
                distance.put(targetCity, getShortestDistance(tempCity) + getDistance(tempCity, targetCity));
                predecessors.put(targetCity, tempCity);
                unvisitedCities.add(targetCity);
            }
        }
    }

    public int getDistance(City tempCity, City targetCity)
    {
        for (Edge edge : edges)
        {
            if ((edge.getStart().equals(tempCity) && edge.getDestination().equals(targetCity)) || (edge.getStart().equals(targetCity) && edge.getDestination().equals(tempCity)))
            {
                return edge.getDistance();
            }
        }
        throw new RuntimeException();
    }

    public ArrayList<City> getNeighbors(City tempCity) 
    {
        ArrayList<City> neighbors = new ArrayList<City>();
        for (Edge edge : edges) 
        {
            if (edge.getStart().equals(tempCity) && !wasVisited(edge.getDestination())) 
            {
                neighbors.add(edge.getDestination());
            }
            if (edge.getDestination().equals(tempCity) && !wasVisited(edge.getStart())) {
                neighbors.add(edge.getStart());
            }
        }
        return neighbors;
    }

    public City getMinimum(HashSet<City> cities) {
        City minimum = null;
        for (City city : cities) {
            if (minimum == null) {
                minimum = city;
            }
            else {
                if (getShortestDistance(city) < getShortestDistance(minimum)) {
                    minimum = city;
                }
            }
        }
        return minimum;
    }

    public boolean wasVisited(City city) {
        return visitedCities.contains(city);
    }

    public int getShortestDistance(City destination) {
        Integer dist = distance.get(destination);
        if (dist == null) {
            return Integer.MAX_VALUE;
        }
        return dist;
    }

    public ArrayList<City> getShortestPath(City targetCity) {
        ArrayList<City> connectingCities = new ArrayList<City>();
        City step = targetCity;
        if (predecessors.get(step) == null) {
            return null;
        }
        connectingCities.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            connectingCities.add(step);
        }
        Collections.reverse(connectingCities);
        return connectingCities;
    }
}