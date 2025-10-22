package LinkedLists;

public class LinkedLists
{
    public static void main(String[] args)
    {
        SinglyLinkedList list = new SinglyLinkedList();
        System.out.println(list.size() + " " + list);
        list.add("Amy");
        System.out.println(list.size() + " " + list);

        list.remove(0);

        String[] names = {"Ben", "Kate", "Wendy"};

        list = new SinglyLinkedList(names);
        System.out.println(list.size() + " " + list);

        list.add(0, "Amy");
        list.add(2, "Dan");
        list.add("Zoe");
        list.add(list.size(), "***");

        System.out.println(list.size() + " " + list);

        list.remove("Dan");
        list.remove(0);
        list.remove(list.size() - 1);
        System.out.println(list.size() + " " + list);

        System.out.println("Ben " + list.contains("Ben") + " " + list.indexOf("Ben"));
        System.out.println("Wendy " + list.contains("Wendy") + " " + list.indexOf("Wendy"));
        System.out.println("Dan " + list.contains("Dan") + " " + list.indexOf("Dan"));

    }
}
