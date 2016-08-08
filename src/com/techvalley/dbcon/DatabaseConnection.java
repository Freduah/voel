package com.techvalley.dbcon;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConnection {
	
	public Connection moConnection() throws Exception{
		
		Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/VodaDB");
       
        return ds.getConnection();
	}
	
	public Connection mtConnection() throws Exception{
		
		Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/VodaDB");
       
        return ds.getConnection();
	}
	
public Connection voelmtConnection() throws Exception{
		
		Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/VodaDB");
       
        return ds.getConnection();
	}


}
