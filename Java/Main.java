public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Value for n:");
        int n = Integer.parseInt(System.console().readLine());
        System.out.println("Value for m:");
        int m = Integer.parseInt(System.console().readLine());
        System.out.println("Number of threads:");
        int numThreads = Integer.parseInt(System.console().readLine());
        int size = m - n;
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = n + i;
        }
        int chunkSize = size / numThreads;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) 
        {
            int startRange = i * chunkSize;
            int endRange = (i == numThreads - 1) ? size - 1 : startRange + chunkSize - 1;
            Thread thread = new MyThread(array, startRange, endRange);
            thread.setName("Thread-" + i);
            threads[i] = thread;
            thread.start();
        }
        for (int i = 0; i < numThreads; i++)
        {
            try 
            {
                threads[i].join();
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
