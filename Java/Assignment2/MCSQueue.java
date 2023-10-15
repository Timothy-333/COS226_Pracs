import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MCSQueue implements Lock
{
    AtomicReference<Node> tail;
    ThreadLocal<Node> myNode;
    public MCSQueue()
    {
        tail = new AtomicReference<Node>();
        myNode = new ThreadLocal<Node>()
        {
            protected Node initialValue()
            {
                return new Node();
            }
        };
    }
    public void lock()
    {
        Node node = myNode.get();
        Node pred = tail.getAndSet(node);
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
        node.next.locked = false;
        node.next = null;
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
