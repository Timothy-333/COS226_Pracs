public class AtomicMRMW<T> implements Register<T>{

    private StampedValue<AtomicMRSW<T>>[] a_table; // array of atomic MRSW registers

    @SuppressWarnings("unchecked")
    public AtomicMRMW(int capacity, T init) {
        // code here
    }

    public void write(T value) {
        // code here
    }

    public T read() {
        // code here
    }
}
