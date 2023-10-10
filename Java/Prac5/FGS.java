package Prac5;

public class FGS implements ListInterface
{
    Node head;

    FGS() 
    {
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }
    public boolean add(Person person) 
    {
        int key = person.hashCode();
        head.lock();
        Node pred = head;
        try {
            Node curr = pred.next;
            curr.lock();
            try {
                while (curr.key < key) {
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if (curr.key == key) {
                    return false;
                }
                Node newNode = new Node(person);
                newNode.next = curr;
                pred.next = newNode;
                return true;
            } finally {
                curr.unlock();
            }
        } finally {
            pred.unlock();
        }
    }
    public boolean remove(Person item) 
    {
        Node pred = null, curr = null;
        int key = item.hashCode();
        head.lock();
        try {
            pred = head;
            curr = pred.next;
            curr.lock();
            try {
                while (curr.key < key) {
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if (curr.key == key) {
                    pred.next = curr.next;
                    return true;
                }
                return false;
            } finally {
                curr.unlock();
            }
        } finally {
            pred.unlock();
        }
    }
    public void printList() 
    {
        StringBuilder sb = new StringBuilder();
        Node curr = head.next;
        sb.append(Thread.currentThread().getName() + ": ");
        while (curr.key != Integer.MAX_VALUE) {
            sb.append("(" + curr.person.name + ", " + curr.person.timeLeft + "ms), ");
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }
}
