package Prac5;

import java.util.concurrent.atomic.AtomicMarkableReference;

class Window 
{
    public NBSNode pred, curr;

    Window(NBSNode myPred, NBSNode myCurr) 
    {
        pred = myPred;
        curr = myCurr;
    }
}
public class NBS implements ListInterface
{
    NBSNode head;
    NBS()
    {
        head = new NBSNode(Integer.MIN_VALUE);
        head.next = new  AtomicMarkableReference<>(new NBSNode(Integer.MAX_VALUE), false);
    }
    public Window find(NBSNode head, int key) 
    {
        NBSNode pred = null, curr = null, succ = null;
        boolean[] marked = {false};
        boolean snip;
        
        while (true)
        {
            pred = head;
            curr = pred.next.getReference();
            retry: while (true) {
                succ = curr.next.get(marked);
                while (marked[0]) {
                    snip = pred.next.compareAndSet(curr, succ, false, false);
                    if (!snip) continue retry;
                    curr = succ;
                    succ = curr.next.get(marked);
                }
                if (curr.key >= key)
                    return new Window(pred, curr);
                pred = curr;
                curr = succ;
            }
        }
    }

    public boolean add(Person person) 
    {
        int key = person.hashCode();
        while (true) {
            Window window = find(head, key);
            NBSNode pred = window.pred, curr = window.curr;
            if (curr.key == key) {
                return false;
            } else {
                NBSNode NBSNode = new NBSNode(person);
                NBSNode.next = new  AtomicMarkableReference<NBSNode>(curr, false);
                if (pred.next.compareAndSet(curr, NBSNode, false, false)) {
                    return true;
                }
            }
        }
    }
    public boolean remove(Person person) 
    {
        int key = person.hashCode();
        boolean snip;
        while (true) {
            Window window = find(head, key);
            NBSNode pred = window.pred, curr = window.curr;
            if (curr.key != key) {
                return false;
            } else {
                NBSNode succ = curr.next.getReference();
                snip = curr.next.compareAndSet(succ, succ, false, true);
                if (!snip)
                    continue;
                pred.next.compareAndSet(curr, succ, false, false);
                return true;
            }
        }
    } 
    public void printList() 
    {
        NBSNode curr = head.next.getReference();
        String str = Thread.currentThread().getName() + ": ";
        while (curr.next.getReference() != null) {
            str += "(" + curr.person.name + ", " + curr.person.timeLeft + "ms), ";
            curr = curr.next.getReference();
        }
        str = str.substring(0, str.length() - 2);
        System.out.println(str);
    }
}
