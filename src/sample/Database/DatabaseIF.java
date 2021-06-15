package sample.Database;

import sample.Domain.StickyNote;

import java.util.ArrayList;

public interface DatabaseIF {
    void saveDatabase(String pName);
    void loadDatabase(String projectName);
    void createNewProject(String projectName);
    void loadProjects();
    void deleteProject(String pName);
    ArrayList<StickyNote> getTempStickyNote();
    ArrayList<String> getTempProject();
}
