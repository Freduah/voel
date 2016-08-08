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

import com.techvalley.data.VoelDeliverySQL;


@WebServlet("/voelmtresponse")
public class VoelMtResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	VoelDeliverySQL voelDeliverySQL = new VoelDeliverySQL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;
		
		try{
			
			  builder = factory.newDocumentBuilder();
			  Document xmlDoc = builder.parse(new InputSource(request.getInputStream())); 
			  xmlDoc.getDocumentElement().normalize();	
			 
			  Element rootElement = xmlDoc.getDocumentElement();
			  			  
			  String transactionid = rootElement.getElementsByTagName("transactionid").item(0).getTextContent();
			  String statuscode = rootElement.getElementsByTagName("statuscode").item(0).getTextContent();
			  String status = rootElement.getElementsByTagName("status").item(0).getTextContent();
			  
			  voelDeliverySQL.VoelDeliveryReceipt(transactionid, statuscode, status);
			 
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
