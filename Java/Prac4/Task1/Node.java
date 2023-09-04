package Task1;
public class Node extends Thread 
{
	boolean locked = false;
	Node next = null;
    private Printer p;

	Node(Printer _p)
	{
		this.p = _p ;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 5; i++) 
		{
			p.Print(i);
		}
	}
}
