package superlist;

public class ListNode<E>
{
	private E value;
	private ListNode<E> next;
	private ListNode<E> previous;
	
	public ListNode(E e)
	{
		value = e;
	}
	
	public E getValue()
	{
		return value;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setNext(ListNode<E> e)
	{
		next = e;
	}
	
	public boolean hasNext()
	{
		if(next == null)
		{
			return false;
		}
			
		else
		{
			return true;
		}
	}


	public ListNode<E> getPrevious()
	{
		return previous;
	}
	
	public void setPrevious(ListNode<E> e)
	{
		previous = e;
	}
	
	public boolean hasPrevious()
	{
		if(previous == null)
		{
			return false;
		}	
		else
		{
			return true;
		}
	}
}