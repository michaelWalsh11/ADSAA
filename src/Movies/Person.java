package Movies;

public class Person implements Comparable
{
    String firstName;
    String lastName;

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Object o) {

        Person tempPerson = ((Person) o);
        int lastComparableValueThing = lastName.compareToIgnoreCase(tempPerson.lastName);

        if (lastComparableValueThing != 0)
        {
            return lastComparableValueThing;
        }

        return firstName.compareTo(tempPerson.firstName);
    }

    @Override
    public boolean equals(Object o)
    {
        Person p = (Person) o;
        return firstName.equals(p.firstName) && lastName.equals(p.lastName);
    }

    @Override
    public String toString()
    {

        return firstName + " " + lastName;
    }
}
