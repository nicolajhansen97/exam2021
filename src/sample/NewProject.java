package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class NewProject {

    @FXML
    Button bCreateNewProject;

    @FXML
    TextField tProjectField;

    String projectName;

    Controller controllerIntance = new Controller();

    public void createProject(ActionEvent actionEvent) {
        projectName = tProjectField.getText();
        Controller.button.createNewProject(projectName);
        controllerIntance.setProjectName(projectName);
        Controller.stageNewProject.close();
    }


}
