public class AtomicMRMW<T> implements Register<T>{

    private StampedValue<AtomicMRSW<T>>[] a_table; // array of atomic MRSW registers

    @SuppressWarnings("unchecked")
    public AtomicMRMW(int capacity, T init) 
    {
        this.a_table = new StampedValue[capacity];
        for (int i = 0; i < a_table.length; i++) 
        {
            this.a_table[i] = new StampedValue<AtomicMRSW<T>>(new AtomicMRSW<T>(init, capacity));
        }
    }

    public void write(T value) 
    {
        int me = (int) Integer.parseInt(Thread.currentThread().getName().split("-")[1])%a_table.length;
        StampedValue<AtomicMRSW<T>> max = new StampedValue<AtomicMRSW<T>>(-1, null);
        for (int i = 0; i < a_table.length; i++) 
        {
            max = max.max(max, a_table[i]);
        }
        a_table[me] = new StampedValue<AtomicMRSW<T>>(max.stamp + 1, new AtomicMRSW<T>(value, a_table.length));
        a_table[me].value.write(value);
    }

    public T read() 
    {
        StampedValue<AtomicMRSW<T>> max = new StampedValue<AtomicMRSW<T>>(-1, null);
        for (int i = 0; i < a_table.length; i++) 
        {
            max = max.max(max, a_table[i]);
        }
        return max.value.read();
    }
}
