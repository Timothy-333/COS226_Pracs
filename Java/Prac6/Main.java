package Prac6;

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.print("Task 1 or 2?:");
        DataStructure ds = null;
        int task = Integer.parseInt(System.console().readLine());
        if (task == 1)
            ds = new LFQueue();
        else if (task == 2)
            ds = new LFStack();
        else
            System.out.println("Invalid input");
        Developer dev1 = new Developer(ds,0);
        Developer dev2 = new Developer(ds,1);
        Developer dev3 = new Developer(ds,2);
        Developer dev4 = new Developer(ds,3);
        SystemAdmin sys1 = new SystemAdmin(ds);
        SystemAdmin sys2 = new SystemAdmin(ds);
        
        sys1.start();
        sys2.start();
        dev1.start();
        dev2.start();
        dev3.start();
        dev4.start();
    }
}
