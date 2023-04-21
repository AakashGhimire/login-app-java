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

//  / - slash is mandatory
@WebServlet("/showSignups")
public class ShowSignupServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			//Step-1 loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			//Step-2 Creating connection to database
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db","root","rootroot");
			String sql="insert into signups_tbl(username,password, email,gender) values(?,?,?,?)";
			//Step-3 compiling query
			PreparedStatement pstmt=conn.prepareStatement(sql);
			//WRITE CODE TO FETCH ALL THE DATA FROM DATABASE
			String fecthData="select username, password , email,gender from signups_tbl";
			//Executing this query
			pstmt=conn.prepareStatement(fecthData);
			ResultSet rs=pstmt.executeQuery();

			DataStore.getSignupDTOs().clear();
			while(rs.next()) {
				String dusername= rs.getString(1);
				String dpassword= rs.getString(2);
				String demail= rs.getString(3);
				String dgender= rs.getString(4);

				//ONE ROW WE ARE SETTING INSIDE ONE INSTANCE OF SignupDTO 
				SignupDTO dto=new SignupDTO();
				dto.setEmail(demail);
				dto.setGender(dgender);
				dto.setPassword(dpassword);
				dto.setUsername(dusername);

				DataStore.getSignupDTOs().add(dto);
				//JDBC PROGRAMMING HERE TO SAVE DATA INSIDE DATABASE;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("signup.jsp").forward(req, resp);
	}

}
