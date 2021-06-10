package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private String password = "123456";

    private ArrayList<StickyNote> tempStickyNote = new ArrayList<>();

    private ArrayList<String> tempProject = new ArrayList<>();

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
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<StickyNote> getTempStickyNote() {
        return tempStickyNote;
    }

    public ArrayList<String> getTempProject() {
        return tempProject;
    }

}


