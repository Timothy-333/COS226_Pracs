package Prac6;

public class Backoff 
{
    final int minDelay, maxDelay;
    int limit;
    final int maxLimit;

    public Backoff(int min, int max) 
    {
        minDelay = min;
        maxDelay = max;
        limit = minDelay;
        maxLimit = maxDelay - minDelay;
    }

    public void backoff()
    {
        int delay = minDelay + (int) (Math.random() * limit);
        limit = Math.min(maxLimit, 2 * limit);
        try 
        {
            Thread.sleep(delay);
        } 
        catch (InterruptedException e) 
        {
        }
    }
}
