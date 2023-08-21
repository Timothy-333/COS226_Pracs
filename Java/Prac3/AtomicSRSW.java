public class AtomicSRSW<T> implements Register<T> 
{

    // code (other member variables if any)
    volatile long lastStamp = 0;
    volatile StampedValue<T> lastRead; // regular SRSW timestamp-value pair
    volatile StampedValue<T> value;

    public AtomicSRSW(T init) 
    {
        lastRead = new StampedValue<T>(init);
        value = new StampedValue<T>(init);
    }

    public T read() 
    {
        StampedValue<T> result = value.max(lastRead, value);
        lastRead = result;
        System.out.println("(reader) " + Thread.currentThread().getName() + " : " + result.value);
        return result.value;
    }

    public void write(T v) 
    {
        long stamp = lastStamp + 1;
        value = new StampedValue<T>(stamp, v);
        lastStamp = stamp;
        System.out.println("(writer) " + Thread.currentThread().getName()  + " : " + value.value);
    }
}
