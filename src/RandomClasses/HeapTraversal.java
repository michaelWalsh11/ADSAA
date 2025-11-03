package RandomClasses;

public class HeapTraversal
{
    public void traverseInOrder(int [] x, int n)
    {
        recursiveHelperMethod(x, n, 1);
    }

    public void recursiveHelperMethod(int [] x, int n, int index)
    {
        //base case (oob)
        if (index > n)
        {
            return;
        }
        //left child
        recursiveHelperMethod(x, n, 2 * index);

        //current node
        System.out.print(x[index] + " ");

        //right child
        recursiveHelperMethod(x, n, 2 * index + 1);
    }

    public static void main (String [] args)
    {
        HeapTraversal traversal = new HeapTraversal();
        int [] x = {0, 10, 5, 15, 2, 7, 12, 20};
        int n = 7;
        traversal.traverseInOrder(x, n);
    }
}
