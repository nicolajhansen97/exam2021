package sample;

import java.sql.*;

public class Database {

    public void saveDatabase () {
        try{
        // (1) load the driver into memory

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // (2) establish Connection
        // Connection con= DriverManager.getConnection("jdbc:sqlserver://EASV-THA-Q418\TH:1433;databaseName=DB_JAN","tha","123456");
        Connection con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=sample","sa","bgs68xgn");

        // (3) create the statement
        // Statement stmt = con.createStatement();

        // (3a) Prepare Statement
        PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_StickyNotes VALUES (?,?,?,?,?)");

        // PreparedStatement ps = con.prepareStatement("SELECT * FROM project");
        ps.setInt(1,1);
        ps.setString(2,"bob gik en tur");
        ps.setString(3,"blue");
        ps.setBoolean(4,true);
        ps.setString(5,"300,400");

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


