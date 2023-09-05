package Task1;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MCSQueue implements Lock
{
    Printer p;
    AtomicReference<Node> tail;
    ThreadLocal<Node> myNode;
    public MCSQueue(Printer p)
    {
        tail = new AtomicReference<Node>();
        myNode = new ThreadLocal<Node>()
        {
            protected Node initialValue()
            {
                return new Node(p);
            }
        };
        this.p = p;
    }
    public void lock(int request)
    {
        Node node = myNode.get();
        node.name = Thread.currentThread().getName();
        Node pred = tail.getAndSet(node);
        node.request = request;
        if(pred != null)
        {
            node.locked = true;
            pred.next = node;
            while(node.locked){};
        }
    }
    public void unlock()
    {
        Node node = myNode.get();
        if(node.next == null)
        {
            if(tail.compareAndSet(node, null))
                return;
            while(node.next == null);
        }
        //print queue
        Node temp = node.next;
        node.next.locked = false;
        System.out.print("Queue: ");
        while(temp.next != null)
        {
            System.out.print(temp.name + ":Request " + temp.request + " -> ");
            temp = temp.next;
        }
        System.out.println(temp.name + ":Request " + temp.request);
        node.next = null;
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
