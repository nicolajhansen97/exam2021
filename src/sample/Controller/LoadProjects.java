package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sample.Database.Database;
import sample.Domain.ProjectNameSingleton;

public class LoadProjects {

    @FXML
    ListView lProjectsView;

    @FXML
    Button bLoadProjectName;

    Database database = new Database();

    public void initialize ()
    {
      loadProjects();
    }

    public void loadProjects(){
        database.loadProjects();
        lProjectsView.getItems().addAll(database.getTempProject());
    }

    public void getProjectName(ActionEvent actionEvent) {
        ProjectNameSingleton.getInstance().setS((String) lProjectsView.getSelectionModel().getSelectedItem());
        Controller.stageLoadProjects.close();
    }

    public void deleteProject(ActionEvent actionEvent) {
        System.out.println(lProjectsView.getSelectionModel().getSelectedItem().toString());
        database.deleteProject(lProjectsView.getSelectionModel().getSelectedItem().toString());
        lProjectsView.getItems().remove(lProjectsView.getSelectionModel().getSelectedItem());
    }
}


