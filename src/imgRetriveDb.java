

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayImage
 */


import java.sql.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class imgRetriveDb extends  HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse
  response) throws ServletException, IOException{
  PrintWriter pw = response.getWriter();
  String connectionURL = "jdbc:mysql://localhost:3306/studentinfo";
  java.sql.Connection con=null;
  try{
  Class.forName("com.mysql.jdbc.Driver").newInstance();
  con=DriverManager.getConnection(connectionURL,"root","manishreddy");
  Statement st1=con.createStatement();
  // Here use the req.getAttribute method and select the desired image from the database!!
  String imgId=request.getParameter("phone");

  ResultSet rs1 = st1.executeQuery("select Pic from event where Id="+imgId);
  String imgLen="";
  if(rs1.next()){
  imgLen = rs1.getString(1);
  pw.println(imgLen.length());
  }
  rs1 = st1.executeQuery
  ("select Pic from event where Id="+imgId);
  if(rs1.next()){
  int len = imgLen.length();
  byte [] rb = new byte[len];
  InputStream readImg = rs1.getBinaryStream(1);
  int index=readImg.read(rb, 0, len);
  pw.println("index"+index);
  st1.close();
 response.reset();
  response.setContentType("text/html");
response.getOutputStream().write(rb,0,len);
  //response.getOutputStream().flush();
  }
  }
  catch (Exception e){
  e.printStackTrace();
  }
  }
}
