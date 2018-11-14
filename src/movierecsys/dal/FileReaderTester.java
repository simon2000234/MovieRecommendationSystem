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
       // MovieDAO movieDao = new MovieDAO();


      //  System.out.println(movieDao.getMovie(597));


      //  movieDao.deleteMovie(new Movie(17771, 1966, "Django"));
       // movieDao.updateMovie(new Movie(17772,2099,"Django_v2"));

        //movieDao.deleteMovie(new Movie(17771, 1966, "Django"));
       // movieDao.updateMovie(new Movie(1, 420, "dab"));
        
        UserDAO user =new UserDAO();
     List<User> users = user.getAllUsers();
        for (User user1 : users)
        {
            System.out.println(user1);
           
        }
         System.out.println( users.size());
    }
}


