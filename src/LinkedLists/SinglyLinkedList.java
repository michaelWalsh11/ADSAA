/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own.  Signed:  Michael Walsh
 * @author walshm
 * Date of Completion:  9/3/2025
 * <p>
 * Assignment:   	SinglyLinkedList Background Class
 * <p>
 * Attribution: I worked on this with the class a little and talked to Barbara about it.
 * <p>
 * General Description:   This file is just created to act the same as a linked list object
 *                        in Java, it is manally done to help get a better undersanding of how
 *                        it works.
 */

package LinkedLists;

import java.util.Iterator;

public class SinglyLinkedList implements Iterable<Object>
{
  private ListNode head;
  private int nodeCount;

  // Constructor: creates an empty list
  public SinglyLinkedList()
  {
    head = null;
    nodeCount = 0;
  }

  // Constructor: creates a list that contains
  // all elements from the array values, in the same order
  public SinglyLinkedList(Object[] values)
  {
    ListNode tail = null;
    for (Object value : values) // for each value to insert
    {
      ListNode node = new ListNode(value, null);
      if (head == null)
        head = node;
      else
        tail.setNext(node);
      tail = node;    // update tail
    }

    nodeCount = values.length;
  }

  // Returns true if this list is empty; otherwise returns false.
  public boolean isEmpty()
  {
      return nodeCount == 0;
  }

  // Returns the number of elements in this list.
  public int size()
  {
      return nodeCount;
  }

  // Returns true if this list contains an element equal to obj;
  // otherwise returns false.
  public boolean contains(Object obj)
  {
      for (Object object : this)
      {
          if (object.equals(obj))
          {
              return true;
          }
      }
    return false;
  }

  // Returns the index of the first element in equal to obj;
  // if not found, retunrs -1.
  public int indexOf(Object obj)
  {
      int index = 0;
      for (Object object : this)
      {
          if (object.equals(obj))
          {
              return index;
          }
          index++;
      }
      return -1;
  }

  // Adds obj to this collection.  Returns true if successful;
  // otherwise returns false.
  public boolean add(Object obj)
  {
      ListNode node = new ListNode(obj, null);

      if (head == null)
      {
          head = node;
          nodeCount++;
          return true;
      }

      ListNode current = head;
      while (current.getNext() != null)
      {
          current = current.getNext();
      }
      current.setNext(node);

      nodeCount++;
      return true;
  }


    // Removes the first element that is equal to obj, if any.
  // Returns true if successful; otherwise returns false.
  public boolean remove(Object obj)
  {
      //empty
      if (head == null)
      {
          return false;
      }

      //isHead
      if (head.getValue().equals(obj))
      {
          head = head.getNext();
          nodeCount--;
          return true;
      }

      //else
      ListNode current = head;
      while (current.getNext() != null)
      {
          if(current.getNext().getValue().equals(obj))
          {
              current.setNext(current.getNext().getNext());
              nodeCount--;
              return true;
          }
          current = current.getNext();
      }
      return false;
  }

  // Returns the i-th element.
  public Object get(int i)
  {
      if (i < 0 || i >= nodeCount)
      {
          return null;
      }

      ListNode current = head;
      for (int x = 0; x < i; x++)
      {
          current = current.getNext();
      }
      return current.getValue();
  }

  // Replaces the i-th element with obj and returns the old value.
  public Object set(int i, Object obj)
  {
      if (i < 0 || i >= nodeCount)
      {
          return null;
      }
      ListNode current = head;
      for (int x = 0; x < i; x++)
      {
          current = current.getNext();
      }
      Object old = current.getValue();
      current.setValue(obj);
      return old;

  }

  // Inserts obj to become the i-th element. Increments the size
  // of the list by one.
  public void add(int i, Object obj)
  {
      if (i < 0 || i > nodeCount)
      {
          throw new IndexOutOfBoundsException();
      }

      if (i == 0)
      {
          head = new ListNode(obj, head);
      }
      else {
          ListNode current = head;
          for (int x = 0; x < i - 1; x++)
          {
              current = current.getNext();
          }
          current.setNext(new ListNode(obj, current.getNext()));
      }
      nodeCount++;
  }

  // Removes the i-th element and returns its value.
  // Decrements the size of the list by one.
  public Object remove(int i)
  {
      if (i < 0 || i >= nodeCount)
      {
          throw new IndexOutOfBoundsException();
      }
      Object gone;
      if (i == 0)
      {
          gone = head.getValue();
          head = head.getNext();
      }
      else {
          ListNode current = head;
          for (int x = 0; x < i - 1; x++)
          {
              current = current.getNext();
          }
          gone = current.getNext().getValue();
          current.setNext(current.getNext().getNext());
      }
      nodeCount--;
      return gone;
  }

  // Returns a string representation of this list.
  @Override
  public String toString()
  {
      StringBuilder output = new StringBuilder("[");
      ListNode current = head;

      while (current != null)
      {
          output.append(current.getValue());
          if (current.getNext() != null)
          {
              output.append(", ");
          }
          current = current.getNext();
      }

      output.append("]");
      return output.toString();
  }

  // Returns an iterator for this collection.
  public Iterator<Object> iterator()
  {
    return new SinglyLinkedListIterator(head);
  }
}

