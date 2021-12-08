package superlist;

public class ListNode<E> implements Comparable<E>
{
	private E value;
	private ListNode<E> nextValue;
	
	public ListNode(E value)
	{
		nextValue = null;
	}
	
	public E getValue()
	{
		return value;
	}
	
	public ListNode<E> getNextValue()
	{
		return nextValue;
	}
	
	public void setNextValue(ListNode<E> newValue)
	{
		nextValue = newValue;
	}
	
	public boolean hasNext()
	{
		
	}
	
	public boolean hasPrevious()
	{
		
	}
	
	public int compareTo(E o) 
	{
		return 0;
	}
}
