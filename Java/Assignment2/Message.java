public class Message 
{
    Client sender;
    String message;
    Message(Client sender, String message)
    {
        this.sender = sender;
        this.message = message;
    }
}
