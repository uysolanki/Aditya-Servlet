

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int a=Integer.parseInt(request.getParameter("t1"));
		String b=request.getParameter("t2");
		int c=Integer.parseInt(request.getParameter("t3"));
		try
		{
		Connection con=MySQLConnectionITP.getConnection();
		
		String query="insert into emp values(?,?,?)";
		
		PreparedStatement ps=con.prepareStatement(query);
		
		ps.setInt(1, a);
		ps.setString(2, b);
		ps.setDouble(3, c);
		
		int rows=ps.executeUpdate();					
		if(rows>0)
		{
			//out.print("alert('Registration Success')");
			out.println("<script type='text/javascript'>");
			out.println("<div class='container mt-5'>");
            out.println("<div class='alert alert-success' role='alert'>");
            out.println("alert('Registration Success');");
            out.println("</div>");
            out.println("</div>");
            out.println("</script>");
			RequestDispatcher rd=request.getRequestDispatcher("showemps");
			rd.include(request, response);
		}
		else
		{
			
		}
			
		
		ps.close();
		con.close();
		}
		catch(Exception e1) {
			System.out.println(e1.getMessage());
			out.print("<font color='red'>Not able to add Employee</font>");
			RequestDispatcher rd=request.getRequestDispatcher("/emp-registration-form.html");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
