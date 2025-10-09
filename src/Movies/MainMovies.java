package Movies;

/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own.  Signed:  Michael Walsh
 * @author walshm
 * Date of Completion:  10/7/2025
 * <p>
 * Assignment:   	Movie Project Question 20
 * <p>
 * Attribution: I worked Katherine and Solee.
 * <p>
 * General Description:   This takes a file and reads through it and creates a list of Movies
 *                        with the information. The Movie class basically just acts as a container
 *                        for the information with no complex methods, same with person. After the part
 *                        of my main that scans through the file and makes the list of movies, I go through
 *                        and execute all the different orders and types of information that the questions
 *                        ask for and print them out.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainMovies
{
    public static void main(String [] args) throws FileNotFoundException
    {
        ArrayList<Movie> movies = new ArrayList<>();

        Scanner scanner = new Scanner(new File("movies.txt"));
        while (scanner.hasNext())
        {
            String fullThing = scanner.nextLine();
            String title = fullThing.substring(5, 38).trim();
            int releaseDate = Integer.parseInt(fullThing.substring(0, 4).trim());
            String [] directorName = fullThing.substring(89).split(", ");
            LinkedList<Person> directors = new LinkedList<>();
            for (String names : directorName)
            {
                String [] name = names.split(" ");
                Person person = new Person(name[0], name[1]);
                directors.add(person);

            }

            String [] actors = fullThing.substring(38, 84).split(", ");
            LinkedList<Person> thing = new LinkedList<>();
            for (String actor : actors)
            {
                String [] name = actor.split(" ");
                Person person = new Person(name[0], name[1]);
                thing.add(person);

            }

            Movie movie = new Movie(releaseDate, title, directors, thing, fullThing);
            movies.add(movie);


        }

        //Part A test print
        for (Movie movie : movies)
        {
            System.out.println(movie.toString());
        }

        //Part B
        movies.sort(Comparator.naturalOrder());
        System.out.println();

        for (Movie movie : movies)
        {
            System.out.println(movie.toString());
        }

        //part C
        movies.sort(Comparator.comparing(Movie::getDirectorLastName));
        System.out.println();

        for (Movie movie : movies)
        {
            System.out.println(movie.toString());
        }

        //part D
        Set<Person> actors = new TreeSet<>();

        for (Movie movie : movies)
        {
            LinkedList temp = movie.actors;
            for (Object person : temp)
            {
                if (!actors.contains(person))
                {
                    actors.add((Person) person);
                }
            }
        }

        System.out.println();

        System.out.println(actors.size());
        for (Person person : actors)
        {
            System.out.println(person);
        }
    }
}
