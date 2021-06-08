package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class newProject {

    @FXML
    Button bCreateNewProject;

    @FXML
    TextField tProjectField;

    public void createProject(ActionEvent actionEvent) {
        tProjectField.getText();
    }
}
