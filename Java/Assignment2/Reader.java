public class Reader extends Thread
{
    private Server server;
    private Client client;

    public Reader(Server server, Client client)
    {
        this.server = server;
        this.client = client;
    }
    public void read()
    {
        server.retrieveMessage(client);
    }
    public void run()
    {
    }
}
