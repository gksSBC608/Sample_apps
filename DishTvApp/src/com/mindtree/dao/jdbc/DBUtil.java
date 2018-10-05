/**
 * 
 */
package com.mindtree.dao.jdbc;
import java.sql.*;

import com.mindtree.exceptions.DBUtilityException;
/**
 * @author M1030608
 *
 */
public class DBUtil {
	 private static final String DRIVER = "com.mysql.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/DISH_DB";
	    private static final String USER = "root";
	    private static final String PWD = "Welcome123";

	    /**
	     * private default constructor to prevent instantiation of utility class
	     */
	    private DBUtil() {
	    }

	    static {
	    	try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }
	     
	    /**
	     * 
	     * @return database connection
	     * @throws DBUtilityException 
	     */
	    public static Connection getConnection() throws DBUtilityException  {
	        try {
				return DriverManager.getConnection(URL, USER, PWD);
			} catch (SQLException e) {
				throw new DBUtilityException("Error in establishing connection",e);
			}
	    }

	    /**
	     * 
	     * @param st
	     *            Statement to release
	     * @throws DBUtilityException 
	     */
	    public static void releaseResource(Statement st) throws DBUtilityException  {
	    	if(st!= null){
	    		try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DBUtilityException("Error in releasing Statement",e);
				}
	    	}
	    }
	    
	    /**
	     * 
	     * @param ps
	     *            PreparedStatement to release
	     * @throws DBUtilityException 
	     */
	    public static void releaseResource(PreparedStatement ps) throws DBUtilityException  {
	    	if(ps!=null){
	    		try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DBUtilityException("Error in releasing PS",e);
				}
	    	}
	    }

	    /**
	     * 
	     * @param con
	     *            connection to release
	     * @throws DBUtilityException 
	     */
	    public static void releaseResource(Connection con) throws DBUtilityException  {
	    	if(con != null) {
	    		try {
					con.close();
				} catch (SQLException e) {
					throw new DBUtilityException("Error in relesing Connection",e);
				}
	    	}
	    }

}
