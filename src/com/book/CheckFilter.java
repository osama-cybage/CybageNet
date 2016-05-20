package com.book;

/*This servlet takes the input from the login page and authenticates the data,and accordingly
  distinguishes between user and admin and forwards the request to the corresponding page.*/

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckFilter
 */
@WebFilter("/CheckFilter")
public class CheckFilter implements Filter {
	HashMap<String, Date> map;
    /**
     * Default constructor. 
     */
    public CheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
HttpServletRequest req=(HttpServletRequest) request;
HttpServletResponse res=(HttpServletResponse) response;
HttpSession ses=req.getSession();
Connection con;
Statement st;
ResultSet rs;
RequestDispatcher rd;
String name=req.getParameter("uname");
String pass=req.getParameter("upass");


try {
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","root");
	if(map ==null)
	{
	 map=new HashMap<String, Date>();
	}
	Date d=new Date(ses.getCreationTime());
	st=con.createStatement();
	rs=st.executeQuery("select * from users where name='"+name+"'and password='"+pass+"'");
	
	if(rs.next())
	 {
	
		String type=rs.getString(3);
		
		if(type.equals("admin"))
		{
			map.put(name, d);
			ses.setAttribute("map", map);
			rd=req.getRequestDispatcher("AdminHome");
			rd.include(request, response);
		}
		else
		{
			map.put(name, d);
			ses.setAttribute("map", map);
			rd=req.getRequestDispatcher("UserHome");
			rd.include(request, response);
		}
	
	 }
	 else
	 {
		 rd=req.getRequestDispatcher("Relogin.html");
		 rd.include(request, response);
	 }
	
	
	ses.setAttribute("username", name);
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
