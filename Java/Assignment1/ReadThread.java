import java.util.Queue;
import java.util.concurrent.locks.Lock;
public class ReadThread implements Runnable
{
    private volatile Queue<Boolean> readQueue;
    private volatile Queue<Info> database;
    private volatile Lock readLock;
    private volatile Lock databaseLock;

    public ReadThread(Queue<Boolean> readQueue, Queue<Info> database, Lock readLock, Lock databaseLock) 
    {
        this.readQueue = readQueue;
        this.database = database;
        this.readLock = readLock;
        this.databaseLock = databaseLock;
    }
    @Override
    public void run() 
    {

        while (!readQueue.isEmpty()) 
        {
            readLock.lock();
            if (!readQueue.isEmpty()) 
            {
                boolean readRecord = readQueue.poll();
                readLock.unlock();
                // Simulate database reading logic
                if(readRecord)
                {
                    databaseLock.lock();
                    System.out.println(Thread.currentThread().getName() + " READ is waiting for request");
                    System.out.println(Thread.currentThread().getName() + " READ");
                    for (Info record : database)
                    {
                        System.out.println(record.id + ", " + record.name + ", " + record.practicals + ", " + record.assignments);
                    }
                    System.out.println(Thread.currentThread().getName() + " READ is sleeping");
                    databaseLock.unlock();
                }
                else
                {
                    System.out.println(Thread.currentThread().getName() + " READ failed");
                }
            }
            
            // Simulate sleeping
            try 
            {
                int sleepTime = (int) (Math.random() * 51) + 50;
                Thread.sleep(sleepTime);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
