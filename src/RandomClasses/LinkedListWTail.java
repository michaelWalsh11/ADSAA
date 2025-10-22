package RandomClasses;

import LinkedLists.ListNode;
import LinkedLists.ListNode2;

import java.util.NoSuchElementException;

/**
 * NOT COMPLETE IDK WHAT IM DOING BUT THIS IS NOT DUE (for us)
 */
public class LinkedListWTail
{
    public ListNode head;
    public ListNode tail;

    public LinkedListWTail()
    {
        head = null;
        tail = null;
    }

    public int sizeLoop(ListNode head)
    {
        int x = 0;
        ListNode temp = head;
        while (temp != null)
        {
            x++;
            temp = temp.getNext();
        }
        return x;
    }

    public static void main (String [] args)
    {
        //Middle Node Stuff
        //empty
        System.out.println(middleNode(new ListNode(null)).getValue());

        //test 1 prep
        ListNode node = new ListNode("H");

        //tests 1
        System.out.println(middleNode(node).getValue());

        //test 3 prep
        ListNode node2 = new ListNode("E");
        node.setNext(node2);
        ListNode node3 = new ListNode("L");
        node2.setNext(node3);

        //Tests 3
        System.out.println(middleNode(node).getValue());

        //test 4 prep
        ListNode node4 = new ListNode("M");
        node3.setNext(node4);

        //test 4
        System.out.println(middleNode(node).getValue());

        //test 5 prep
        ListNode node5 = new ListNode("K");
        node4.setNext(node5);

        //test 5
        System.out.println(middleNode(node).getValue());


        //create 5 nodes
        ListNode2 Lnode1 = new ListNode2("Cherry");
        ListNode2 Lnode2 = new ListNode2("Date");
        ListNode2 Lnode3 = new ListNode2("Apple");
        ListNode2 Lnode4 = new ListNode2("Banana");
        ListNode2 Lnode5 = new ListNode2("Elderberry");

        // Link them together
        Lnode1.setNext(Lnode2);

        Lnode2.setPrevious(Lnode1);
        Lnode2.setNext(Lnode3);

        Lnode3.setPrevious(Lnode2);
        Lnode3.setNext(Lnode4);

        Lnode4.setPrevious(Lnode3);
        Lnode4.setNext(Lnode5);

        Lnode5.setPrevious(Lnode4);

        //sort
        //quicksortComplex(Lnode1, Lnode5);

        //print out
        ListNode2 tempNode = Lnode1;
        while (tempNode.getNext() != null)
        {
            System.out.println(tempNode.getValue().toString());
            tempNode = tempNode.getNext();
        }

        //AI generated Test case
        ListNode n1 = new ListNode("New York");
        ListNode n2 = new ListNode("Boston");
        ListNode n3 = new ListNode("New Jersey");
        ListNode n4 = new ListNode("Chicago");
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        System.out.println("Before:");
        printList(n1);

        String pattern = "New ???????";
        ListNode newHead = moveToBack(n1, pattern);

        System.out.println("\nAfter moving matches to back (pattern = \"" + pattern + "\"):");
        printList(newHead);


    }


