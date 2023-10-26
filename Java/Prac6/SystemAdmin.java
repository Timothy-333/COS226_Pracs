package Prac6;

public class SystemAdmin extends Thread
{
    DataStructure ds;
    public SystemAdmin(DataStructure ds)
    {
        this.ds = ds;
    }
    public void run()
    {
        while (true)
        {
            try
            {
                Job job = ds.deq();
                int randomNumber = (int) (Math.random() * 24) + 1;
                String approvalStatus = job.hours < randomNumber ? "approved" : "rejected";
                System.out.println("(OUT) " + Thread.currentThread().getName() + " " + job.number + " " + job.hours + " " + approvalStatus);
            }
            catch (Exception e)
            {
            }
        }
    }
}
