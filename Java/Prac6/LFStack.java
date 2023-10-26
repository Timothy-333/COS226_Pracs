package Prac6;

import java.util.concurrent.atomic.AtomicReference;

public class LFStack implements DataStructure
{
    AtomicReference<StackNode> top = new AtomicReference<StackNode>(null);
    static final int MIN_DELAY = 500;
    static final int MAX_DELAY = 1500;
    Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
    @Override
    public void enq(Job value) 
    {
        StackNode StackNode = new StackNode(value);
        while (true) 
        {
            if (tryPush(StackNode)) 
            {
                System.out.println("(IN) " + Thread.currentThread().getName() + " " + value.number + " " + value.hours);
                return;
            } 
            else backoff.backoff();
        }
    }
    @Override
    public Job deq() throws Exception
    {
        while (true)
        {
            StackNode returnStackNode = tryPop();
            if (returnStackNode != null)
                return returnStackNode.value;
            else
                backoff.backoff();
        }
    }
    public boolean tryPush(StackNode StackNode)
    {
        StackNode oldTop = top.get();
        StackNode.next = oldTop;
        return(top.compareAndSet(oldTop, StackNode));
    }
    protected StackNode tryPop() throws Exception 
    {
        StackNode oldTop = top.get();
        if (oldTop == null)
        throw new Exception("Stack is empty");
        StackNode newTop = oldTop.next;
        if (top.compareAndSet(oldTop, newTop))
        return oldTop;
        else
        return null;
    }
}
