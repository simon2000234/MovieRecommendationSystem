/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

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
public class movieDBDAO implements IMovieRepository
{

    private DbConnectionProvider dbCon ;

    public movieDBDAO()
    {
        dbCon= new DbConnectionProvider();
    }
    
    
    
    
    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMovie(Movie movie) throws FileNotFoundException, IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllMovies() throws IOException
    {
      
       List<Movie>movies = new ArrayList<>();
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
//                   
//                    
        } catch (SQLException ex)
        {
            ex.printStackTrace();
       }
     
        
        return null;
        
    }

    @Override
    public Movie getMovie(int id) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
