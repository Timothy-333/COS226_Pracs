import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
public class PetersonLock implements Lock
{
    private boolean[] flag = new boolean[2];
    private int victim;
    @Override
    public void lock() 
    {
        int i = Integer.parseInt(Thread.currentThread().getName().split("-")[1]);
        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) 
        {
            //wait
        }
    }
    @Override
    public void unlock() 
    {
        int i = Integer.parseInt(Thread.currentThread().getName().split("-")[1]);
        flag[i] = false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
