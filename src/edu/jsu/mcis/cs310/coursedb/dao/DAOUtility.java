package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                 ResultSetMetaData meta = rs.getMetaData();
            int numColumns = meta.getColumnCount();

            while (rs.next()) {
                JsonObject row = new JsonObject();
                for (int i = 1; i <= numColumns; i++) {
                    
                    row.put(meta.getColumnName(i),rs.getObject(i));
                }
                records.add(row);
            } 

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
