/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movierecsys.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.MoviedbDAO;
import movierecsys.dal.RatingDBDAO;
import movierecsys.dal.UserDBDAO;

/**
 *
 * @author pgn
 */
public class MRSManager implements MRSOwsLogicFacade {

    MoviedbDAO mddao = new MoviedbDAO();
    UserDBDAO uddao = new UserDBDAO();
    RatingDBDAO rddao = new RatingDBDAO();
    @Override
    public List<Rating> getRecommendedMovies(User user) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllTimeTopRatedMovies() throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getMovieReccomendations(User user) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> searchMovies(String query) throws IOException
    {
        List<Movie> allMovies = mddao.getAllMovies();
        List<Movie> movieMatch = new ArrayList<>();
        for (Movie allMovy : allMovies)
        {
            if(query.equals(allMovy.getTitle()))
            {
                movieMatch.add(allMovy);
            }
        }
        return movieMatch;
    }

    @Override
    public Movie createMovie(int year, String title) throws IOException
    {
        return mddao.createMovie(year, title);
    }

    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        mddao.updateMovie(movie);
    }

    @Override
    public void deleteMovie(Movie movie) throws IOException
    {
        mddao.deleteMovie(movie);
    }

    @Override
    public void rateMovie(Movie movie, User user, int rating) throws IOException
    {
        rddao.createRating(new Rating(movie.getId(), user.getId(), rating));
    }

    @Override
    public User createNewUser(String name) throws IOException
    {
        return uddao.createUser(name);
    }

    @Override
    public User getUserById(int id) throws IOException
    {
        List<User> users = uddao.getAllUser();
        for (User user : users)
        {
            if (user.getId() == id)
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws IOException
    {
      
        return uddao.getAllUser();
    }

}
