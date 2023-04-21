package com.cubic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteData")
public class DeleteSignupServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Reading data from query parameter
		String uname=req.getParameter("username");

		try {
			//Step-1 loading the driver
			Class.forName("com.mysql.jdbc.Driver");

			//Step-2 Creating connection to database
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db","root","rootroot");
			String sql="delete from signups_tbl where username = ?";

			//Step-3 compiling query
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,uname);

			//Executing this query
			pstmt.executeUpdate(); //this executes the sql statement

		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("showSignups").forward(req, resp);

	}


}
