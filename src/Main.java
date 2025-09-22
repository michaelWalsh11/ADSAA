import java.util.LinkedList;
import java.util.List;

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
    public static void main(String[] args)
    {

        List<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.addFirst(5);
        System.out.println(list);



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