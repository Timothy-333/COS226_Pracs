package Prac6;
import java.util.concurrent.atomic.AtomicReference;

public class LFQueue implements DataStructure
{
    AtomicReference<Node> head;
    AtomicReference<Node> tail;
    public LFQueue()
    {
        head = new AtomicReference<Node>(new Node(null));
        tail = new AtomicReference<Node>(head.get());
    }
    @Override
    public void enq(Job job) 
    {
        Node node = new Node(job);
        while (true)
        {
            Node last = tail.get();
            if (last.next.compareAndSet(null, node)) 
            {
                System.out.println("(IN) " + Thread.currentThread().getName() + " " + job.number + " " + job.hours);
                tail.compareAndSet(last, node);
                return;
            }
            Node next = last.next.get();
            if (last == tail.get())
            {
                if (next == null) 
                {
                    if (last.next.compareAndSet(next, node)) 
                    {
                        tail.compareAndSet(last, node);
                        System.out.println("(IN) " + Thread.currentThread().getName() + " " + job.number + " " + job.hours);
                        return;
                    } 
                    else
                        tail.compareAndSet(last, next);
                }
            }
        } 
    }
    @Override
    public Job deq() throws Exception
    {
        while (true) 
        {
            Node first = head.get();
            Node last = tail.get();
            Node next = first.next.get();
            if (first == head.get()) 
            {
                if (first == last) 
                {
                    if (next == null)
                    {
                        throw new Exception("Queue is empty");
                    }
                    tail.compareAndSet(last, next);
                } else 
                {
                    Job value = next.value;
                    if (head.compareAndSet(first, next))
                        return value;
                }
            }
        }
    }
    
}
