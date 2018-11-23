/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import movierecsys.be.Movie;
import movierecsys.gui.view.Modle;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{
    Modle mod = new Modle();
    /**
     * The TextField containing the URL of the targeted website.
     */
    @FXML
    private TextField txtMovieSearcjh;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }

    @FXML
    private void handelMovieSeach(ActionEvent event)
    {
        ObservableList<Movie> valgteMovies;
        try
        {
            valgteMovies = FXCollections.observableArrayList(mod.getMrs().searchMovies(txtMovieSearcjh.getText()));
            lstMovies.setItems(valgteMovies);
        } catch (IOException ex)
        {
            Logger.getLogger(MovieRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}