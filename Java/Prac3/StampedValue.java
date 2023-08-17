public class StampedValue <T> {
    public long stamp;
    public T value;
    // initial value with zero timestamp
    public StampedValue(T init) {
        // code here
    }
    // later values with timestamp provided
    public StampedValue(long stamp, T value) {
        // code here
    }

    public StampedValue<T> max(StampedValue<T> x, StampedValue<T> y) {
        // code here
    }
}
