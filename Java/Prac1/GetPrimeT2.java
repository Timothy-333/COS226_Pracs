public class GetPrimeT2 extends Thread
{
    private Integer[] array;
    public SharedCounter counter;
    public GetPrimeT2(Integer[] array)
    {
        this.array = array;
        this.counter = new SharedCounter();
    }
    public void run() 
    {
        int count = counter.getAndIncrement();
        while (count < array.length)
        {
            if (isPrime(array[count]))
            {
                System.out.println(Thread.currentThread().getName() + " [" + count + "]: " + array[count]);
            }
            count = counter.getAndIncrement();
        }
    }
    private boolean isPrime(int num) 
    {
        if (num <= 1) 
        {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) 
        {
            if (num % i == 0) 
            {
                return false;
            }
        }
        return true;
    }
}
