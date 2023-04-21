package com.cubic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//  / - slash is mandetory
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	
	/**
	 * <form action="auth">
  		     <label>Username :</label> 
  		     <input type="text" name="username"  class="form-control">
  		      <label>Password :</label> 
  		     <input type="text" name="password"  class="form-control">
  		     <br/>
  		     <button type="submit"  class="btn btn-primary">Login</button>
  		     </form>
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String uname=req.getParameter("username");
		String ppass=req.getParameter("password");
		
		if(uname.equals("jack") && ppass.equals("jill")) {
			req.getRequestDispatcher("success.htm").forward(req, resp);
		}else {
			req.getRequestDispatcher("fail.htm").forward(req, resp);
		}
	}
	
}
