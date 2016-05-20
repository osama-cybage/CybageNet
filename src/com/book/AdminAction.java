package com.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Performs the function of distinguishing the option chosen by the administrator
  and redirecting to the corresponding page*/
/**
 * Servlet implementation class AdminAction
 */
@WebServlet("/AdminAction")
public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAction() {
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
		
		String choice=request.getParameter("b1");
		if(choice.equals("Insert"))
		{
			response.sendRedirect("BookInsert.html");;
		}
		else if(choice.equals("Delete"))
		{
			response.sendRedirect("BookDelete.html");;
		}
		else if(choice.equals("Show"))
		{
			response.sendRedirect("BookShow");;
		}
		else if(choice.equals("Show UserLog"))
		{
			response.sendRedirect("ShowUserLog");;
		}
		else if(choice.equals("Current Logged User"))
		{
			response.sendRedirect("CurrentLoggedUser");;
		}
	}

}
