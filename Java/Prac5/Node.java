package Prac5;

import java.util.concurrent.locks.ReentrantLock;

public class Node
{
    public final ReentrantLock l = new ReentrantLock();
    public boolean marked = false;
    Node(Person person)
    {
        this.person = person;
        this.key = person.hashCode();
    }
    Node(int key)
    {
        this.key = key;
    }
    public void lock()
    {
        l.lock();
    }
    public void unlock()
    {
        l.unlock();
    }
	public Person person;
    public int key;
    volatile public Node next;
}
