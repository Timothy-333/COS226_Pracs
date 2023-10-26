package Prac6;


public class StackNode 
{
    public Job value;
    public StackNode next;

    public StackNode(Job value) 
    {
        this.value = value;
        next = null;
    }
}
