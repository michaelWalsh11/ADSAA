package Chapter26;

public class twoSixMain
{
    public static void main(String [] args)
    {
        //default heap
        HeapPriorityQueue pq = new HeapPriorityQueue(10);
        pq.add(10);
        pq.add(14);
        pq.add(124);
        pq.add(50);

        System.out.println(pq.toString());

        //adding seems to work
        pq.add(1);
        System.out.println(pq.toString());

        pq.add(5);
        System.out.println(pq.toString());

        pq.add(0);
        System.out.println(pq.toString());

        //removing
        pq.remove();
        System.out.println(pq.toString());

        pq.remove();
        System.out.println(pq.toString());

        pq.remove();
        System.out.println(pq.toString());



    }
}
