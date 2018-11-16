/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class RatingDAO
{
    
    /**
     * Persists the given rating.
     * @param rating the rating to persist.
     */
    public void createRating(Rating rating) throws IOException
    {
        PrintWriter writer = new PrintWriter("data/temp_ratings.txt");
        BufferedReader reader = new BufferedReader(new FileReader("data/ratings.txt"));
        String line;
        while ((line = reader.readLine()) != null)
        { 
            writer.println(line);
        }
        writer.println(rating.getMovie().getId() + "," + rating.getUser().getId() + "," + rating.getRating());
        writer.close();
//        File oldFile = new File("data/ratings.txt");
//	File newFile = new File("data/temp_ratings.txt");
//        Files.copy(newFile.toPath(), oldFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        Files.delete(newFile.toPath());
        
    }
    
    /**
     * Updates the rating to reflect the given object.
     * @param rating The updated rating to persist.
     */
    public void updateRating(Rating rating)
    {
        //TODO Update rating
    }
    
    /**
     * Removes the given rating.
     * @param rating 
     */
    public void deleteRating(Rating rating)
    {
        //TODO Delete rating
    }
    
    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings()
    {
        //TODO Get all rating.
        return null;
    }
    
    /**
     * Get all ratings from a specific user.
     * @param user The user 
     * @return The list of ratings.
     */
    public List<Rating> getRatings(User user)
    {
        //TODO Get user ratings.
        return null;
    }
    
}
