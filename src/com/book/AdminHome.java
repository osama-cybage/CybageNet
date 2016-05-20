package com.book;

/*This servlet is the homepage of the admin and displays the functionality of the admin*/

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminHome
 */
@WebServlet("/AdminHome")
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHome() {
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
		HttpSession ses=request.getSession();
		String str=(String) ses.getAttribute("username");
		out.print("<h3><i>Welcome Admin : "+str+"</i></h3>");
out.print("<html><body><center><h2>Admin Home Page</h2><form method='post' action='AdminAction'>");
out.print("<input type='submit' name='b1' value='Show'>&nbsp;&nbsp;");
out.print("<input type='submit' name='b1' value='Insert'>&nbsp;&nbsp;");
out.print("<input type='submit' name='b1' value='Delete'>&nbsp;&nbsp;");
out.print("<input type='submit' name='b1' value='Show UserLog'>&nbsp;&nbsp;");
out.print("<input type='submit' name='b1' value='Current Logged User'>");


out.print("</form></center></body></html>");;
		
	}

}
