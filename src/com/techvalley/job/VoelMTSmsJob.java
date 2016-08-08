package com.techvalley.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.dbcon.DatabaseConnection;
import com.techvalley.sender.VoelSender;

public class VoelMTSmsJob implements Job {
	
	DatabaseConnection databaseConn = new DatabaseConnection();
	VoelSender voelSender = new VoelSender();
	
	CallableStatement VoelSenderCallableStatement = null;
    ResultSet VoelSenderResult = null;
	String VoelSmsSenderCall = "{ call sp_voelsmssender() }";	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			VoelSenderCallableStatement = databaseConn.mtConnection().prepareCall(VoelSmsSenderCall);
			VoelSenderResult = VoelSenderCallableStatement.executeQuery();
			
			while(VoelSenderResult.next()){
				
				voelSender.VoelMtSmsSender(VoelSenderResult.getString("partnerid"), VoelSenderResult.getString("transactionid"),
						VoelSenderResult.getString("shortcode"), VoelSenderResult.getString("msisdn"), VoelSenderResult.getString("mtmessage"), 
						VoelSenderResult.getString("servicename"), VoelSenderResult.getString("msgtype"), VoelSenderResult.getString("billmtsms"), 
						VoelSenderResult.getString("network"));
				
				System.out.print("Sending MT to elcuto" + VoelSenderResult.getString("partnerid") + " " + VoelSenderResult.getString("transactionid")
						+ " " + VoelSenderResult.getString("shortcode") + " " + VoelSenderResult.getString("msisdn") + " " +  VoelSenderResult.getString("mtmessage")
						+ " " + VoelSenderResult.getString("servicename") + " " + VoelSenderResult.getString("network") + "End \n");
				

			}			
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
		
	        if (databaseConn.mtConnection() != null) {
	        	databaseConn.mtConnection().close();
	        }
	
	         if (VoelSenderCallableStatement != null){
	        	 VoelSenderCallableStatement.close();
	        }
	        
	         if(VoelSenderResult != null){
	        	 VoelSenderResult.close();
	         }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }

}
