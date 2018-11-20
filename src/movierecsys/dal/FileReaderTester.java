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
    public static void main(String[] args) throws IOException
    {       
        MovieDbDao mddao = new MovieDbDao();
        
    }
    
    public static void migrateMovies() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("movierecsys");
        ds.setUser("CS2018A_30");
        ds.setPassword("CS2018A_30");
        
        MovieDAO mvDao = new MovieDAO();
        List<Movie> movies = mvDao.getAllMovies();
        
        try(Connection con = ds.getConnection())
        {
            
            Statement statement = con.createStatement();
            for (Movie movie : movies)
            {
                String SQL = "INSERT INTO Movie (id, year, title) VALUES(" 
                    + movie.getId() + "," 
                    + movie.getYear() + ",'" 
                    + movie.getTitle().replace("'", "") + "');";
                System.out.println(SQL);
                int i = statement.executeUpdate(SQL);
                // INSERT INTO Movie (id,year,title) VALUES (1,20018,Venom)
                System.out.println("Affected row = " + i);
            }
        } 
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
        public static void migrateUsers() throws IOException
            {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName("10.176.111.31");
            ds.setDatabaseName("movierecsys");
            ds.setUser("CS2018A_30");
            ds.setPassword("CS2018A_30");
        
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getAllUsers();
        
            try(Connection con = ds.getConnection())
            {
                int counter = 0;
                Statement statement = con.createStatement();
                for (User user : users)
                {
                    String SQL = "INSERT INTO [User] (id, name) VALUES(" 
                        + user.getId() + ",'" 
                        + user.getName().replace("'", "") + "');";
                    statement.addBatch(SQL);
                    counter++;
                    if (counter % 10000 == 0)
                    {
                        statement.executeBatch();
                        System.out.println("Added " + counter + " users.");
                    }
                    statement.executeBatch();
                }
        } 
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        public static void migrateRatings() throws IOException
            {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName("10.176.111.31");
            ds.setDatabaseName("movierecsys");
            ds.setUser("CS2018A_30");
            ds.setPassword("CS2018A_30");
        
            RatingDAO ratingDAO = new RatingDAO();
            List<Rating> ratings = ratingDAO.getAllRatings();
        
            try(Connection con = ds.getConnection())
            {
                int counter = 0;
                Statement statement = con.createStatement();
                for (Rating rating : ratings)
                {
                    String SQL = "INSERT INTO Rating (movieID, userID, rating) VALUES(" 
                        + rating.getMovie()+ "," 
                        + rating.getUser() + ","
                        + rating.getRating() + ");";
                    statement.addBatch(SQL);
                    counter++;
                    if (counter % 10000 == 0)
                    {
                        statement.executeBatch();
                        System.out.println("Added " + counter + " ratings.");
                    }
                }
                statement.executeBatch();
                System.out.println("Added " + counter + " ratings");
        } 
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
}

