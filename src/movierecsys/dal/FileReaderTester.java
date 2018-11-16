/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {       
        RatingDAO ratingDAO = new RatingDAO();
        List<Rating> ratings = ratingDAO.getAllRatings();
        for (Rating rating : ratings)
        {
            System.out.println(rating);
        }
 


        // MovieDAO movieDao = new MovieDAO();

        UserDAO user = new UserDAO();
        List<User> users = user.getAllUsers();
//        for (User user1 : users)
//        {
//            System.out.println(user1);
//
//        }
        System.out.println(users.size());

//        User u = user.getUser(2000);
//        System.out.println(u.getName());
        
        
        user.updateUser(new User(7, "test"));
        
        // MovieDAO movieDao = new MovieDAO();
        // movieDao.deleteMovie(new Movie(17771, 1966, "Django"));
        //movieDao.updateMovie(new Movie(1, 420, "dab"));

    }
}

