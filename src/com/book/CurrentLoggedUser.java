package com.book;

/*Displays the users who are logged in currently.*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CurrentLoggedUser
 */
@WebServlet("/CurrentLoggedUser")
public class CurrentLoggedUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentLoggedUser() {
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
		PrintWriter out=response.getWriter();;
		HttpSession session=request.getSession(false);
		
		@SuppressWarnings("unchecked")
		HashMap<String, Date> map=(HashMap<String, Date>) session.getAttribute("map");
		
		Iterator<Map.Entry<String, Date>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<String, Date> entry = entries.next();
		    out.println("Name = " + entry.getKey() + ", Login Time = " + entry.getValue());
	}

}
}
