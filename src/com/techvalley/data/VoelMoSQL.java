package com.techvalley.data;

import java.sql.CallableStatement;

import com.techvalley.dbcon.DatabaseConnection;

public class VoelMoSQL {
	
	DatabaseConnection databaseConn = new DatabaseConnection();
	
	CallableStatement VoelMoCallableStatement = null;
	String VoelMORequestCall = "{ call sp_incomingmomessages(?, ?, ?, ?, ?, ?, ?, ?) }";
	
	public void incomingmomessages(String pTransactionID, String pshortcode, String pmsisdn, String pkeyword, 
			String pservicekeyword, String pmsgtype, String pnetwork, String pcountry){
		
		
			try{
						
				VoelMoCallableStatement = databaseConn.moConnection().prepareCall(VoelMORequestCall);
				VoelMoCallableStatement.setString(1, pTransactionID);
				VoelMoCallableStatement.setString(2, pshortcode);
				VoelMoCallableStatement.setString(3, pmsisdn);
				VoelMoCallableStatement.setString(4, pkeyword);
				VoelMoCallableStatement.setString(5, pservicekeyword);
				VoelMoCallableStatement.setString(6, pmsgtype);
				VoelMoCallableStatement.setString(7, pnetwork);
				VoelMoCallableStatement.setString(8, pcountry);
						
				VoelMoCallableStatement.executeQuery();
						
			} catch(Exception ex){
				ex.printStackTrace();
			} finally {
				cleanConnection();
			}
		
	}
	
	private void cleanConnection() {

		try{
		
	        if (databaseConn.moConnection() != null) {
	        	databaseConn.moConnection().close();
	        }
	        
	        if (VoelMoCallableStatement != null){
	        	VoelMoCallableStatement.close();
	        }
	         
	         
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }
	

}
