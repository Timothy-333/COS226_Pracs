package Prac5;

import java.util.LinkedList;

public class SecurityGuard extends Thread
{
    private ListInterface synchronizationMethod;
    private LinkedList<Person> entranceQueues;
    
    SecurityGuard(ListInterface synchronizationMethod, LinkedList<Person> entranceQueues)
    {
        this.synchronizationMethod = synchronizationMethod;
        this.entranceQueues = entranceQueues;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (entranceQueues.isEmpty())
            {
                break;
            }
            Person person = entranceQueues.remove();
            if (synchronizationMethod.add(person))
            {
                System.out.println(this.getName() + ": ADD (" + person.name + ", " + person.timeLeft + "ms)");
            }
            else
            {
                System.out.println("Failed to add " + person.name + " to the gallery");
            }
            try
            {
                Thread.sleep(person.timeLeft);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (synchronizationMethod.remove(person))
            {
                synchronizationMethod.printList();
            }
            else
            {
                System.out.println("Failed to remove " + person.name + " from the gallery");
            }
            if(person.timeLeft < 200)
            {
                try
                {
                    Thread.sleep(200 - person.timeLeft);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
