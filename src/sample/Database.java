package sample;

import java.sql.*;

public class Database {

    public void saveDatabase () {
        try{
        // (1) load the driver into memory

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // (2) establish Connection
        // Connection con= DriverManager.getConnection("jdbc:sqlserver://EASV-THA-Q418\TH:1433;databaseName=DB_JAN","tha","123456");
        Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DB_Stickynote","sa","123456");

        // (3) create the statement
        // Statement stmt = con.createStatement();

        // (3a) Prepare Statement
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_StickyNotes VALUES (?,?,?,?,?)");

        // PreparedStatement ps = con.prepareStatement("SELECT * FROM project");
        ps.setInt(1,StickyListSingleton.getInstance().getArray().get(1).getID());
        ps.setString(2,StickyListSingleton.getInstance().getArray().get(1).getSomeText());
        ps.setString(3,StickyListSingleton.getInstance().getArray().get(1).getColorString());
        ps.setBoolean(4,StickyListSingleton.getInstance().getArray().get(1).getUpOrDown());
        ps.setString(5,StickyListSingleton.getInstance().getArray().get(1).getXCoordinate() + "," +
                StickyListSingleton.getInstance().getArray().get(1).getYCoordinate());

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

        int rows = ps.executeUpdate();
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
}


