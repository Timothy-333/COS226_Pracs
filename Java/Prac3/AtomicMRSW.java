public class AtomicMRSW<T> implements Register<T> 
{

    // code (other member variables if any)
    volatile long lastStamp = 0;
    private volatile StampedValue<AtomicSRSW<T>>[][] a_table; // each entry is SRSW atomic

    @SuppressWarnings("unchecked")
    public AtomicMRSW(T init, int readers) 
    {
        a_table = new StampedValue[readers][readers];
        for (int i = 0; i < a_table.length; i++) 
        {
            for (int j = 0; j < a_table.length; j++) 
            {
                a_table[i][j] = new StampedValue<AtomicSRSW<T>>(new AtomicSRSW<T>(init));
            }
        }
    }

    public T read()
    {
        int me = (int) Integer.parseInt(Thread.currentThread().getName().split("-")[1]) % a_table.length;
        StampedValue<AtomicSRSW<T>> result = a_table[me][me];
        for (int i = 0; i < a_table.length; i++) 
        {
            result = result.max(result, a_table[i][me]);
        }
        for (int i = 0; i < a_table.length; i++) 
        {
            a_table[me][i] = result;
        }
        return result.value.read();
    }

    public void write(T v) 
    {
        long stamp = lastStamp + 1;
        lastStamp = stamp;
        StampedValue<AtomicSRSW<T>> value = new StampedValue<AtomicSRSW<T>>(stamp, new AtomicSRSW<T>(v));
        for (int i = 0; i < a_table.length; i++)
        {
            a_table[i][i] = value;
        }
        value.value.write(v);
    }
}
