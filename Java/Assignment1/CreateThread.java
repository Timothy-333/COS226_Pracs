import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class CreateThread implements Runnable {
    private final Queue<Info> createQueue;
    private final Queue<Info> database;
    private final Lock createLock;
    private final Lock databaseLock;

    public CreateThread(Queue<Info> createQueue, Queue<Info> database, Lock createLock, Lock databaseLock) {
        this.createQueue = createQueue;
        this.database = database;
        this.createLock = createLock;
        this.databaseLock = databaseLock;
    }

    @Override
    public void run() {
        while (!createQueue.isEmpty()) 
        {
            synchronized (createLock)
            {
                if (!createQueue.isEmpty()) 
                {
                    Info newRecord = createQueue.poll();
                    // Simulate database creation logic
                    synchronized (databaseLock) 
                    {
                        database.add(newRecord);
                    }
                    System.out.println(Thread.currentThread().getName() + " CREATE success: " + newRecord.id + ", " + newRecord.name);
                }
            }
            
            // Simulate sleeping
            try {
                int sleepTime = (int) (Math.random() * 51) + 50;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
