/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
        writer.println(rating.getMovie() + "," + rating.getUser() + "," + rating.getRating());
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
    public void updateRating(Rating rating) throws IOException
    {
        List<Rating> newRatingList = getAllRatings();
        for (int i = 0; i < newRatingList.size(); i++)
        {
            if (newRatingList.get(i).getUser() == rating.getUser() && newRatingList.get(i).getMovie() == rating.getMovie() )
            {
                newRatingList.get(i).setRating(rating.getRating());
            }
        }
        PrintWriter writer = new PrintWriter("data/temp_ratings.txt");
        for (int i = 0; i < newRatingList.size(); i++)
        {
            writer.println(newRatingList.get(i).toFileFormat());
        }

        writer.close();
        File oldFile = new File("data/ratings.txt");
	File newFile = new File("data/temp_ratings.txt");
        Files.copy(newFile.toPath(), oldFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(newFile.toPath());
    }
    
    /**
     * Removes the given rating.
     * @param rating 
     */
    public void deleteRating(Rating rating) throws IOException
    {
        PrintWriter writer = new PrintWriter("data/temp_ratings.txt");
        BufferedReader reader = new BufferedReader(new FileReader("data/ratings.txt"));
        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.equals(rating.toFileFormat()))
            {
            }
            else
            {
                writer.println(line);
            }
        }
        writer.close();
        reader.close();
//        File oldFile = new File("data/ratings.txt");
//	File newFile = new File("data/temp_ratings.txt");
//        Files.copy(newFile.toPath(), oldFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        Files.delete(newFile.toPath());
    }
    
    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings() throws IOException
    {
        List<Rating> allRatings = new ArrayList<>();
        String source = "data/ratings.txt";
        File file = new File(source);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Using a try with resources!
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    try
                    {
                        Rating rat = stringArrayToRating(line);
                        allRatings.add(rat);
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return allRatings;
    }
    private Rating stringArrayToRating(String line) throws IOException
    {
        String[] arrRating = line.split(",");
        
        int movieid = Integer.parseInt(arrRating[0]);
        int userID = Integer.parseInt(arrRating[1]);
        int rating = Integer.parseInt(arrRating[2]);

        Rating rat = new Rating(movieid, userID, rating);
        return rat;
    }
    
    /**
     * Get all ratings from a specific user.
     * @param user The user 
     * @return The list of ratings.
     */
    public List<Rating> getRatings(User user) throws IOException
    {
        ArrayList<Rating> userRatings = new ArrayList<>();
        List<Rating> allRatings = getAllRatings();
        
        for (int i = 0; i < allRatings.size(); i++)
        {
            if (allRatings.get(i).getUser() == user.getId())
            {
                userRatings.add(allRatings.get(i));
            }
        }
        if (userRatings.isEmpty())
        {
            return null;
        }
        else 
        {
            return userRatings;
        }
    }
    
}
