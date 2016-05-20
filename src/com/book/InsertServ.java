package com.book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/*This page is or the administrator which functions for inserting new books in the database*/

/**
 * Servlet implementation class InsertServ
 */
@WebServlet("/InsertServ")
public class InsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServ() {
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
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		RequestDispatcher rd;
		
		int id=Integer.parseInt(request.getParameter("bid"));
		String title=request.getParameter("bname");
		int page=Integer.parseInt(request.getParameter("bpage"));
		String author=request.getParameter("bauthor");
		int edition=Integer.parseInt(request.getParameter("bedition"));

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","root");
			pst=con.prepareStatement("insert into books values(?,?,?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, title);
			pst.setInt(3, page);
			pst.setString(4, author);
			pst.setInt(5, edition);
			int i=pst.executeUpdate();
			if(i>0)
			{
			response.sendRedirect("InsertSuccess.html");
			}
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			catch (MySQLIntegrityConstraintViolationException e)
		{
				response.sendRedirect("BookReInsert.html");
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
