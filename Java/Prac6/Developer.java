package Prac6;
import java.util.Random;

public class Developer extends Thread
{
    DataStructure ds;
    int range;
    public Developer(DataStructure ds, int range)
    {
        this.ds = ds;
        this.range = range;
    }
    public void run()
    {
        for (int i = 3*range; i < 3*range + 3; i++)
        {
            Job job = new Job();
            Random rand = new Random();
            job.hours = rand.nextInt(24) + 1;
            job.number = i;
            ds.enq(job);
        }
    }
}
