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
		int random = (int) (Math.random() * 100);
		try 
		{
			Thread.sleep(random);
			if(writer)
				register.write(random);
			else
				register.read();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
