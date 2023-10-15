package Assignment2;

public class Writer extends Thread 
{
    private Server server;
    private Client client;
    private String message;

    public Writer(Server server, Client client)
    {
        this.server = server;
        this.client = client;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public void run()
    {
        server.storeMessage(client.name, message);
    }
}
