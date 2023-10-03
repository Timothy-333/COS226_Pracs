package Task2;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Timeout implements Lock 
{
    private Printer p;
    static Node AVAILABLE = new Node(null);
    AtomicReference<Node> tail;
    ThreadLocal<Node> myNode;
    public Timeout(Printer p)
    {
        this.p = p;
        tail = new AtomicReference<Node>(null);
        myNode = new ThreadLocal<Node>()
        {
            protected Node initialValue()
            {
                return new Node(p);
            }
        };
    }
    public boolean lock(long timeout, int request)
    {
        Node node = new Node(p);
        node.name = Thread.currentThread().getName();
        node.request = request;
        myNode.set(node);
        node.prev = null;
        Node myPred = tail.getAndSet(node);

        if(myPred == null || myPred.prev == AVAILABLE)
        {
            if(myPred != null)
            {
                myPred.next = node;
            }
            return true;
        }
        myPred.next = node;
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start < timeout)
        {
            Node prePred = myPred.prev;
            if(prePred == AVAILABLE)
            {
                return true;
            }
            else if(prePred != null)
            {
                myPred = prePred;
            }
        }
        System.out.println(Thread.currentThread().getName() + ":Request " + request + " timed out");
        if(!tail.compareAndSet(node, myPred))
        {
            node.prev = myPred;
        }
        return false;
    }
    public void unlock()
    {
        Node node = myNode.get();
        // print queue
        Node temp = node;
        System.out.print("Queue: ");
        while(temp.next != null)
        {
            if(temp != AVAILABLE)
            {
                System.out.print(temp.name + ":Request " + temp.request + " -> ");
            }
            temp = temp.next;
        }
        System.out.println(temp.name + ":Request " + temp.request);
        if(!tail.compareAndSet(node, null))
        {
            node.prev = AVAILABLE;
        }
    }
    public void lock()
    {
        throw new UnsupportedOperationException();
    }
    public void lockInterruptibly() throws InterruptedException
    {
        throw new UnsupportedOperationException();
    }
    public boolean tryLock()
    {
        throw new UnsupportedOperationException();
    }
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        throw new UnsupportedOperationException();
    }
    public Condition newCondition()
    {
        throw new UnsupportedOperationException();
    }
}
