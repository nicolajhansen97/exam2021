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
        if (ProjectNameSingleton.getInstance().getS() == null){
            Controller.newProjectWasPressed = false;
        } else {
            Controller.newProjectWasPressed = true;
        }
        ProjectNameSingleton.getInstance().setS(tProjectField.getText());
        Controller.databaseInstance.createNewProject(ProjectNameSingleton.getInstance().getS());
        Controller.stageNewProject.close();
    }
}
