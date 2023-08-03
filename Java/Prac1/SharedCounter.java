import java.util.concurrent.locks.Lock;

public class SharedCounter 
{
    private static int count = 0;
    private static Lock lock = new PetersonLock();
    public int getAndIncrement()
    {
        try
        {
            lock.lock();
            int temp = count;
            count = temp + 1;
        }
        finally
        {
            lock.unlock();
        }
        return count;
    }
}
