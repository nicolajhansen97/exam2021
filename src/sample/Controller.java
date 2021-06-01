package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {

    public void initialize (){

    }

    public void handleSingleUserVersion(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("singleUserVersion.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1500,900);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            scene.getWindow().centerOnScreen();
            stage.show();

        }catch (Exception e)
        {e.printStackTrace();}
    }

    public void handleMultiUserVersion(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("multiUserVersion.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1500,900);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            scene.getWindow().centerOnScreen();
            stage.show();

        }catch (Exception e)
        {e.printStackTrace();}
    }



}

