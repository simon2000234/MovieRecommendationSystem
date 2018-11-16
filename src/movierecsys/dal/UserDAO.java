/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class UserDAO
{

    /**
     * Gets a list of all known users.
     *
     * @return List of users.
     */
    public List<User> getAllUsers() throws FileNotFoundException, IOException
    {
        List<User> alluser = new ArrayList<>();
        String source = "data/users.txt";
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
                        User user = stringArrayToUser(line);
                        alluser.add(user);
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }

            }

        }
        return alluser;
    }

    private User stringArrayToUser(String line)
    {
        String[] arrUser = line.split(",");

        int id = Integer.parseInt(arrUser[0]);
        String name = arrUser[1];

        User u = new User(id, name);
        return u;
    }

    /**
     * Gets a single User by its ID.
     *
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    public User getUser(int id) throws IOException
    {
        List<User> users = getAllUsers();
        for (User user : users)
        {
            if (user.getId() == id)
            {
                return user;
            }
        }

        return null;
    }

    /**
     * Updates a user so the persistence storage reflects the given User object.
     *
     * @param user The updated user.
     */
    public void updateUser(User user)
    {
        //TODO Update user.
    }

}
