package com.techvalley.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VoelSender {
	
	
	public void VoelMtSmsSender(String partnerid, String transactionid, String shortcode, String msisdn, String mtmessage,
			String servicename, String msgtype, String billmtsms, String network){
		
		URL urlObject;
		String SmsXMLDoc =  VoelXmlSender(partnerid,transactionid,shortcode,msisdn,mtmessage,servicename,msgtype,billmtsms,network);
		
		try {
			urlObject = new URL("http://216.93.184.181:4505/sendmtsms/submitMTsms");
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			con.addRequestProperty("Content-Length", "" + SmsXMLDoc.getBytes().length);
	       
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(SmsXMLDoc);
			wr.flush();
			wr.close();
			
			con.connect();
	        
	        int responseCode = con.getResponseCode();
            if(responseCode == 200) {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

                System.out.println("Submission Result MO: " + in.readLine());
                in.close();
            }
	            
		} catch (Exception e) {
			// TODO Auto-generated catch block  Exception 
			e.printStackTrace();
		} 			
		
	}
	
	
	
	private String VoelXmlSender(String partnerid, String transactionid, String shortcode, String msisdn, String mtmessage,
			String servicename, String msgtype, String billmtsms, String network){
		
		StringBuilder sb = new StringBuilder("<elcsubmitmtsms>");
		sb.append("<partnerid>").append(partnerid).append("</partnerid>")
		.append("<transactionid>").append(transactionid).append("</transactionid>")
		.append("<shortcode>").append(shortcode).append("</shortcode>")
		.append("<msisdn>").append(msisdn).append("</msisdn>")
		.append("<mtmessage>").append(mtmessage).append("</mtmessage>")		
		.append("<servicename>").append(servicename).append("</servicename>")
		.append("<msgtype>").append(msgtype).append("</msgtype>")
		.append("<billmtsms>").append(billmtsms).append("</billmtsms>")
		.append("<network>").append(network).append("</network>")		
		.append("</elcsubmitmtsms>");
		
		return sb.toString();
	}
	

}
