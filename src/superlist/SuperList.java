package superlist;
import java.util.*;

public class SuperList<E>
{
	private ListNode<E> root;
	private ListNode<E> end;
	private int size;
	
	public SuperList()
	{
		root = null;
		end = null;
		size = 0;
	}
	
	public SuperList(E start)
	{
		root = new ListNode<E>(start);
		end = root;
		size = 1;
	}
	
	public E getRoot()
	{
		if(root == null)
		{
			return null;
		}
		
		return root.getValue();
	}
	
	public E getEnd()
	{
		if(end == null)
		{
			return null;
		}
		
		return end.getValue();
	}
	
	public void add(E value)
	{
		ListNode<E> val = new ListNode<E>(value);
		
		size++;
		
		if(root == null)
		{
			root = val;
			end = root;
		}
		else
		{
			end.setNext(val);
			val.setPrevious(end);
			end = val;
		}
	}
	
	public void push(E value)
	{
		add(value);
	}
	
	public void add(int index, E value)
	{
		ListNode<E> val = new ListNode<E>(value);
		
		if(index > size || index < 0)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		else if(index == 0)
		{
			if(root == null)
			{
				add(value);
			}
			else
			{
				root.setPrevious(val);
				val.setNext(root);
				root = val;
				size++;
			}
		}
		else if(index == size)
		{
			end.setNext(val);
			val.setPrevious(end);
			end = val;
			size++;
		}
		else
		{
			ListNode<E> curr = root;
			for(int i = 0; i < size; i++)
			{
				if(i == index)
				{
					ListNode<E> before = curr.getPrevious();
					curr.setPrevious(val);
					val.setNext(curr);
					val.setPrevious(before);
					before.setNext(val);
					break;
				}
				curr = curr.getNext();
			}
			size++;
		}
	}
	
	public E pop()
	{
		if(size == 0)
		{
			throw new EmptyStackException();
		}
		else if(size == 1)
		{
			E val = end.getValue();
			root = null;
			end = null;
			size--;
			return val;
		}
		else
		{
			E val = end.getValue();
			ListNode<E> newEnd = end.getPrevious();
			end = newEnd;
			end.setNext(null);
			size--;
			return val;
		}
	}
	
	public E poll()
	{
		if(size == 0)
		{
			return null;
		}
		else if(size == 1)
		{
			E val = root.getValue();
			root = null;
			end = null;
			size--;
			return val;
		}
		else
		{
			E val = root.getValue();
			ListNode<E> newRoot = root.getNext();
			root = newRoot;
			root.setPrevious(null);
			size--;
			return val;
		}
	}
	
	public E remove(int index)
	{
		if(index >= size || index < 0)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		else if(index == 0)
		{
			return poll();
		}
		else if(index == size-1)
		{
			return pop();
		}
		else
		{
			E returnVal = null;
			ListNode<E> curr = root;
			for(int i = 0; i < size; i++)
			{
				if(i == index)
				{
					returnVal = curr.getValue();
					ListNode<E> before = curr.getPrevious();
					ListNode<E> after = curr.getNext();
					before.setNext(after);
					after.setPrevious(before);
					break;
				}
				curr = curr.getNext();
			}
			size--;
			return returnVal;
		}
	}
	
	public E peekStack()
	{
		if(end != null)
		{
			return end.getValue();
		}
			
		return null;
	}
	
	public E peekQueue()
	{
		if(root != null)
		{
			return root.getValue();
		}
			
		return null;
	}

	public E get(int index)
	{
		if(index >= size || index < 0)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		else
		{
			ListNode<E> curr = root;
			for(int i = 0; i < size; i++)
			{
				if(i == index)
				{
					return curr.getValue();
				}
				curr = curr.getNext();
			}
			return null;
		}
	}
	
	public String toString()
	{
		String str = "[";
		ListNode<E> curr = root;
		
		for(int i = 0; i < size && curr != null; i++)
		{
			str = str + curr.getValue();
			
			if(i < size - 1)
			{
				str += ", ";
			}
				
			curr = curr.getNext();
		}
		
		str+="]";
		return str;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		if(size == 0)
		{
			return true;
		}
		return false;
	}
	
	public void clear()
	{
		root = null;
		end = null;
	}
	
	public int contains(E val)
	{
		ListNode<E> curr = root;
		for(int i = 0; i < size; i++)
		{
			if((curr.getValue()).equals(val))
			{
				return i;
			}
			curr = curr.getNext();
		}
		return -1;
	}
}

