/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public static void main(String[] args) throws IOException, SQLException
    {


    userDBDAO UserDbDAO = new userDBDAO();
                   // opretter user
//    User newUser;
//    String name ="test";
//    newUser = UserDbDAO.createUser(2649286,name);
      //  UserDbDAO.deleteUser(new User(2649286,"test"));
      
                 // get one user
//        User user= UserDbDAO.getUser(15737);
//        System.out.println(user.getId()+" "+ user.getName());

        // update user  
      String name ="wombat";
      User user;
        UserDbDAO.updateUser(user= new User(7,name));

    

    }
 
}
