package RandomClasses;

import java.util.ArrayList;

public class PriorityQueueArray
{
    private ArrayList<Integer> list = new ArrayList<>();

    public void add(int num)
    {
        int index = binarySearch(num);
        list.add(index, num);
    }

    public int binarySearch(int val)
    {
        //get each end of the sorted list
        int low = 0;
        int high = list.size() -1;

        //while the indexes are not equal (solved) or inverted by 1 (solved)
        while (low <= high)
        {
            //get mid info
            int mid = (low + high) / 2;
            int midVal = list.get(mid);

            //depending on the value change high or low and loop back unless solved
            if (midVal == val)
            {
                return mid;
            }
            else if (midVal < val)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }

        return low;
    }

    public int remove()
    {
        return list.remove(0);
    }

    public int peek()
    {
        return list.get(0);
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }
}
