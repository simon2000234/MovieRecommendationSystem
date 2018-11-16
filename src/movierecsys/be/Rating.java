/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.be;

/**
 *
 * @author pgn
 */
public class Rating
{

    public static final int SUPER_BAD = -5;
    public static final int BAD = -3;
    public static final int NEUTRAL = 1;
    public static final int GOOD = 3;
    public static final int SUPER_GOOD = 5;

    private final int movieID;
    private final int userID;
    private int rating;

    /**
     * Constructs a new rating.
     *
     * @param rating The value of the rating. Only the constants of the Rating
     * class are allowed values.
     */
    public Rating(int movieID, int userID, int rating)
    {
        this.movieID = movieID;
        this.userID = userID;
        this.rating = rating;
    }

    /**
     * Sets the rating to a new value. Only the constants of the Rating class
     * are allowed values.
     *
     * @param rating The rating to set.
     */
    public void setRating(int rating)
    {
        this.rating = rating;
    }

    /**
     * Gets the movie being rated.
     *
     * @return A movie
     */
    public int getMovie()
    {
        return movieID;
    }

    /**
     * Gets the rating user.
     *
     * @return A user.
     */
    public int getUser()
    {
        return userID;
    }

    /**
     * Gets the rating value.
     *
     * @return An integer.
     */
    public int getRating()
    {
        return rating;

    }   

    
    
    @Override
    public String toString()
    {
        return "Movie=" + movieID + " user=" + userID + " rating=" + rating;
    }

    public String toFileFormat()
    {
        return movieID + "," + userID + "," + rating;
    }

}
