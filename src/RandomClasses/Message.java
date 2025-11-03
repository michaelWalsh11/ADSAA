package RandomClasses;

import java.sql.Time;
import java.util.Date;

public class Message
{
    private int priority;
    private String message;
    private int timeOfArrival;

    public Message(String message, int min)
    {
        priority = (int)(Math.random() * 5);
        this.message = message;

        timeOfArrival = min;
    }
    // int 0-4 (0 = highest priority)
    public int getPriority()
    {
        return priority;
    }

    @Override
    public String toString()
    {
        return "Priority: " + priority + "  " + message + " TimeOfArrival " + timeOfArrival;
    }

    public int getTimeOfArrival()
    {
        return timeOfArrival;
    }
}
