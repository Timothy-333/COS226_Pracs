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
        reader.start();
        writer.start();
    }
    void send(Client reciever, Message message)
    {
        lock.lock();
        writer.setMessage(message, reciever);
        writer.send();
        lock.unlock();
    }
    void receive()
    {
        lock.lock();
        reader.read();
        lock.unlock();
    }
}
