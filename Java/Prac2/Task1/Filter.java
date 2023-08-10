import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name: Timothy Whitaker
// Student Number: 22744968

public class Filter implements Lock
{
	private volatile int[] level;
	private volatile int[] victim;
	int n;
	public Filter(int n)
	{
		level = new int[n];
		victim = new int[n];
		this.n = n;
		for(int i = 0; i < n; i++)
		{
			level[i] = 0;
		}
	}
	public void lock()
	{
		int me = Integer.parseInt(Thread.currentThread().getName().split("-")[1]);
		for (int i = 1; i < n; i++)
		{
            level[me] = i;
            victim[i] = me;
			System.out.println("Thread-" + me + ": level[" + me + "] = " + i + ", victim[" + i + "] = " + me);
            while(sameOrHigher(me, i) && victim[i] == me)
			{
				//spin
			}
        }
	}
	private boolean sameOrHigher(int me, int other)
	{
		for(int k = 0; k < n; k++)
		{
			if(k!=me && level[k] >= level[me])
				return true;
		}
		return false;
	}
	public void unlock()
	{
		int me = Integer.parseInt(Thread.currentThread().getName().split("-")[1]);
		level[me] = 0;
	}
	public void lockInterruptibly() throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock()
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}

}
