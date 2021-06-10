package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class NewProject {

    @FXML
    Button bCreateNewProject;

    @FXML
    TextField tProjectField;

    /**
     * Creates a new project, a static boolean is changed if the project name is applied, in which case another class handles
     * removing the objects of the project as a new one is made. Name of the project is also changed.
     * @param actionEvent The button "Create new project"
     */
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
