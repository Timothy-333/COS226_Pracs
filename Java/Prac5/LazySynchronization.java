package Prac5;

public class LazySynchronization implements ListInterface
{
    Node head;

    LazySynchronization() 
    {
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }
    public boolean add(Person Person) 
    {
        int key = Person.hashCode();
        while (true) {
            Node pred = head;
            Node curr = head.next;
            while (curr.key < key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred, curr)) {
                        if (curr.key == key) {
                            return false;
                        } else {
                            Node node = new Node(Person);
                            node.next = curr;
                            pred.next = node;
                            return true;
                        }
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }
    public boolean remove(Person Person) {
        int key = Person.hashCode();
        while (true) {
            Node pred = head;
            Node curr = head.next;
            while (curr.key < key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred, curr)) {
                        if (curr.key != key) {
                            return false;
                        } else {
                            curr.marked = true;
                            pred.next = curr.next;
                            return true;
                        }
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
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
    boolean validate(Node pred, Node curr) 
    {
        return !pred.marked && !curr.marked && pred.next == curr;
    }
    
}
