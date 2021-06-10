package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class NewProject {

    @FXML
    Button bCreateNewProject;

    @FXML
    TextField tProjectField;


    public void createProject(ActionEvent actionEvent) {
        ProjectNameSingleton.getInstance().setS(tProjectField.getText());
        Controller.button.createNewProject(ProjectNameSingleton.getInstance().getS());
        Controller.newProjectWasPressed = true;
        Controller.stageNewProject.close();
    }


}
