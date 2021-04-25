package com.labcerebrone.Backend.Microservice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        String FirstName = request.getParameter("fname");
        String LastName = request.getParameter("lname");
        String EmailID = request.getParameter("email");
        String PhoneNo = request.getParameter("phone");
        String Message = request.getParameter("message");

        response.setContentType("text/html");
        PrintWriter write = response.getWriter();
        write.println("<html><head><title>GLAD TO SEE YOU !</title></head></html");
        write.println("<p>Will Reach Out To You Soon,Have A Great day Ahead</p>");
        write.println("<p>Your Name is:  " + FirstName + LastName + "/n" +"Your MailId is :   " + EmailID + "/n" + "Your Contact Number is:  " + PhoneNo +
                "/n" + " And Your Mesage is /n " + Message + "</p>");
        write.println("</body></html>");
    }
}