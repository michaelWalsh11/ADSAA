package RandomClasses;

import java.sql.Time;
import java.util.Date;

public class Message
{
    private int priority;
    private String message;
    private Time timeOfArrival;

    public Message(String message)
    {
        priority = (int)(Math.random() * 5);
        this.message = message;

        timeOfArrival = new Time(new Date().getTime());
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

    public Time getTimeOfArrival()
    {
        return timeOfArrival;
    }
}
