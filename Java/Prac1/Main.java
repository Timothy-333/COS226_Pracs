public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Value for n:");
        int n = Integer.parseInt(System.console().readLine());
        System.out.println("Value for m:");
        int m = Integer.parseInt(System.console().readLine());
        System.out.println("Task 1 or 2:");
        int task = Integer.parseInt(System.console().readLine());
        Integer[] array = new Integer[m - n];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = n + i;
        }
        if (task == 1)
        {
            task1(n, m, array);
        }
        else if (task == 2)
        {
            task2(n, m, array);
        }
        else
        {
            System.out.println("Invalid task number");
        }
    }
    public static void task1(int n, int m, Integer[] array)
    {
        System.out.println("Number of threads:");
        int numThreads = Integer.parseInt(System.console().readLine());
        int size = m - n;
        int chunkSize = size / numThreads;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) 
        {
            int startRange = i * chunkSize;
            int endRange = (i == numThreads - 1) ? size - 1 : startRange + chunkSize - 1;
            Thread thread = new GetPrime(array, startRange, endRange);
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
    public static void task2(int n, int m, Integer[] array)
    {
        Thread thread1 = new GetPrimeT2(array);
        Thread thread2 = new GetPrimeT2(array);
        thread1.setName("Thread-0");
        thread2.setName("Thread-1");
        thread1.start();
        thread2.start();
        try
        {
            thread1.join();
            thread2.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
