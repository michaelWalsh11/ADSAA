package RandomClasses;

import java.util.ArrayList;

public class PQSimulation
{

    private int numMessages = 10;
    private int numPriorities = 5;
    private int processingTime = 4;
    private double messageProb = 0.2;

    private MessagePriorityQueue list;
    ArrayList<ArrayList<Integer>> waitTimes = new ArrayList<>();

    public PQSimulation(int numMessages)
    {
        this.numMessages = numMessages;
        list = new MessagePriorityQueue();

        for (int x = 0; x < numPriorities; x++)
        {
            waitTimes.add(new ArrayList<>());
        }
    }

    public static void main(String [] args)
    {
        PQSimulation sim = new PQSimulation(1000);
        sim.run(3000);
        sim.reportStats();
    }

    public void run(int min) {
        int currentTime = 0;
        int timeUntilFree = 0;

        while (currentTime < min) {

            if (Math.random() < messageProb)
            {
                list.add(new Message("Arbitrary Test", currentTime));
            }

            if (timeUntilFree <= 0 && !list.isEmpty())
            {
                Message temp = list.remove();
                int wait = currentTime - temp.getTimeOfArrival();
                waitTimes.get(temp.getPriority()).add(wait);
                timeUntilFree = processingTime;
            }

            currentTime++;
            timeUntilFree--;
        }
    }
    private void reportStats()
    {
        for (int x = 0; x < numPriorities; x++)
        {

            int numThings = 0;
            int totalTime = 0;
            ArrayList<Integer> temp = waitTimes.get(x);
            for (int i : temp)
            {
                totalTime += i;
                numThings++;
            }

            System.out.println("Average Time for Proiority " + x + " is " + totalTime / numThings);
        }
    }
}
