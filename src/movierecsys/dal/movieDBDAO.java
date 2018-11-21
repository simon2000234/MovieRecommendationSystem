/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import movierecsys.be.Movie;

/**
 *
 * @author andre
 */
public class MoviedbDAO 
{

    private DbConnectionProvider dbCon ;

    public MoviedbDAO()
    {
        dbCon= new DbConnectionProvider();
    }
    
    
    
    
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        int id = getNextAvailableMovieID();
        try( Connection con= dbCon.getConnection())
        {
            Statement Statement= con.createStatement();
            Statement.executeQuery("INSERT INTO movie (id,year,title)VALUES ("
            + id + ","
            + releaseYear + ",'"
            + title.replace("'", "") + "');");
            Statement.executeQuery("SELECT * FROM Movie ORDER BY id;");
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return new Movie(id, releaseYear, title);
        
    }
    
    private int getNextAvailableMovieID() throws IOException
    {
        List<Movie> allMovies = getAllMovies();
        int curNextId = 1; //Id skal mindst være 0
        for (int i = 0; i < allMovies.size(); i++)
        {
            if (curNextId != allMovies.get(i).getId())
            {
                return curNextId;
            }
            else 
            {
                curNextId ++;
            }
        }
        return curNextId;
    }

    public void deleteMovie(Movie movie) throws FileNotFoundException, IOException
    {
        try( Connection con= dbCon.getConnection())
        {
            Statement Statement= con.createStatement();
            Statement.executeQuery("DELETE FROM Movie WHERE id = " 
                    + movie.getId() + ";");
        }   
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() throws IOException
    {
      
       List<Movie> movies = new ArrayList<>();
        try( Connection con= dbCon.getConnection())
       {
          Statement Statement= con.createStatement();
          ResultSet rs= Statement.executeQuery("SELECT * FROM movie");
          while(rs.next())
          {
              int id = rs.getInt("id");
              int year = rs.getInt("year");
              String title = rs.getString("title");
              System.out.println(""+ id+ " " + title);
              Movie movie = new Movie(id, year,title);
          movies.add(movie);
          }                 
        } catch (SQLException ex)
        {
            ex.printStackTrace();
       }
     
        
        return movies;
        
    }

    public Movie getMovie(int id) throws IOException
    {
        List<Movie> movies = getAllMovies();
        for (int i = 0; i < movies.size(); i++)
        {
            if (id == movies.get(i).getId())
            {
                return movies.get(i);
            }
        }
        return null;
    }

    public void updateMovie(Movie movie) throws IOException
    {
        try( Connection con= dbCon.getConnection())
        {
            Statement Statement= con.createStatement();
            Statement.executeQuery("UPDATE Movie SET year = " 
                    + movie.getYear() + " , title = '" 
                    + movie.getTitle() + "' WHERE id=" 
                    + movie.getId() + ";");
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
