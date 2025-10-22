package Movies;

import java.util.LinkedList;

public class Movie implements Comparable
{
    int releaseYear;
    String title;
    LinkedList directorName;
    LinkedList actors;
    String fullThing;

    public Movie(int releaseYear, String title, LinkedList directorName, LinkedList actors, String fullThing)
    {
        this.releaseYear = releaseYear;
        this.title = title;
        this.directorName = directorName;
        this.actors = actors;
        this.fullThing = fullThing;
    }


    @Override
    public int compareTo(Object o) {
        Movie movie = (Movie) o;
        return this.title.compareToIgnoreCase(movie.title);
    }

    @Override
    public String toString()
    {
        return fullThing;
    }


    /**
     * This gets the first directors last name
     * @return lastName
     */
    public String getDirectorLastName()
    {
        String [] names = directorName.peek().toString().split(" ");
        return names[names.length - 1];
    }

    /**
     * This is a way prettier toString that is not relevant to this project.
     * @return String
     */
    public String betterToString()
    {

        return  "\nRelease Year: " + releaseYear +
                "\nTitle: " + title +
                "\ndirectors: " + directorName.toString() +
                "\nActors: " + actors.toString();
    }


}
