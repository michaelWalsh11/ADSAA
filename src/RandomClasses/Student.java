package RandomClasses;

import java.util.*;

public class Student
{
    private String name;
    private double GPA;

    public Queue<Student> cutAtGPA(Queue<Student> students,
                                   double minGPA)
    {
        Queue<Student> honors = new LinkedList<>();
        while(!students.isEmpty())
        {
            Student student = students.poll();
            if (student.GPA > minGPA)
            {
                honors.add(student);
            }
        }
        return honors;
    }
}
