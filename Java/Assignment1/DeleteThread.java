import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class DeleteThread implements Runnable
{
    private volatile Queue<Info> deleteQueue;
    private volatile Queue<Info> database;
    private volatile Lock deleteLock;
    private volatile Lock databaseLock;
    
    public DeleteThread(Queue<Info> deleteQueue, Queue<Info> database, Lock deleteLock, Lock databaseLock) 
    {
        this.deleteQueue = deleteQueue;
        this.database = database;
        this.deleteLock = deleteLock;
        this.databaseLock = databaseLock;
    }
    
    @Override
    public void run() 
    {
        while (!deleteQueue.isEmpty()) 
        {
            deleteLock.lock();
            if (!deleteQueue.isEmpty()) 
            {
                Info deleteRecord = deleteQueue.poll();
                boolean found = false;
                // Simulate database deletion logic
                databaseLock.lock();
                System.out.println(Thread.currentThread().getName() + " DELETE is waiting for request");
                for (Info record : database) 
                {
                    if (record.id.equals(deleteRecord.id) || record.name.equals(deleteRecord.name)) 
                    {
                        database.remove(record);
                        found = true;
                        break;
                    }
                }
                if(!found)
                {
                    System.out.println(Thread.currentThread().getName() + " DELETE failed " + deleteRecord.id + ", " + deleteRecord.name + " attempts:" + deleteRecord.attempt);
                    if(deleteRecord.attempt < 3)
                    {
                        deleteRecord.attempt++;
                        deleteQueue.add(deleteRecord);
                    }
                }
                else
                    System.out.println(Thread.currentThread().getName() + " DELETE success " + deleteRecord.id + ", " + deleteRecord.name);
                System.out.println(Thread.currentThread().getName() + " DELETE is sleeping");
                databaseLock.unlock();
            }
            deleteLock.unlock();
            
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
