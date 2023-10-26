package Prac6;

import java.util.concurrent.atomic.AtomicReference;

public class Node 
{
    public Job value;
    public AtomicReference<Node> next;

    public Node(Job value) 
    {
        this.value = value;
        next = new AtomicReference<Node>(null);
    }

}
