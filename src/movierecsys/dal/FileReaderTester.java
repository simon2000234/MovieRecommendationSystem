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
    userDBDAO userdbdao = new userDBDAO();
    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException, SQLException
    {
        
    }

    public static void mitigateMovie() throws IOException
    {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("andr_mrs");
        ds.setUser("CS2018A_3");
        ds.setPassword("CS2018A_3");

        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies = movieDAO.getAllMovies();

        movieDBDAO movieDBDAO = new movieDBDAO();

        try (Connection con = ds.getConnection())
        {
            Statement Statement = con.createStatement();

            for (Movie movie : movies)
            {
                String sql = "INSERT INTO movie (id,year,title)VALUES ("
                        + movie.getId() + ","
                        + movie.getYear() + ",'"
                        + movie.getTitle().replace("'", "") + "')";
                System.out.println(sql);
                int i = Statement.executeUpdate(sql);
                System.out.println("affected row =" + i);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    
    public static void mitigateUser() throws IOException
    {
    SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("andr_mrs");
        ds.setUser("CS2018A_3");
        ds.setPassword("CS2018A_3");

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();

        userDBDAO userDBDAO = new userDBDAO();

        try (Connection con = ds.getConnection())
        {
            Statement Statement = con.createStatement();
            for (User user : users)
            {
                String sql = "INSERT INTO [User] (id,name)VALUES ("
                        + user.getId() + ",'"
                        + user.getName() + "');";
                System.out.println(sql);
                int i = Statement.executeUpdate(sql);
                System.out.println("affected row =" + i);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    
    }
    
    
     public static void mitigateRatings() throws IOException
    {
        List<Rating> allRatings = new RatingDAO().getAllRatings();
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("andr_mrs");
        ds.setUser("CS2018A_3");
        ds.setPassword("CS2018A_3");
        try (Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            int counter = 0;
            for (Rating rating : allRatings)
            {
                String sql = "INSERT INTO Rating (movieID, userID, rating) VALUES ("
                        + rating.getMovie() + ","
                        + rating.getUser() + ","
                        + rating.getRating()
                        + ");";
                st.addBatch(sql);
                counter++;
                if (counter % 10000 == 0)
                {
                    st.executeBatch();
                    System.out.println("Added "+counter+" ratings.");
                }
            }
            if (counter % 10000 != 0)
            {
                st.executeBatch();
                System.out.println("Added final batch of ratings.");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
}
