public class Writer extends Thread 
{
    private Server server;
    private Client client;
    private Client recipient;
    private Message message;

    public Writer(Server server, Client client)
    {
        this.server = server;
        this.client = client;
    }
    public void setMessage(Message message, Client recipient)
    {
        this.message = message;
        this.recipient = recipient;
    }
    public void send()
    {
        System.out.println("(SEND) " + Thread.currentThread().getName() + ": { sender: " + this.client.name + " , recipient: " + recipient.name + " } Message: " + message.message);
        server.storeMessage(recipient, message);
    }
    public void run()
    {
    }
}
