package superlist;

public class SuperList<E>
{
	private ListNode<E> rootValue, endValue, newValue;
	private int size;
	
	public SuperList()
	{
		rootValue = null;
		endValue = null;
		size = 0;
	}
	
	public SuperList(ListNode<E> rootValue)
	{
		this.newValue = rootValue;
	}
		
	public ListNode<E> getRootValue()
	{
		return rootValue;
	}
	
	public ListNode<E> getEndValue()
	{
		return endValue;
	}
	
	public void add(E value)
	{
		ListNode<E> newNode = new ListNode<E>(value);
		
		if(isEmpty())
		{
			rootValue = newNode;
			endValue = rootValue;
			size++;
		}
		else
		{
			endValue = setNext(newNode);
			
		}
		size++;
	}
	
	public ListNode<E> setNext()
	{
		
	}
	
	public void add(int pos, E value)
	{
		ListNode<E> newNode = new ListNode<E>(value);
		
		if(pos < 0 || pos > size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(isEmpty())
		{
			add(value);
		}
		else if(pos == size)
		{
				
		}
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
}
