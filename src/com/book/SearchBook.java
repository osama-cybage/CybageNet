package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/* Searches the required book based on the book title and returns the details of the book
  along with the option of viewing the reviews and submitting the user's own review*/

/**
 * Servlet implementation class SearchBook
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBook() {
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
		Statement pst;
		ResultSet rs;
		PrintWriter out=response.getWriter();
		HttpSession ses=request.getSession();
		
		String name=request.getParameter("bname");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","root");
			pst=con.createStatement();
			rs=pst.executeQuery("select * from books where title='"+name+"'");
			String uname=(String) ses.getAttribute("username");
			
				out.println("<html><body><form action='ActionServ' method='post'>");
				out.println("<table border=2><tr><td>ID</td><td>Title</td><td>Pages</td><td>Author</td><td>Edition</td>");
				while(rs.next())
				{
				out.println("<tr><td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getInt(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getInt(5)+"</td></tr></table>");
				
				ses.setAttribute("book_id", rs.getInt(1));
				ses.setAttribute("username", uname);
				}				
				out.print("<input type = 'submit' name='b1' value='Show_Review'>");
				
				out.println("<input type = 'submit' name='b1' value='Add_Review'>");
				
				out.print("<br><br>");
		//out.print("<a href="+"'review.html'"+">"+"Add Review"+"</a>");
		
		out.print("<a href='UserHome'>"+"Back To Home"+"</a>");
		out.println("</html></body></form>");
}
		
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
