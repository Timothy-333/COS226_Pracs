package Prac5;

public class OLS implements ListInterface
{
    Node head;

    OLS() 
    {
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }

    public boolean add(Person person) 
    {
        int key = person.hashCode();
        while (true) {
            Node pred = head;
            Node curr = pred.next;
            while (curr.key < key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            curr.lock();
            try {
                if (validate(pred, curr)) {
                    if (curr.key == key) {
                        return false;
                    } else {
                        Node node = new Node(person);
                        node.next = curr;
                        pred.next = node;
                        return true;
                    }
                }
            } finally {
                pred.unlock();
                curr.unlock();
            }
        }
    }
    
    public boolean remove(Person person) 
    {
        int key = person.hashCode();
        while (true) {
            Node pred = head;
            Node curr = pred.next;
            while (curr.key < key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            curr.lock();
            try {
                if (validate(pred, curr)) {
                    if (curr.key == key) {
                        pred.next = curr.next;
                        return true;
                    } else {
                        return false;
                    }
                }
            } finally {
                pred.unlock();
                curr.unlock();
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
    
    public boolean validate(Node pred, Node curr) {
        Node node = head;
        while (node.key <= pred.key) {
            if (node == pred) {
                return pred.next == curr;
            }
            node = node.next;
        }
        return false;
    }
}
