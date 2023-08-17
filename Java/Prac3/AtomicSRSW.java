public class AtomicSRSW<T> implements Register<T> {

    // code (other member variables if any)
    StampedValue<T> r_value; // regular SRSW timestamp-value pair

    public AtomicSRSW(T init) {
        // code here
    }

    public T read() {
        // code here
    }

    public void write(T v) {
        // Code here
    }
}
