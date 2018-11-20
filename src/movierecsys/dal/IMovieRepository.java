/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import movierecsys.be.Movie;

/**
 *
 * @author Melchertsen
 */
public interface IMovieRepository
{

    /**
     * Creates a movie in the persistence storage.
     *
     * @param releaseYear The release year of the movie
     * @param title The title of the movie
     * @return The object representation of the movie added to the persistence
     * storage.
     */
    Movie createMovie(int releaseYear, String title) throws IOException;

    /**
     * Deletes a movie from the persistence storage.
     *
     * @param movie The movie to delete.
     * @throws java.io.FileNotFoundException
     */
    void deleteMovie(Movie movie) throws IOException;

    /**
     * Gets a list of all movies in the persistence storage.
     *
     * @return List of movies.
     * @throws java.io.IOException
     */
    List<Movie> getAllMovies() throws IOException;

    /**
     * Gets a the movie with the given ID.
     *
     * @param id ID of the movie.
     * @return A Movie object.
     * @throws java.io.IOException
     */
    Movie getMovie(int id) throws IOException;

    /**
     * Updates the movie in the persistence storage to reflect the values in the
     * given Movie object.
     *
     * @param movie The updated movie.
     * @throws java.io.IOException
     */
    void updateMovie(Movie movie) throws IOException;
    
}
