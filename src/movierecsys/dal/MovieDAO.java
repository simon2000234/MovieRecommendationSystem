/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import movierecsys.be.Movie;

/**
 *
 * @author pgn
 */
public class MovieDAO
{

    private static final String MOVIE_SOURCE = "data/movie_titles.txt";

    /**
     * Gets a list of all movies in the persistence storage.
     *
     * @return List of movies.
     * @throws java.io.IOException 
     */
    public List<Movie> getAllMovies() throws IOException
    {
        List<Movie> allMovies = new ArrayList<>();
        String source = "data/movie_titles.txt";
        File file = new File(source);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Using a try with resources!
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    try
                    {
                        Movie mov = stringArrayToMovie(line);
                        allMovies.add(mov);
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return allMovies;
    }

    /**
     * Reads a movie from the comma separated line.
     *
     * @param line the comma separated line.
     * @return The representing Movie object.
     * @throws NumberFormatException
     */
    private Movie stringArrayToMovie(String line)
    {
        String[] arrMovie = line.split(",");

        int id = Integer.parseInt(arrMovie[0]);
        int year = Integer.parseInt(arrMovie[1]);
        String title = arrMovie[2];

        Movie mov = new Movie(id, year, title);
        return mov;
    }

    /**
     * Creates a movie in the persistence storage.
     *
     * @param releaseYear The release year of the movie
     * @param title The title of the movie
     * @return The object representation of the movie added to the persistence
     * storage.
     */
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        Path path = new File(MOVIE_SOURCE).toPath();
        int id = -1;
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE))
        {
            id = getNextAvailableMovieID();
            bw.newLine();
            bw.write(id + "," + releaseYear + "," + title);
        }
        return new Movie(id, releaseYear, title);
    }

    /**
     * Examines all stored movies and returns the next available highest ID.
     *
     * @return
     * @throws IOException
     */
    private int getNextAvailableMovieID() throws IOException
    {
        List<Movie> allMovies = getAllMovies();
        int highId = allMovies.get(allMovies.size() - 1).getId();
        return highId + 1;
    }

    /**
     * Deletes a movie from the persistence storage.
     *
     * @param movie The movie to delete.
     */
    public void deleteMovie(Movie movie) throws FileNotFoundException, IOException
    {

        PrintWriter writer = new PrintWriter("data/temp_movie_titles.txt");
        BufferedReader reader = new BufferedReader(new FileReader("data/movie_titles.txt"));
        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.equals(movie.toFileFormat()))
            {
            }
            else
            {
                writer.println(line);
            }
        }
        writer.close();
        reader.close();
        File oldfile = new File("data/movie_titles.txt");
	File newfile = new File("data/temp_movie_titles.txt");
		if(oldfile.renameTo(newfile)){
			System.out.println("Rename succesful");
		}else{
			System.out.println("Rename failed");
		}
    }

    /**
     * Updates the movie in the persistence storage to reflect the values in the
     * given Movie object.
     *
     * @param movie The updated movie.
     */
    public void updateMovie(Movie movie) throws IOException
    {
        
        for (int i = 0; i < getAllMovies().size(); i++)
        {
            if(getAllMovies().get(movie.getId()).getId()== movie.getId()){
            getAllMovies().get(i).setTitle(movie.getTitle());
            getAllMovies().get(i).setYear(movie.getYear());
            }else{
                System.out.println("der er ske en fejl");
            }
            
        }
        
        
        
    }

    /**
     * Gets a the movie with the given ID.
     *
     * @param id ID of the movie.
     * @return A Movie object.
     */
    public Movie getMovie(int id) throws IOException
    {

        for (int i = 0; i < getAllMovies().size() ; i++)
        {
            if (getAllMovies().get(i).getId() == id)
            return getAllMovies().get(i);
        }

        return null;
    }

}
