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
import movierecsys.be.Movie;
import movierecsys.be.User;

/**
 *
 * @author andre
 */
public class UserDBDAO
{

    private DbConnectionProvider dbCon;

    public UserDBDAO()
    {
        dbCon = new DbConnectionProvider();
    }

    public User createUser(String name) throws IOException
    {
        int id = getNextAvailableUserID();
        User user = new User(id, name);
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO [User] (id,name)VALUES (" + id + ",'" + name + "')";
            Statement statement = con.createStatement();
            statement.executeLargeUpdate(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return user;
    }
    private int getNextAvailableUserID() throws IOException
    {
        List<User> allUsers = getAllUser();
        int curNextId = 1; //Id skal mindst være 0
        for (int i = 0; i < allUsers.size(); i++)
        {
            if (curNextId != allUsers.get(i).getId())
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

    public void deleteUser(User user) throws FileNotFoundException, IOException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "DELETE FROM [User] WHERE id=" + user.getId();
            Statement statement = con.createStatement();
            statement.executeLargeUpdate(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUser() throws IOException
    {
        List<User> allUser = new ArrayList<>();
        try (Connection con = dbCon.getConnection())
        {
            Statement Statement = con.createStatement();
            ResultSet rs = Statement.executeQuery("SELECT * FROM User");
            while (rs.next())
            {
                int id = rs.getInt("id");
                String Name = rs.getString("name");

                System.out.println("" + id + " " + Name);
                User user = new User(id, Name);
                allUser.add(user);
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return allUser;
    }

    public User getUser(int id) throws IOException, SQLServerException, SQLException
    {
        
      try (Connection con = dbCon.getConnection())
        {
            Statement Statement = con.createStatement();
          ResultSet rs= Statement.executeQuery("SELECT * FROM [User] WHERE id="+id);
         while (rs.next())
            {
                int uid = rs.getInt("id");
                String Name = rs.getString("name");

                //System.out.println("" + uid + " " + Name);
                User user = new User(uid, Name);
                return user;
            }
        } catch(SQLException ex){
        
        ex.printStackTrace();
        }   
      return null;
    }

    public void updateUser(User user) throws IOException, SQLServerException, SQLException
    {
         try (Connection con = dbCon.getConnection())
        {
        Statement Statement = con.createStatement();
         Statement.executeLargeUpdate("UPDATE [User] SET name ='"+ user.getName()+"' WHERE id="+ user.getId());
        }
    }

}
