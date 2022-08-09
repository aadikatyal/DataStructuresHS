public class Edge
{
    private City start, destination;
    private int distance, uniqueID;

    public Edge(City city1, City city2, int distance)
    {
        start = city1;
        destination = city2;
        this.distance = distance;
        uniqueID = start.hashCode() + destination.hashCode();
    }

    public int getUniqueID()
    {
        return uniqueID;
    }

    public City getDestination()
    {
        return destination;
    }

    public City getStart()
    {
        return start;
    }

    public int getDistance()
    {
        return distance;
    }

    public String toString()
    {
        return start + " to " + destination + ": " + distance;
    }

    public boolean equals(Object obj)
    {
        if(this.equals(obj))
        {
            return true;
        }

        return this.hashCode() == ((Edge) obj).hashCode();
    }
}