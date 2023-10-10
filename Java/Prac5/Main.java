package Prac5;

import java.util.LinkedList;
import java.util.Random;

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.print("Please Pick a task 1-4:");
        int task = Integer.parseInt(System.console().readLine());
        ListInterface synchronizationMethod = new FGS();
        if (task == 1)
        {
            synchronizationMethod = new FGS();
        }
        else
        if (task == 2)
        {
            synchronizationMethod = new OLS();
        }
        else if (task == 3)
        {
            synchronizationMethod = new LazySynchronization();
        }
        else if (task == 4)
        {
            synchronizationMethod = new NBS();
        }
        else
        {
            System.out.println("Invalid task number");
            return;
        }
        // Queue for 5 different entrances
        LinkedList<Person> entranceQueue1 = new LinkedList<Person>();
        LinkedList<Person> entranceQueue2 = new LinkedList<Person>();
        LinkedList<Person> entranceQueue3 = new LinkedList<Person>();
        LinkedList<Person> entranceQueue4 = new LinkedList<Person>();
        LinkedList<Person> entranceQueue5 = new LinkedList<Person>();

        // Queue for inside the gallery

        // Populate each entrance queue with 10 People objects with a random timestamp between 100 ms and 1000ms names can be P-1, P-2 etc.
        Random rand = new Random();
        for (int i = 0; i < 10; i++) 
        {
            entranceQueue1.add(new Person("P-" + i, rand.nextInt(901) + 100));
            entranceQueue2.add(new Person("P-" + (i + 10), rand.nextInt(901) + 100));
            entranceQueue3.add(new Person("P-" + (i + 20), rand.nextInt(901) + 100));
            entranceQueue4.add(new Person("P-" + (i + 30), rand.nextInt(901) + 100));
            entranceQueue5.add(new Person("P-" + (i + 40) , rand.nextInt(901) + 100));
        }

        SecurityGuard guard1 = new SecurityGuard(synchronizationMethod,entranceQueue1);
        guard1.setName("Thread-0");
        SecurityGuard guard2 = new SecurityGuard(synchronizationMethod,entranceQueue2);
        guard2.setName("Thread-1");
        SecurityGuard guard3 = new SecurityGuard(synchronizationMethod,entranceQueue3);
        guard2.setName("Thread-2");
        SecurityGuard guard4 = new SecurityGuard(synchronizationMethod,entranceQueue4);
        guard2.setName("Thread-3");
        SecurityGuard guard5 = new SecurityGuard(synchronizationMethod,entranceQueue5);
        guard2.setName("Thread-4");

        guard1.start();
        guard2.start();
        guard3.start();
        guard4.start();
        guard5.start();
    }
}
