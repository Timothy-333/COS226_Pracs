public class GetPrime extends Thread
{
    private Integer[] array;
    private int startRange;
    private int endRange;

    public GetPrime(Integer[] array, int n, int m)
    {
        this.array = array;
        this.startRange = n;
        this.endRange = m;
    }
    public void run() 
    {
        for (int i = startRange; i <= endRange; i++)
        {
            if (isPrime(array[i]))
            {
                System.out.println(Thread.currentThread().getName() + " [" + startRange + "-" + endRange + "]: " + array[i]);
            }
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
