package com.techvalley.data;

import java.sql.CallableStatement;

import com.techvalley.dbcon.DatabaseConnection;

public class VoelDeliverySQL {
	
	
	DatabaseConnection databaseConn = new DatabaseConnection();
	
	CallableStatement VoelDeliveryReceiptCallableStatement = null;
	String VoelDeliveryReceiptCall = "{ call sp_deliveryreceipt(?, ?, ?) }";
	
	public void VoelDeliveryReceipt(String transactionid, String statuscode, String status){
		
		try{
			
			VoelDeliveryReceiptCallableStatement = databaseConn.voelmtConnection().prepareCall(VoelDeliveryReceiptCall);
			VoelDeliveryReceiptCallableStatement.setString(1, transactionid);
			VoelDeliveryReceiptCallableStatement.setString(2, statuscode);
			VoelDeliveryReceiptCallableStatement.setString(3, status);

					
			VoelDeliveryReceiptCallableStatement.executeQuery();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
	}
	
	private void cleanConnection() {

		try{
		
	        if (databaseConn.voelmtConnection() != null) {
	        	databaseConn.voelmtConnection().close();
	        }
	        
	        if (VoelDeliveryReceiptCallableStatement != null){
	        	VoelDeliveryReceiptCallableStatement.close();
	        }
	         
	         
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }
	

}
