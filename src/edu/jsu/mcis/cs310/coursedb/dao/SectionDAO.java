package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;


public class SectionDAO {
    
    // INSERT YOUR CODE HERE
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
            ps = conn.prepareStatement("SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn");
            ps.setInt(1, termid);
            ps.setString(2, subjectid);
            ps.setString(3, num);

            rs = ps.executeQuery();

            rsmd = rs.getMetaData();

            JsonArray jsonArray = new JsonArray();

            while (rs.next()) {

                JsonObject jsonObject = new JsonObject();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(columnName);
                    jsonObject.put(columnName, columnValue);
                }

                jsonArray.add(jsonObject);
            }

            result = jsonArray.toString();

            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}