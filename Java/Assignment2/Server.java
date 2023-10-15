import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class Server 
{
    private Map<Client, Message> clientMessages = new HashMap<>();
    private Lock serverLock = new MCSQueue();

    public void storeMessage(Client recipient, Message message) 
    {
        serverLock.lock();
        clientMessages.put(recipient, message);
        System.out.println("(SEND) " + Thread.currentThread().getName() + ": SUCCESSFUL");
        serverLock.unlock();
        if (recipient != message.sender)
        {
            recipient.receive();
        }
    }

    public void retrieveMessage(Client recipient)
    {
        Message message = clientMessages.get(recipient);
        if (message != null)
        {
            System.out.println("(RECEIVE) " + Thread.currentThread().getName() + ": { recipient: " + recipient.name + " , sender: " + message.sender.name + " }\nMessage: " + message.message);
            clientMessages.remove(recipient);
        }
        else
        {
            System.out.println("(RECEIVE) " + Thread.currentThread().getName() + ": { recipient: " + recipient.name + " , sender: null }");
        }
    }
}
