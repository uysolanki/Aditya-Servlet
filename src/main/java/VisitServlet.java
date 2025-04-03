

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitServlet
 */
@WebServlet("/VisitServlet")
public class VisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Cookie cookies[]=request.getCookies();
		int visitCounter=1;
		if(cookies==null)
		{
			out.print(visitCounter + "Visit");
			Cookie cookie=new Cookie("visit",visitCounter+"");
			response.addCookie(cookie);
		}
		else
		{
			for(Cookie cookie:cookies)
			{
				if(cookie.getName().equals("visit"))
				{
					int value=Integer.parseInt(cookie.getValue());
					visitCounter=value+1;
					out.print(visitCounter + "Visit");
					cookie.setValue(visitCounter+"");
					cookie.setMaxAge(60*60*24*365);
					response.addCookie(cookie);
				}
			}
		}
		
		ServletContext cont=getServletContext();
		int n1=Integer.parseInt(cont.getInitParameter("n1"));
		System.out.println(n1);
		
		
		ServletConfig conf=getServletConfig();
		int n2=Integer.parseInt(conf.getInitParameter("n2"));
		System.out.println(n2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
