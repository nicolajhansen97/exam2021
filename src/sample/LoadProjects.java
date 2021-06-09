package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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
        lProjectsView.getItems().remove(lProjectsView.getSelectionModel().getSelectedItem());

    }
}


