

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LanguageServlet
 */
@WebServlet("/LanguageServlet")
public class LanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LanguageServlet() {
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
		String newLanguage=request.getParameter("s1").toLowerCase();
		String newColor=request.getParameter("s2").toLowerCase();
		
		
		
			for(Cookie cookie:cookies)
			{
				if(cookie.getName().equals("language"))
				{
					if(cookie.getValue().equalsIgnoreCase(newLanguage))
					{
						switch(newLanguage.toLowerCase())
						{
						case "english":out.print("Cookie Allready Present for Language " + newLanguage);break;
						case "arabic":out.print("Cookie mojoud haza browser for " + newLanguage);break;
						case "hindi":out.print(newLanguage+ "basha ki Cookie Pehle se hi sangrahit he ");break;
						}
						return;
					}
					else
					{
						cookie.setValue(newLanguage);
						out.print("Cookie Updated to Language " +newLanguage);
						response.addCookie(cookie);
						return;
					}
				}
				
				if(cookie.getName().equals("color"))
				{
					out.print("<body bgcolor='"+ cookie.getValue() +"'>");
					cookie.setValue(newColor);
					response.addCookie(cookie);
					return;
				}
			}
			
			Cookie cook1=new Cookie("language",newLanguage);
			out.print("Cookie Set for Language " +newLanguage);
			response.addCookie(cook1);
			
			Cookie cook2=new Cookie("color",newColor);
			response.addCookie(cook2);
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
