package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private String password = "datamatiker";

    //temporary lists gained from the database to apply in the functions that add sticky notes to the project
    private ArrayList<StickyNote> tempStickyNote = new ArrayList<>();
    private ArrayList<String> tempProject = new ArrayList<>();

    /**
     * This method first deletes all excisting entries in the stickynote table that matches the project name in the parameter
     * Then it inserts the current values of the Stickynote Singleton into the database with the correct project name
     * @param pName The project name
     */
    public void saveDatabase(String pName) {
        try{
        // (1) load the driver into memory

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // (2) establish Connection
        Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa",password);


        // (3) create the statement
            String sql = "SELECT * FROM tblNotes";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String bName = rs.getString("fldBoardName");
                if (bName.equals(pName)){
                    rs.deleteRow();
                }
            }

        // (3a) Prepare Statement
        PreparedStatement ps = con.prepareStatement("INSERT INTO tblNotes VALUES (?,?,?,?,?,?)");

        for (int i = 0; i < StickyListSingleton.getInstance().getArray().size(); i++) {

            ps.setString(1,pName);
            ps.setString(2,StickyListSingleton.getInstance().getArray().get(i).getColorString());
            ps.setString(3,StickyListSingleton.getInstance().getArray().get(i).getSomeText());
            ps.setBoolean(4,StickyListSingleton.getInstance().getArray().get(i).getUpOrDown());
            ps.setDouble(5,StickyListSingleton.getInstance().getArray().get(i).getXCoordinate());
            ps.setDouble(6,StickyListSingleton.getInstance().getArray().get(i).getYCoordinate());

            int rows = ps.executeUpdate();
        }

            System.out.println("Saved in database");

        // (5) close the connection
        ps.close();
        con.close();
        stmt.close();
        rs.close();

    }catch(Exception e){
        e.printStackTrace();
    }
}

    /**
     * This loads all the sticky notes and their values into the workspace, it uses the project name to get the correct stickynotes
     * @param projectName The project name used to locate the right sticky notes.
     */
    public void loadDatabase(String projectName) {
        tempStickyNote.clear();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa",password);

            int count = 0;
            String sql = "SELECT * FROM tblNotes";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String bName = rs.getString("fldBoardName");
                if (bName.equals(projectName)){
                    tempStickyNote.add(new StickyNote());
                    tempStickyNote.get(count).setSavedText(rs.getString(4));
                    tempStickyNote.get(count).setUpOrDown(rs.getBoolean(5));
                    tempStickyNote.get(count).setColorToString(rs.getString(3));
                    tempStickyNote.get(count).setCoordinate(rs.getDouble(6), rs.getDouble(7));
                    count++;
                }
            }

            con.close();
            stmt.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Creates a new project, in which sticky notes can be saved.
     * Currently the project is made using a hardcoded user in the user table, this will be changed once logins
     * and profiles are implemented.
     * @param projectName Name of the project made in the DB,
     */
    public void createNewProject(String projectName){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // (2) establish Connection
            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa",password);

            // (3a) Prepare Statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO tblBoard VALUES (?, ?)");

            ps.setString(1,projectName);
            ps.setInt(2,1);

            ps.executeUpdate();
            System.out.println("Saved in database");
            ps.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Loads the projects from the projects table, to be used in a listview where the correct project can be selected.
     * The projects get added to a temporary arraylist that can then be getted by another function that might need it.
     */
    public void loadProjects() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa",password);

            String sql = "SELECT * FROM tblBoard";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                tempProject.add(rs.getString(1));
            }

            con.close();
            stmt.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes projects and associated stickynotes from the database, using the projectname as reference.
     * First the sticky notes are deleted, the the project.
     * @param pName Name of the project to be deleted.
     */
    public void deleteProject(String pName){
        try{
            // (1) load the driver into memory

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // (2) establish Connection
            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa",password);

            System.out.println(pName);

            // (3) create the statement
            String sql = "SELECT * FROM tblNotes";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String bName = rs.getString("fldBoardName");
                if (bName.equals(pName)){
                    rs.deleteRow();
                }
            }


            rs.close();

            stmt = con.createStatement();

            // (3a) Prepare Statement
            PreparedStatement ps = con.prepareStatement("DELETE FROM tblBoard WHERE fldBoardName = (?)");

            // PreparedStatement ps = con.prepareStatement("SELECT * FROM project");
            ps.setString(1, pName);

            int rows = ps.executeUpdate();

            System.out.println("Deleted in database");



            // (5) close the connection
            con.close();
            stmt.close();
            ps.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Getter for the temporary sticky notes
     * @return returns a list of stickynotes to be used in other methods..
     */
    public ArrayList<StickyNote> getTempStickyNote() {
        return tempStickyNote;
    }

    /**
     * Getter for the temporary project list
     * @return returns a list of the projects to be used by listviews.
     */
    public ArrayList<String> getTempProject() {
        return tempProject;
    }

}