    //part of AI generated test case
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.getValue());
            if (current.getNext() != null) System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println();
    }


    private static boolean matches(String str, String pattern)
    {
        if (pattern.length() < str.length())
        {
            return false;
        }

        for (int i = 0; i < str.length(); i++)
        {
            char pc = pattern.charAt(i);
            if (pc != '?' && pc != str.charAt(i))
            {
                return false;
            }
        }
        return true;
    }

    public static ListNode moveToBack(ListNode head, String pattern)
    {
        if (head == null)
        {
            return null;
        }

        //list of non-matched
        ListNode nonMatchHead = null;
        ListNode nonMatchTail = null;
        //List of matched
        ListNode matchHead = null;
        ListNode matchTail = null;

        //sets up to loop through
        ListNode current = head;
        while (current != null)
        {
            //gets the next one ready and sperates this one from the list so you can add it to another without bringing stuff with it
            ListNode next = current.getNext();
            current.setNext(null);

            // if it matches and there are none add it and set them otherwise just add it and move the tail
            if (matches(current.getValue().toString(), pattern))
            {
                if (matchHead == null)
                {
                    matchHead = current;
                    matchTail = current;
                }
                else {
                    matchTail.setNext(current);
                    matchTail = current;
                }
            }
            //same thing but for unmatched instead of matched.
            else {
                if (nonMatchHead == null)
                {
                    nonMatchTail = current;
                    nonMatchHead = current;
                }
                else {
                    nonMatchTail.setNext(current);
                    nonMatchTail = current;
                }
            }
            //iterates through
            current = next;
        }

        //combines the lists and returns
        if (nonMatchHead == null)
        {
            return matchHead;
        }
        nonMatchTail.setNext(matchHead);
        return nonMatchHead;
    }

    /**
     * This first checks if head is null; if so it returns null
     * Then it creates two copy nodes and a counter
     * Then it makes a while loop and one of the copy nodes loops through
     * while the other increments every other time
     * then it returns the one in the middle
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head)
    {
        if (head == null)
        {
            return null;
        }
        int x = 0;
        ListNode temp = head;
        ListNode middleMan = head;
        while (temp.getNext() != null)
        {
            temp = temp.getNext();
            if (x % 2 != 0)
            {
                middleMan = middleMan.getNext();
            }
            x++;
        }
        return  middleMan;
    }


//    public static void quicksortComplex(ListNode2 fromNode, ListNode2 toNode) {
//        if (fromNode.getNext() == null || toNode.getPrevious() == null) {
//            throw new NullPointerException();
//        }
//
//        // If value at fromNode is greater than or equal to toNode value - return early (everything is sorted)
//        if ((fromNode.getValue().toString()).compareTo(toNode.getValue().toString()) >= 0) {
//            return;
//        }
//
//        // Set pivot point to any node - in this case: node after fromNode
//        ListNode2 pivot = findMiddle(fromNode, toNode);
//
//        // While from and to Nodes are different and on respective sides of list (to on right, from on left)
//        while (fromNode != toNode) {
//            // If val of fromNode is less than or equal to val of pivot (sorted)
//            if (fromNode.getValue().toString().compareTo(pivot.getValue().toString()) <= 0) {
//                fromNode = fromNode.getNext(); // Move to next node on right
//            }
//            // If val of toNode is greater than or equal to val of pivot (sorted)
//            else if (toNode.getValue().toString().compareTo(pivot.getValue().toString()) >= 0) {
//                toNode = toNode.getPrevious(); // Move to next node to left
//            }
//            // If neither is true - node values are unsorted (greater val on left and lesser on right)
//            else {
//                swapNodeVals(fromNode, toNode);
//                fromNode = fromNode.getNext();
//                toNode = toNode.getPrevious();
//            }
//        }
//
//        // "Finish Partitioning"
//        if (pivot.getValue().toString().compareTo(toNode.getValue().toString()) < 0) {
//            swapNodeVals(toNode, pivot);
//            pivot = toNode;
//        } else if (pivot.getValue().toString().compareTo(fromNode.getValue().toString()) > 0) {
//            swapNodeVals(fromNode, pivot);
//            pivot = fromNode;
//        }
//
//        // Sort recursively
//        quicksortComplex(fromNode, pivot.getPrevious());
//        quicksortComplex(pivot.getNext(), toNode);
//    }

    public static ListNode2 findMiddle(ListNode2 from, ListNode2 to)
    {
        ListNode2 front = from;
        ListNode2 end = to;

        int x = 0;

        while (front != end)
        {
            if (x % 2 == 0)
                front = front.getNext();

            if (x % 2 != 0)
                end = end.getPrevious();

            x++;
        }
        return front;
    }

    public static void swapNodeVals(ListNode2 a, ListNode2 b) {
        Object temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);
    }


    public static void sortBasic(double[] a) {
        quickSortBasic(a, 0, a.length - 1);
    }


    public void quicksort(ListNode2 fromNode, ListNode2 toNode) {
        if(fromNode.getNext() == null || toNode.getPrevious() == null)
        {
            throw new NullPointerException();
        }

        //if value at fromNode is greater than or equal to toNode value - return early (everything is sorted)
        if((fromNode.getValue().toString()).compareTo(toNode.getValue().toString()) >= 0)
        {
            return;
        }
        //set pivot point to any node - in this case: node after fromNode
        ListNode2 pivot = findMiddle(fromNode, toNode);
        //while from and to Nodes are different and to respective sides of list (to on right, from on left)
        while(fromNode != toNode)
        {
            //if val of fromNode is less than or equal to val of toNode (SORTED)
            if(fromNode.getValue().toString().compareTo(pivot.getValue().toString()) <= 0)
            {
                fromNode = fromNode.getNext(); //move to next node on right
            }
            //if val of toNode is greater than or equal to val of fromNode (SORTED)
            else if (toNode.getValue().toString().compareTo(pivot.getValue().toString()) >= 0)
            {
                toNode = toNode.getPrevious(); //move to next node to left
            }
            else
            { //if either not true - node values are unsorted (greater val on left and lesser on right)
                //swap vals at to and from Nodes
                Object temp = fromNode.getValue();
                fromNode.setValue(toNode.getValue());
                toNode.setValue(temp);
                //increase both from and to Nodes to next and previous Nodes respectively
                fromNode = fromNode.getNext();
                toNode = toNode.getPrevious();
            }
        }
        //if pivot is to left of toNode
    }


    private static void quickSortBasic(double[] a, int from, int to) {
        if (from >= to) return;

        // Choose pivot value (not index)
        double pivot = a[(from + to) / 2];

        int i = from;
        int j = to;

        // Partition
        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i <= j) {
                swapBasic(a, i, j);
                i++;
                j--;
            }
        }

        // Recursively sort left and right parts
        if (from < j) quickSortBasic(a, from, j);
        if (i < to) quickSortBasic(a, i, to);
    }

    private static void swapBasic(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public ListNode reverseList(ListNode head)
    {
        ListNode newHead = null;

        for (ListNode node = head; node != null; node = node.getNext())
        {
            newHead = new ListNode(node.getValue(), newHead);
        }

        return newHead;
    }

    public ListNode add(ListNode head, Object value)
    {
        //create Node to add
        ListNode addition = new ListNode(value);

        ListNode temp = head;
        //find the last node
        while (!(temp.getNext() == null))
        {
            temp = temp.getNext();
        }

        //add the new last node
        temp.setNext(addition);

        return head;
    }

    public int sizeRecc(ListNode head)
    {
        if (head == null)
        {
            return 0;
        }
        return sizeRecc(head.getNext()) + 1;
    }

    public boolean hasTwo(ListNode head)
    {
        return (head != null && head.getNext() != null);
    }

    public ListNode removeFirst(ListNode head)
    {
        if (head == null)
            throw new NoSuchElementException();

        if (head.getNext() == null)
            return null;

        ListNode temphead = head.getNext();
        head.setNext(null);
        head = temphead;

        return head;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public Object peek()
    {
        if (head == null)
        {
            throw new NoSuchElementException();
        }
        return head.getValue();
    }

    public void add(ListNode node)
    {
        if (tail == null)
        {
            head = node;
            tail = node;
        }
        else
        {
            tail.setNext(node);
            tail = node;
        }


    }

    public Object remove()
    {
        if (head == null)
        {
            throw new NoSuchElementException();
        }

        Object temp = head.getValue();
        head = head.getNext();
        if (head == null)
        {
            tail = null;
        }
        return temp;
    }


}
