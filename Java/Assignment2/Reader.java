package Assignment2;

public class Reader extends Thread
{
    private Server server;
    private Client client;

    public Reader(Server server, Client client)
    {
        this.server = server;
        this.client = client;
    }

    public void run()
    {
        String message = server.retrieveMessage(client.name);
        System.out.println(message);
    }
}
