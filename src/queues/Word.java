package queues;

public class Word implements Comparable<Word>
{
	private String word;
	
	public Word(String word)
	{
		this.word = word;
	}
	
	public String toString()
	{
		return word;
	}

	@Override
	public int compareTo(Word otherWord) 
	{
		// if first > second --> swap, else no change [if negative no change]
		
		return word.compareTo(otherWord.word);
	}
}