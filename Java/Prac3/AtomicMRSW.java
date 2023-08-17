public class AtomicMRSW<T> implements Register<T> {

    // code (other member variables if any)
    private StampedValue<AtomicSRSW<T>>[][] a_table; // each entry is SRSW atomic

    @SuppressWarnings("unchecked")
    public AtomicMRSW(T init, int readers) {
        // code here
    }

    public T read() {
       // code here
    }

    public void write(T v) {
        // code here
    }
}
