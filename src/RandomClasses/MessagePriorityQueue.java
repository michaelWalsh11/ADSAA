package RandomClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MessagePriorityQueue
{
    ArrayList<Queue<Message>> list = new ArrayList<>(5);

    public MessagePriorityQueue()
    {
        for (int x = 0; x < 5; x++)
        {
            list.add(new LinkedList<Message>());
        }
    }

    public void add(Message message)
    {
        list.get(message.getPriority()).add(message);
    }

    public Message remove()
    {
        int x = 0;
        while (x < 5 && list.get(x).isEmpty())
        {
            x++;
        }

        if (x >= 5)
        {
            return null;
        }

        return list.get(x).remove();
    }

    public Message peek()
    {
        int x = 0;
        while (x < 5 && list.get(x).isEmpty())
        {
            x++;
        }

        if (x >= 5)
        {
            return null;
        }

        return list.get(x).peek();
    }

    public boolean isEmpty()
    {
        int x = 0;
        while (x < 5 && list.get(x).isEmpty())
        {
            x++;
        }

        if (x >= 5)
        {
            return true;
        }

        return false;
    }

}
