package Task1;
public class Main {
    public static void main(String args[])
    {
        Node[] threads = new Node[5];
        Printer p = new Printer();
        for (int i = 0; i < 5; i++) 
        {
            threads[i] = new Node(p);
            threads[i].setName("Thread " + i);
        }
        for (int i = 0; i < 5; i++)
        {
            threads[i].start();
        }
    }
}
