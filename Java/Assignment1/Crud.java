import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Crud {
    private volatile Queue<Info> database = new LinkedList<>(); 

    private volatile Queue<Info> create = new LinkedList<>();
    private volatile Queue<Boolean> read = new LinkedList<>();
    private volatile Queue<Info> update = new LinkedList<>();
    private volatile Queue<Info> delete = new LinkedList<>();

    private int numThreads = 5;
    private Thread[] threads = new Thread[numThreads * 4];
    
    private volatile Lock createLock = new Bakery(numThreads * 4);
    private volatile Lock readLock = new Bakery(numThreads* 4);
    private volatile Lock updateLock = new Bakery(numThreads* 4);
    private volatile Lock deleteLock = new Bakery(numThreads* 4);
    private volatile Lock databaseLock = new Bakery(numThreads * 4);
    
    public Crud()
    {
        String ids[] = {"u123","u456","u789","u321","u654","u987","u147","u258","u369","u741","u852","u963"};
        String names[] = {"Thabo","Luke","James","Lunga","Ntando","Scott","Michael","Ntati","Lerato","Niel","Saeed","Rebecca"};
        for( int i= 0; i<20; i++ )
        {
            read.add(true);

            if(i<12) create.add( new Info( ids[i], names[i], 'c' ));

            if(i<4) update.add( new Info( ids[i+1], names[i+1], 'u'));
            if(i<4) delete.add( new Info( ids[i+2], names[i+2], 'd'));

            if(i>=9 && i<12){
                update.add( new Info( ids[i], names[i], 'u'));
                delete.add( new Info( ids[i], names[i], 'd'));
            }
        }
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < numThreads; j++)
            {
                if(i == 0)
                {
                    threads[j] = new Thread(new CreateThread(create, database, createLock, databaseLock));
                    threads[j].setName("Thread-" + j);
                }
                else if(i == 1)
                {
                    threads[j + numThreads * i] = new Thread(new ReadThread(read, database, readLock, databaseLock));
                    threads[j + numThreads * i].setName("Thread-" + (j + numThreads * i));
                }
                else if(i == 2)
                {
                    threads[j + numThreads * i] = new Thread(new UpdateThread(update, database, updateLock, databaseLock));
                    threads[j + numThreads * i].setName("Thread-" + (j + numThreads * i));
                }
                else if(i == 3)
                {
                    threads[j + numThreads * i] = new Thread(new DeleteThread(delete, database, deleteLock, databaseLock));
                    threads[j + numThreads * i].setName("Thread-" + (j + numThreads * i));
                }
            }
        }
    }
    public void run()
    {
        for(int i = 0; i < numThreads * 4; i++)
        {
            threads[i].start();
        }
    }
}