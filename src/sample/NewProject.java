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

    public void createProject(ActionEvent actionEvent) {
        projectName = tProjectField.getText();
        Controller.button.createNewProject(projectName);
        ProjectNameSingleton.getInstance().setS(projectName);
        Controller.stageNewProject.close();
    }


}
