package Assignment2;
import java.util.ArrayList;
import java.util.Random;
public class Main
{
    public static void main(String[] args) 
    {
        Server server = new Server();
        Client client1 = new Client(server, "Client 1");
        Client client2 = new Client(server, "Client 2");
        Client client3 = new Client(server, "Client 3");
        Client client4 = new Client(server, "Client 4");

        // Add clients to an ArrayList
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);

        // Initialize 10 random messages to send to a randomly selected client
        String[] messages = 
        {
            "Hello, how are you?",
            "What are you up to today?",
            "Have you seen the latest movie?",
            "Do you want to grab lunch later?",
            "I heard about this new restaurant, want to try it out?",
            "Did you finish that project yet?",
            "What do you think about the new update?",
            "I'm so tired, I need a nap",
            "Can you believe it's already Friday?",
            "I'm looking forward to the weekend"
        };

        // Send 10 random messages to a randomly selected client
        Random random = new Random();
        for (int i = 0; i < 10; i++) 
        {
            int randomClientIndex = random.nextInt(clients.size());
            Client recipient = clients.get(randomClientIndex);
            String message = messages[random.nextInt(messages.length)];
            clients.get(i).send(recipient, message);
            clients.get(i).receive();
        }
    }
}
