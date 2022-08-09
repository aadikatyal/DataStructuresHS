public class Artist
{
    private String artistName;
    private int uniqueID;

    public Artist(String name)
    {
        artistName = name;
        uniqueID = artistName.hashCode();
    }

    public String getName()
    {
        return artistName;
    }

    public String toString()
    {
        return artistName;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        return uniqueID == ((Artist) obj).hashCode();
    }
}