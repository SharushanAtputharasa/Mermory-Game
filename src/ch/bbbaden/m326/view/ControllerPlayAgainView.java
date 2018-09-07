/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m326.view;

import ch.bbbaden.m326.mermorygame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sharu
 */
public class ControllerPlayAgainView implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void reset(ActionEvent event) throws IOException {
        Stage stage = mermorygame.getStage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ch/bbbaden/m326/view/FXMLDocument.fxml"));
        
                    Scene scene = new Scene(root);
        
                    stage.setScene(scene);
                    stage.show();
        
        
    }
    
    @FXML
    private void exit() {
        System.exit(0);
    }
    
}
