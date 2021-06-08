package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class newProject {

    @FXML
    Button bCreateNewProject;

    @FXML
    TextField tProjectField;

    String projectName;

    Controller controllerIntance = new Controller();
    Database databaseInstance = new Database();

    public void createProject(ActionEvent actionEvent) {
        projectName = tProjectField.getText();
        databaseInstance.createNewProject(projectName);
        controllerIntance.setProjectName(projectName);
    }


}
