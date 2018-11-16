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

        UserDAO user = new UserDAO();
        List<Rating> ratings = ratingDAO.getAllRatings();
//        for (Rating rating : ratings)
//        {
//            System.out.println(rating);
//        }
 


        // MovieDAO movieDao = new MovieDAO();

        
        List<User> users = user.getAllUsers();
//        for (User user1 : users)
//        {
//            System.out.println(user1);
//
//        }
        System.out.println(users.size());

        ratingDAO.updateRating(new Rating(8,1744889, 5));
// 8,1744889,-5
    }
}

