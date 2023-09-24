import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name: Timothy Whitaker
// Student Number: 22744968

public class Bakery implements Lock
{
	private volatile boolean[] flag;
	private volatile int[] label;
	int n;
	public Bakery(int n)
	{
		this.n = n;
		flag = new boolean[n];
		label = new int[n];
		for(int i = 0; i < n; i++)
		{
			flag[i] = false;
			label[i] = 0;
		}
	}
	public void lock()
	{
		int i = Integer.parseInt(Thread.currentThread().getName().split("-")[1]);
		flag[i] = true;
		label[i] = max() + 1;
		while(thereExists(i))
		{
			//wait
		}
	}
	private boolean thereExists(int i)
	{
		for (int k = 0; k < flag.length; k++) 
		{
			if(flag[k] && (label[k] < label[i] || (label[k] == label[i] && k < i)))
			{
				return true;
			}
		}
		return false;
	}
	private int max()
	{
		int max = -1;
		for (int i = 0; i < label.length; i++) 
		{
			if(label[i] > max)
			{
				max = label[i];
			}
		}
		return max;
	}
	public void unlock()
	{
		int i = Integer.parseInt(Thread.currentThread().getName().split("-")[1]);
		flag[i] = false;
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
