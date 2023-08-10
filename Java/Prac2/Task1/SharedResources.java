import java.util.concurrent.locks.Lock;

public class SharedResources 
{
    static Lock l = new Filter(5);

	public void access()
	{
		try
		{
			l.lock();
			int sleepTime = (int)(Math.random() * 801) + 200;
			Thread.sleep(sleepTime);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println(Thread.currentThread().getName() + ":---------------------------------- DONE");
			l.unlock();
		}
	}
}
