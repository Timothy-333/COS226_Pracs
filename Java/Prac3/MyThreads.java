public class MyThreads extends Thread {
    AtomicMRMW<Integer> register;
    boolean writer ;

	public MyThreads(AtomicMRMW<Integer> register_ , boolean writer ){
        this.register =register_ ;
        this.writer = writer ;
	}

	@Override
	public void run()
	{
        
        // code here
        
	}
}
