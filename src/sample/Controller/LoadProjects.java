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

    /**
     * This function calls the database and make it load the project that is saved. Then it add everything to the list view,
     * so you easily can pick the project you want on the list and load it.
     */
    public void loadProjects(){
        database.loadProjects();
        lProjectsView.getItems().addAll(database.getTempProject());
    }

    /**
     * This gets the singleton instance and get the names from the projects. It is used to give the names to other classes
     * @param actionEvent
     */
    public void getProjectName(ActionEvent actionEvent) {
        ProjectNameSingleton.getInstance().setS((String) lProjectsView.getSelectionModel().getSelectedItem());
        Controller.stageLoadProjects.close();
    }

    /**
     * This delete the project from the listview, but also from the database. So its delete the project chosed on the listview,
     * in the database, and after that it delete it from the database.
     * @param actionEvent
     */
    public void deleteProject(ActionEvent actionEvent) {
        System.out.println(lProjectsView.getSelectionModel().getSelectedItem().toString());
        database.deleteProject(lProjectsView.getSelectionModel().getSelectedItem().toString());
        lProjectsView.getItems().remove(lProjectsView.getSelectionModel().getSelectedItem());
    }
}


