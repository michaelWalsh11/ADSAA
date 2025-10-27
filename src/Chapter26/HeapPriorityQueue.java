package Chapter26;

/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own.  Signed:  Michael Walsh
 * @author walshm
 * Date of Completion:  10/23/2025
 * <p>
 * Assignment:   	HeapPriorityQueue
 * <p>
 * Attribution: I worked solo.
 * <p>
 * General Description:   This was not a very difficult assignment in my opinion
 *                        I believe this works and heaps up and down (in theory)
 *                        without failure.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class HeapPriorityQueue
{
  private static final int DFLT_CAPACITY = 101;
  private Object[] items;
  private int numItems;
  private final Comparator<Object> comparator;

  public HeapPriorityQueue()
  {
    this(DFLT_CAPACITY, null);
  }

  public HeapPriorityQueue(Comparator<Object> c)
  {
    this(DFLT_CAPACITY, c);
  }

  public HeapPriorityQueue(int initialCapacity)
  {
    this(initialCapacity, null);
  }

  public HeapPriorityQueue(int initialCapacity, Comparator<Object> c)
  {
    items = new Object[initialCapacity + 1];
    comparator = c;
  }

  /**
   * Returns true if this priority queue is empty;
   * otherwise returns false.
   */
  public boolean isEmpty()
  {
    return numItems == 0;
  }

  /**
   * Returns the highest priority element without removing
   * it from this priority queue.
   */
  public Object peek()
  {
    if (numItems == 0)
    {
      throw new NoSuchElementException();
    }
    return items[1];
  }

  /**
   * Adds obj to this priority queue; returns true.
   */
  public boolean add(Object obj)
  {
    numItems++;
    if (numItems >= items.length)  // items[0] is not used
      growCapacity();
    items[numItems] = obj;
    reheapUp();
    return true;
  }

  /**
   * Removes and returns the highest priority item.
   */
  public Object remove()
  {
    if (numItems == 0)
    {
      throw new NoSuchElementException();
    }

    Object minObject = items[1];
    items[1] = items[numItems];
    numItems--;
    reheapDown();
    return minObject;
  }

  //**************************************************************************

  @SuppressWarnings("unchecked")
  private boolean lessThan(Object obj1, Object obj2)
  {
    if (comparator != null)
      return comparator.compare(obj1, obj2) < 0;
    else
      return ((Comparable<Object>)obj1).compareTo(obj2) < 0;
  }

  private void reheapDown()
  {
        int index = 1;
        int childIndex;

        while(items[index * 2] != null)
        {
            int left = index * 2;
            int right = index * 2 + 1;
            //find the smaller child node
            if (lessThan(items[left], items[right]))
            {
                childIndex = left;
            }
            else
            {
                childIndex = right;
            }

            //is the part sorted
            if (lessThan(items[index], items[childIndex]))
            {
                items[numItems + 1] = null;
                return;
            }

            //swap if not
            swap(childIndex, index);
        }
      items[numItems + 1] = null;
  }

  private void reheapUp()
  {
        int index = numItems;

        while(index != 1)
        {
            int parentIndex = index / 2;
            if (lessThan(items[index], items[parentIndex]))
            {
                swap(index, parentIndex);
            }
            else {
                return;
            }
            index = index / 2;
        }
  }

  private void swap(int index1, int index2)
  {
      Object temp = items[index1];
      items[index1] = items[index2];
      items[index2] = temp;
  }

  private void growCapacity()
  {
    Object[] tempItems = new Object[2 * items.length - 1];
    System.arraycopy(items, 0, tempItems, 0, items.length);
    items = tempItems;
  }

  public String toString()
  {
      return Arrays.toString(items);
  }
}

