package com.book;

/*This servlet performs the functioning of adding a review given by the user to the database
which can be seen by other users also*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReviewSucc
 */
@WebServlet("/ReviewSucc")
public class ReviewSucc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewSucc() {
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
		Connection con;
		PreparedStatement pst;
		
		String rev=request.getParameter("review");
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","root");
		HttpSession session=request.getSession();
		String uname=(String) session.getAttribute("username");
		int book_id=(int) session.getAttribute("bid");
		String review=request.getParameter("review");
		
		
		pst=con.prepareStatement("insert into review values (?,?,?) ");
		pst.setInt(1, book_id);
		pst.setString(2, uname);
		pst.setString(3, review);
		pst.executeUpdate();
		out.print("Your Review have been Recorded");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String String(Object attribute) {
		// TODO Auto-generated method stub
		return null;
	}

}
