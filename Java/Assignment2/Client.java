package Assignment2;

import java.util.concurrent.locks.Lock;

public class Client 
{
    String name;
    Reader reader;
    Writer writer;
    Lock lock = new MCSQueue();
    Server server;
    Client(Server server, String name)
    {
        this.name = name;
        reader = new Reader(server, this);
        writer = new Writer(server, this);
    }
    void send(Client client, String message)
    {
        lock.lock();
        writer.setMessage(message);
        writer.start();
        lock.unlock();
    }
    void receive()
    {
        lock.lock();
        reader.start();
        lock.unlock();
    }
}
