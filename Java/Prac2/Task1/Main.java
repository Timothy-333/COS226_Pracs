public class Main {
    public static void main(String[] args) 
    {
	    MyThread[] threads = new MyThread[5];

        SharedResources CriticalSection = new SharedResources();

        for(int i = 0; i < 5; i++)
        {
            threads[i] = new MyThread(CriticalSection);
            threads[i].setName("Thread-" + i);
        }
        for(MyThread t : threads)
                t.start();
    }
}
