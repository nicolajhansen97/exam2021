package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {


    private ArrayList<StickyNote> tempStickyNote = new ArrayList<>();

    private ArrayList<String> tempProject = new ArrayList<>();

    public void saveDatabase() {
        try{
        // (1) load the driver into memory

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // (2) establish Connection
        // Connection con= DriverManager.getConnection("jdbc:sqlserver://EASV-THA-Q418\TH:1433;databaseName=DB_JAN","tha","123456");
        Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa","123456");


        // (3) create the statement
        // Statement stmt = con.createStatement();
            String sql = "SELECT * FROM tblNotes";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String bName = rs.getString("fldBoardName");
                if (bName.equals("TestBoard")){
                    rs.deleteRow();
                }
            }

        // (3a) Prepare Statement
        PreparedStatement ps = con.prepareStatement("INSERT INTO tblNotes VALUES (?,?,?,?,?,?,?)");



            // PreparedStatement ps = con.prepareStatement("SELECT * FROM project");
        for (int i = 0; i < StickyListSingleton.getInstance().getArray().size(); i++) {

            ps.setInt(1,StickyListSingleton.getInstance().getArray().get(i).getID());
            ps.setString(2,"TestBoard");
            ps.setString(4,StickyListSingleton.getInstance().getArray().get(i).getSomeText());
            ps.setString(3,StickyListSingleton.getInstance().getArray().get(i).getColorString());
            ps.setBoolean(5,StickyListSingleton.getInstance().getArray().get(i).getUpOrDown());
            ps.setDouble(6,StickyListSingleton.getInstance().getArray().get(i).getXCoordinate());
            ps.setDouble(7,StickyListSingleton.getInstance().getArray().get(i).getYCoordinate());

            int rows = ps.executeUpdate();
        }

            System.out.println("Saved in database");

          /*
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
                    // Project p = new Project();
            while(rs.next()){

                for (int num = 1; num <= columns; num++){
                    System.out.print(rs.getString(num));
                }
                System.out.println();

                // process data field by field


            }
            */


        // (4) execute the SQL query
        //int rows = stmt.executeUpdate("INSERT INTO project VALUES ('a1','Venus',56987)");

        // System.out.println("number of rows affected = "+rows);

        // (5) close the connection
        ps.close();
        con.close();
        stmt.close();
        rs.close();

    }catch(Exception e){
        e.printStackTrace();
    }
}

    public void loadDatabase() {
        tempStickyNote.clear();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa","123456");

            int count = 0;
            String sql = "SELECT * FROM tblNotes";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String bName = rs.getString("fldBoardName");
                if (bName.equals("TestBoard")){
                    tempStickyNote.add(new StickyNote());
                    tempStickyNote.get(count).setSavedText(rs.getString(4));
                    tempStickyNote.get(count).setUpOrDown(rs.getBoolean(5));
                    tempStickyNote.get(count).setID(rs.getInt(1));
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
            // Connection con= DriverManager.getConnection("jdbc:sqlserver://EASV-THA-Q418\TH:1433;databaseName=DB_JAN","tha","123456");
            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa","123456");

            // (3a) Prepare Statement
            PreparedStatement ps = con.prepareStatement("INSERT INTO tblBoard VALUES (?, ?)");



            // PreparedStatement ps = con.prepareStatement("SELECT * FROM project");

            ps.setString(1,projectName);
            ps.setInt(2,1);

            System.out.println("Saved in database");

              /*
                ResultSet rs = ps.executeQuery();

                ResultSetMetaData rsmd = rs.getMetaData();

                int columns = rsmd.getColumnCount();
                        // Project p = new Project();
                while(rs.next()){

                    for (int num = 1; num <= columns; num++){
                        System.out.print(rs.getString(num));
                    }
                    System.out.println();

                    // process data field by field


                }
                */


            // (4) execute the SQL query
            //int rows = stmt.executeUpdate("INSERT INTO project VALUES ('a1','Venus',56987)");

            // System.out.println("number of rows affected = "+rows);

            // (5) close the connection
            ps.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadProjects() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=stickyNotesProgram","sa","123456");

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

    public ArrayList<StickyNote> getTempStickyNote() {
        return tempStickyNote;
    }

    public ArrayList<String> getTempProject() {
        return tempProject;
    }

}


