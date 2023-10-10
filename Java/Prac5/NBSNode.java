package Prac5;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.locks.ReentrantLock;

public class NBSNode 
{
    public final ReentrantLock l = new ReentrantLock();
    public boolean marked = false;
    public Person person;
    public int key;
    volatile public AtomicMarkableReference<NBSNode> next;
    NBSNode(Person person)
    {
        this.person = person;
        this.key = person.hashCode();
        next = new AtomicMarkableReference<>(null, false);
    }
    NBSNode(int key)
    {
        this.key = key;
        next = new AtomicMarkableReference<>(null, false);
    }
    public void lock()
    {
        l.lock();
    }
    public void unlock()
    {
        l.unlock();
    }
}
