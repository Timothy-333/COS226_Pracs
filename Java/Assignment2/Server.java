package Assignment2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class Server 
{
    private Map<String, String> clientMessages = new HashMap<>();
    private Lock serverLock = new MCSQueue();

    public void storeMessage(String recipient, String message) 
    {
        serverLock.lock();
        clientMessages.put(recipient, message);
        serverLock.unlock();
    }

    public String retrieveMessage(String recipient) 
    {
        String message = clientMessages.getOrDefault(recipient, "");
        return message;
    }
}
