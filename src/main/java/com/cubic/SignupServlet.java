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
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	/**
	 *  		<form action="signup">
  		     <label>Username :</label> 
  		     <input type="text" name="username"  class="form-control">
  		      <label>Password :</label> 
  		     <input type="text" name="password"  class="form-control">
  		      <label>Email :</label> 
  		     <input type="email" name="email"  class="form-control">
  		      <label>Gender :</label> 
  		     <select  name="gender"  class="form-control">
  		           <option>Male</option>
  		           <option>Female</option>
  		     </select>
  		     <br/>
  		     <button type="submit"  class="btn btn-primary">Signup</button>
  		     </form>
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uname=req.getParameter("username"); //this "username" comes from 'input name' tag in signup.html
		String ppass=req.getParameter("password");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");

		try {

			//Step-1 loading the driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Step-2 Creating connection to database
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db","root","rootroot");

			//THIS CODE IS USED TO PUSH DATA INSIDE TABLE
			String sql="insert into signups_tbl(username,password, email,gender) values(?,?,?,?)";
			
			//Step-3 compiling query
			PreparedStatement pstmt=conn.prepareStatement(sql);
			//Step-4 - Setting the values in the query
			pstmt.setString(1, uname);
			pstmt.setString(2, ppass);
			pstmt.setString(3, email);
			pstmt.setString(4, gender);
			//Step-5 Fire the query
			pstmt.executeUpdate();

			//WRITE CODE TO FETCH ALL THE DATA FROM DATABASE
			String fecthData="select username, password , email,gender from signups_tbl";
			//Executing this query
			pstmt=conn.prepareStatement(fecthData);
			ResultSet rs=pstmt.executeQuery();

			//CLEARNING ALL THE DATA FROM THE LIST
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

		System.out.println("Username  = "+uname);
		System.out.println("Password  = "+ppass);
		System.out.println("Email  = "+email);
		System.out.println("Gender  = "+gender);

		req.getRequestDispatcher("signup.jsp").forward(req, resp);
	}

}
