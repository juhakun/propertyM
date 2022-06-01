package de.muulti.spring.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbServlet
 */
@WebServlet("/DbServlet")
public class DbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setup connection variables
		Connection connect = null;
		String user = "testStudent";
		String pass = "testPw";

		String jdbcUrl = "jdbc:mysql://localhost:3306/PropertyM?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";

		// get connection
		try {
			PrintWriter out = response.getWriter();

			out.println("Connecting to Database: " + jdbcUrl);
			
			Class.forName(driver);
			connect = DriverManager.getConnection(jdbcUrl, user, pass);
				
			out.println("Success!");
			
			connect.close();
			
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);

		}

	}

}
