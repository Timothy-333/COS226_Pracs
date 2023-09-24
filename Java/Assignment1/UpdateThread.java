import java.util.Queue;
import java.util.concurrent.locks.Lock;
public class UpdateThread implements Runnable
{
    private volatile Queue<Info> updateQueue;
    private volatile Queue<Info> database;
    private volatile Lock updateLock;
    private volatile Lock databaseLock;

    public UpdateThread(Queue<Info> updateQueue, Queue<Info> database, Lock updateLock, Lock databaseLock) 
    {
        this.updateQueue = updateQueue;
        this.database = database;
        this.updateLock = updateLock;
        this.databaseLock = databaseLock;
    }

    @Override
    public void run() 
    {
        while (!updateQueue.isEmpty()) 
        {
            updateLock.lock();
            if (!updateQueue.isEmpty()) 
            {
                Info updateRecord = updateQueue.poll();
                boolean found = false;
                // Simulate database update logic
                databaseLock.lock();
                System.out.println(Thread.currentThread().getName() + " UPDATE is waiting for request");
                for (Info record : database) 
                {
                    if (record.id.equals(updateRecord.id) && record.name.equals(updateRecord.name))
                    {
                        found = true;
                        record.practicals = updateRecord.practicals;
                        record.assignments = updateRecord.assignments;
                        break;
                    }
                }
                if(!found)
                {
                    System.out.println(Thread.currentThread().getName() + " UPDATE failed " + updateRecord.id + ", " + updateRecord.name + " attempts:" + updateRecord.attempt);
                    if(updateRecord.attempt < 3)
                    {
                        updateRecord.attempt++;
                        updateQueue.add(updateRecord);
                    }
                }                
                else
                    System.out.println(Thread.currentThread().getName() + " UPDATE success " + updateRecord.id + ", " + updateRecord.name);
                System.out.println(Thread.currentThread().getName() + " UPDATE is sleeping");
                databaseLock.unlock();
            }
            updateLock.unlock();
            
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
