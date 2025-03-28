

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ShowAllEmployeesServlet
 */
@WebServlet("/ShowAllEmployeesServlet")
public class ShowAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllEmployeesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try
		{
			Connection con=MySQLConnectionITP.getConnection();
			//display name and salary of eno 8
			
			String query="select * from emp";
			//String query="select sal,ename from emp where eno=8";
			
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery(query);
			out.print("<head>\r\n"
					+ "	<meta charset=\"ISO-8859-1\">\r\n"
					+ "	<title>Insert title here</title>\r\n"
					+ "	<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"\r\n"
					+ "		integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n"
					+ "</head>"
					+ "<script>function verify(){return confirm('do you want to delete?')}</script>"
					+ "<body>");
			
			out.print("<a href='emp-registration-form.html' class='btn btn-primary mb-3'>ADD EMPLOYEE</a>");
			out.print("<table class=\"table\">\r\n"
					+ "		  <thead class=\"thead-dark\">\r\n"
					+ "		    <tr>\r\n"
					+ "		      <th scope=\"col\">Employee No</th>\r\n"
					+ "		      <th scope=\"col\">Employee Name</th>\r\n"
					+ "		      <th scope=\"col\">Employee Salary</th>\r\n"
					+ "		      <th scope=\"col\">Delete</th>\r\n"
					+ "		    </tr>\r\n"
					+ "		  </thead><tbody>");
			while(rs.next())
			{
				System.out.println(rs.getString("ename") + rs.getDouble("sal"));
				out.print("<tr>\r\n"
						+ "		      <td>" + rs.getInt("eno") + "</td>\r\n"
						+ "		      <td>" + rs.getString("ename") + "</td>\r\n"
						+ "		      <td>" + rs.getDouble("sal") + "</td>\r\n"
						+ "		      <td><a onClick='return vefify()'class='btn btn-danger' href='delemp?eno=" + rs.getInt("eno") + "'>Delete</a></td>\r\n"
						+ "		    </tr>");
			}
			
			out.print(" </tbody>\r\n"
					+ "		</table></body></html>");
			st.close();
			con.close();
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
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
