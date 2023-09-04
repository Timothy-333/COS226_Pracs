package Task1;
import java.util.concurrent.locks.Lock;

public class Printer 
{
    volatile Lock l = new MCSQueue(this);
	public void Print(int request)
	{
		System.out.println(Thread.currentThread().getName() + ":Request " + request + " printing request");
		l.lock();
		System.out.println(Thread.currentThread().getName() + ":Request " + request + " printing Hello World");
		l.unlock();
	}
}
