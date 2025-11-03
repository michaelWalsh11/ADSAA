package RandomClasses;

public class MessagerTester
{
    public static void main(String [] args)
    {
        MessagePriorityQueue queue = new MessagePriorityQueue();
        for (int x = 0; x < 8; x++)
        {
            Message message = new Message("Arbitrary text!", 0);
            queue.add(message);
        }

        while(!queue.isEmpty())
        {
            System.out.println(queue.remove().toString());
        }
    }
}
