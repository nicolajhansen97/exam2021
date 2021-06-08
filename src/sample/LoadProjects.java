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
    Controller controller = new Controller();

    public void initialize ()
    {
      loadProjects();
    }


    public void loadProjects(){
        database.loadProjects();
        lProjectsView.getItems().addAll(database.getTempProject());
    }

    public void getProjectName(ActionEvent actionEvent) {

        controller.setProjectName((String) lProjectsView.getSelectionModel().getSelectedItem());
        Controller.stageLoadProjects.close();
    }
}


