package com.book;

/*Displays the information of the login ime of the users to the admin*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowUserLog
 */
@WebServlet("/ShowUserLog")
public class ShowUserLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		HashMap<String, Date> map=(HashMap<String, Date>) session.getAttribute("map");
		

		Iterator<Map.Entry<String, Date>> entries = map.entrySet().iterator();
		while (entries.hasNext()) 
		{
		    Map.Entry<String, Date> entry = entries.next();
		    out.println("Name = " + entry.getKey() + ", Login Time = " + entry.getValue());
		}
		    int hitnumber=0;
		
		hitnumber=hitnumber+1;
		String hitnumber1=Integer.toString(hitnumber);
		Cookie cookie=new Cookie("Hit",hitnumber1);
		Date d=new Date();
		cookie.setMaxAge((int)d.getTime()+ (24 * 60 * 60));
		response.addCookie(cookie);
		out.println("total hit :"+hitnumber);
		
	

}
}
