package com.techvalley.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.techvalley.data.VoelMoSQL;


@WebServlet("/morequest")
public class Morequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	VoelMoSQL voelMoSQL = new VoelMoSQL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;
        
		try{
			
			  builder = factory.newDocumentBuilder();
			  Document xmlDoc = builder.parse(new InputSource(request.getInputStream())); 
			  xmlDoc.getDocumentElement().normalize();	
			 
			  Element rootElement = xmlDoc.getDocumentElement();
			  			  
			  String TransactionID = rootElement.getElementsByTagName("TransactionID").item(0).getTextContent();			  
			  String shortcode = rootElement.getElementsByTagName("shortcode").item(0).getTextContent();
			  String msisdn = rootElement.getElementsByTagName("msisdn").item(0).getTextContent();
			  String MOmessage = rootElement.getElementsByTagName("MOmessage").item(0).getTextContent();
			  String servicekeyword = rootElement.getElementsByTagName("servicekeyword").item(0).getTextContent();
			  String msgtype = rootElement.getElementsByTagName("msgtype").item(0).getTextContent();
			  String network = rootElement.getElementsByTagName("network").item(0).getTextContent();
			  String country = rootElement.getElementsByTagName("country").item(0).getTextContent();
			  
			  voelMoSQL.incomingmomessages(TransactionID, shortcode, msisdn, MOmessage, servicekeyword, msgtype, network, country);
			  
		      System.out.print("Receiving MO Messages" + TransactionID + " " + shortcode + " " + msisdn + " " +
		    		  MOmessage + " " + servicekeyword + " " + msgtype + " " + network + " " + country);
		      
		      
		      if(xmlDoc != null){
			    	
			    	response.setContentType("text/xml; charset=UTF-8");
					response.getWriter().print(moResponseXML());			    	
			  }
			  
		      
		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	private String moResponseXML(){
		
		StringBuilder sb = new StringBuilder("<elcmoresponse>");
		sb.append("<statuscode>0</statuscode>")
		.append("<status>success</status>")
		.append("</elcmoresponse >");
		
	 return sb.toString();
		
	}

}
