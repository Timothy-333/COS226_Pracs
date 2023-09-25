import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class CreateThread implements Runnable 
{
    private volatile Queue<Info> createQueue;
    private volatile Queue<Info> database;
    private volatile Lock createLock;
    private volatile Lock databaseLock;

    public CreateThread(Queue<Info> createQueue, Queue<Info> database, Lock createLock, Lock databaseLock) 
    {
        this.createQueue = createQueue;
        this.database = database;
        this.createLock = createLock;
        this.databaseLock = databaseLock;
    }

    @Override
    public void run() 
    {
        while (!createQueue.isEmpty()) 
        {
            createLock.lock();
            if (!createQueue.isEmpty()) 
            {
                Info newRecord = createQueue.poll();
                createLock.unlock();
                // Simulate database creation logic
                databaseLock.lock();
                System.out.println(Thread.currentThread().getName() + " CREATE is waiting for request");
                database.add(newRecord);
                System.out.println(Thread.currentThread().getName() + " CREATE success " + newRecord.id + ", " + newRecord.name);
                System.out.println(Thread.currentThread().getName() + " CREATE is sleeping");
                databaseLock.unlock();             
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
