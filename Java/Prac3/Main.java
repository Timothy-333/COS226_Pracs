public class Main {
    public static void main(String[] args) {
        int n = 20;
	    MyThreads[] threads = new MyThreads[n];
        AtomicMRMW<Integer> reg = new AtomicMRMW<Integer>(n/2, -100);
        
        for(int i = 0; i < n; i++){
            threads[i] = new MyThreads(reg, true );
            threads[i].setName("Thread-" + i);
            threads[(++i)] = new MyThreads(reg, false);
            threads[i].setName("Thread-" + i);
        }
        for(MyThreads t : threads)
            t.start();

    }
}
