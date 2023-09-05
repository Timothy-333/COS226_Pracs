package Task1;
public class Node extends Thread 
{
	volatile boolean locked = false;
	Node next = null;
	public int request;
	public String name;
    private Printer p;

	Node(Printer _p)
	{
		this.p = _p ;
		request = 0;
	}

	@Override
	public void run()
	{
		for (request = 0; request < 5; request++)
		{
			p.Print(request);
		}
	}
}
