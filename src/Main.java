import java.util.*;

/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own.  Signed:  Michael Walsh
 * @author walshm
 * Date of Completion:  8/25/2025
 * <p>
 * Assignment:   	Code for the Big O notation worksheet
 * <p>
 * Attribution: I worked with Boom and Matthew for this one.
 * <p>
 * General Description:   This optimizes one method by changing a line to lower
 *                        the time complexity from n^2 to linear and also adds a
 *                        thing to put at the start of mergeSort to tell if its already
 *                        sorted.
 */

public class Main {
    private final String terminator = "END";
    public static void main(String[] args)
    {

        Queue<Integer> q = new LinkedList<Integer>();

        for (int k = 1; k <= 3; k++)
        {
            q.add(k-1);
            q.add(k+1);
        }

        while (!q.isEmpty())
            System.out.print(q.remove() + " ");

        Stack<Integer> stack = new Stack<>();

        stack.push(-6);
        stack.push(4);
        stack.push(-2);
        stack.push(7);
        stack.push(3);
        stack.push(-1);

        Stack<Integer> stackPos = new Stack<Integer>();
        Stack<Integer> stackNeg = new Stack<Integer>();

        while (!stack.isEmpty())
        {
            Integer obj = stack.pop();
            if (obj.intValue() >= 0)
                stackPos.push(obj);
            else
                stackNeg.push(obj);
        }

        while (!stackPos.isEmpty())
            stack.push(stackPos.pop());

        while (!stackNeg.isEmpty())
            stack.push(stackNeg.pop());

        System.out.println(stack);



        int [] s = new int[20];

        for (int x = 10; x < 30; x++)
        {
            s[x - 10] = (int)(Math.random() * (x + 1));
        }

        System.out.print("{");
        for (int y = 0; y < s.length; y++)
            System.out.print(" " + s[y]);
        System.out.print(" }");

        System.out.println();

        int n = 4;

        System.out.println(ne(s, n));
        System.out.println(oldMethod(s, n));
    }

    public void replace(Queue<String> morseCode)
    {
        int size = morseCode.size();

        for (int x = 0; x < size; x++)
        {
            String current = morseCode.poll();

            if (current.equals("..--.."))
            {
                morseCode.add(".-.-.-");
            }
            else
            {
                morseCode.add(current);
            }
        }
    }

    public static <E> Queue<E> copy(Queue<E> q)
    {
        return new LinkedList<>(q);
    }

    public static <E> Queue<E> copy(Queue<E> q, int noReason)
    {
        if (q.isEmpty())
        {
            return new LinkedList<>();
        }
        Queue<E> out = new LinkedList<>();
        E temp = q.remove();
        q.add(temp);
        out.add(temp);

        while (q.peek() != temp)
        {
            E temp2 = q.remove();
            q.add(temp2);
            out.add(temp2);
        }

        return out;

    }


    public LinkedList<String> mix (List<String> list1,
                                   List<String> list2)
    {
        LinkedList<String> output = new LinkedList<>();
        while(!list1.isEmpty())
        {
            output.add(list1.remove(0));
            output.add(list2.remove(0));
        }
        return output;
    }


    public double sum2(List<Double> list)
    {
        double output = 0.0;
        for (int x = 0; x < list.size() - 1; x++)
        {
            for (int y = x + 1; y < list.size(); y++)
            {
                output += list.get(x) * list.get(y);
            }
        }
        return output;
    }



    // did not use while (!list2.isEmpty) because did not know
    // if I could alter list2.
    public <E> void append(List<E> list1, List <E> list2)
    {
        int x = 0;
        while (list2.size() > x)
        {
            list1.add(list2.get(x));
            x++;
        }
    }

    public <E> void append2(List<E> list1, List <E> list2)
    {
        while (!list2.isEmpty())
        {
            list1.add(list2.removeFirst());
        }
    }

    public <E> void append3(List<E> list1, List <E> list2)
    {
        for (E e : list2)
        {
            list1.add(e);
        }
    }


    /**
     * This is the new version of the number 6 problem
     * the thing that I changed removed a for loop that would
     * reclaculate the entire average but i changed it to just drop
     * the first ones value and add the last one.
     * @param s the array
     * @param n the second interval thing
     * @return the kMax
     */
    public static int ne(int [] s, int n)
    {
        double sum = 0.0;
        for (int i = 0; i < n; i++)
        {
            sum += s[i];
        }

        double max = sum;
        int kMax = 0;

        for (int k = 1; k < 4 * n; k++) {

            //replace the loop with this
            sum = sum - s[k - 1] + s[k + n - 1];

            if (sum > max) {
                max = sum;
                kMax = k;
            }
        }

        return kMax;
    }

    /**
     * Finds the arrival time of the first seismic wave in a 5-second seismogram.
     * The array s has 5n samples at n per second.
     * Averages each one-second interval and
     * returns the second with the largest average.
     * @param s the array
     * @param n 5x the number per second
     * @return the kMax
     */
    public static int oldMethod(int [] s, int n)
    {
        // Find the sum starting at i = 0:
        double sum = 0.0;
        for (int i = 0; i < n; i++)
            sum += s[i];
        double max = sum;
        int kMax = 0;

        for (int k = 1; k < 4 * n; k++) {
            // Find the sum starting at i = k:
            sum = 0.0;
            for (int i = k; i < k + n; i++)
                sum += s[i];

            if (sum > max) {   // update max
                max = sum;
                kMax = k;
            }
        }

        return kMax;
    }

}