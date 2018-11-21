/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.util.List;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author Melchertsen
 */
public interface IRatingCRUD
{

    /**
     * Persists the given rating.
     * @param rating the rating to persist.
     */
    void createRating(Rating rating) throws IOException;

    /**
     * Removes the given rating.
     * @param rating
     */
    void deleteRating(Rating rating) throws IOException;

    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    List<Rating> getAllRatings() throws IOException;

    /**
     * Get all ratings from a specific user.
     * @param user The user
     * @return The list of ratings.
     */
    List<Rating> getRatings(User user) throws IOException;

    /**
     * Updates the rating to reflect the given object.
     * @param rating The updated rating to persist.
     */
    void updateRating(Rating rating) throws IOException;
    
}
