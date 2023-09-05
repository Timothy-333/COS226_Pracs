package Task1;
import java.util.concurrent.locks.Lock;

public class Printer 
{
    volatile MCSQueue l = new MCSQueue(this);
	public void Print(int request)
	{
		System.out.println(Thread.currentThread().getName() + ":Request " + request + " printing request");
		l.lock(request);
		try {
			Thread.sleep((long) (Math.random()*800 + 200));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ":Request " + request + " printing Hello World");
		l.unlock();
	}
}
