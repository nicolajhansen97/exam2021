package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LoadProjects {

    @FXML
    ListView lProjectsView;

    public void initialize ()
    {
      loadProjects();
    }


    public void loadProjects(){
        Database database = new Database();
        database.loadProjects();

        lProjectsView.getItems().addAll(database.getTempProject());

    }
}


