public class City
{
    private String cityName;
    private int uniqueID;

    public City(String cityName)
    {
        this.cityName = cityName;
        uniqueID = cityName.hashCode();
    }

    public String getName()
    {
        return cityName;
    }

    public int getUniqueID()
    {
        return uniqueID;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        return this.hashCode() == ((City) obj).hashCode();
    }

    @Override
    public String toString()
    {
        return cityName;
    }
}