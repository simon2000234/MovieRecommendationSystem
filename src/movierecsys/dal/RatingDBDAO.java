/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author Melchertsen
 */
public class RatingDBDAO
{
    DbConnectionProvider db = new DbConnectionProvider();
    public RatingDBDAO()
    {
        db= new DbConnectionProvider();
    }
    
    
    public void createRating(Rating rating) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteRating(Rating rating) throws IOException
    {
        try( Connection con= db.getConnection())
       {
          Statement Statement= con.createStatement();
          Statement.executeQuery("DELETE FROM Rating WHERE movieID="+rating.getMovie()+" AND userID="+rating.getUser()+" AND rating="+rating.getRating());
       }catch (SQLException ex)
       {
       ex.printStackTrace();
       }
    }

    public List<Rating> getAllRatings() throws IOException
    {
        List<Rating> ratings = new ArrayList();
        try( Connection con= db.getConnection())
       {
          Statement Statement= con.createStatement();
          ResultSet rs= Statement.executeQuery("SELECT * FROM Rating");
          while (rs.next())
          {
              int movieID = rs.getInt("movieID");
              int userID = rs.getInt("userID");
              int rating = rs.getInt("rating");
              Rating therating = new Rating(movieID, userID, rating);
              ratings.add(therating);
          }
        }   
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ratings;
    }

    
    
    public List<Rating> getRatings(User user) throws IOException
    {
        List<Rating> ratings = getAllRatings();
        List<Rating> userRatings = new ArrayList();
        for (int i = 0; i < ratings.size(); i++)
        {
            if(ratings.get(i).getUser() == user.getId())
            {
                userRatings.add(ratings.get(i));
            }
        }
        if(userRatings.isEmpty())
        {
            return null;
        }
        else
        {
            return userRatings;
        }
    }

    
    public void updateRating(Rating rating) throws IOException
    {
        try( Connection con= db.getConnection())
       {
          Statement Statement= con.createStatement();
          ResultSet rs= Statement.executeQuery("UPDATE Rating SET rating = " 
                  + rating.getRating() +  " WHERE movieID = " + rating.getMovie() 
                  + " AND userID = " + rating.getUser() + ";");
        }   
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
