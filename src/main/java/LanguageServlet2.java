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
@WebServlet("/LanguageServlet2")
public class LanguageServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LanguageServlet2() {
        super();
    }

    /**
     * Handles the GET request.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get parameters from the request
        String newLanguage = request.getParameter("s1");
        String newColor = request.getParameter("s2");

        // Validate parameters
        if (newLanguage == null || newColor == null) {
            out.print("Invalid language or color parameters.");
            return;
        }

        // Normalize inputs
        newLanguage = newLanguage.trim().toLowerCase();
        newColor = newColor.trim().toLowerCase();

        // Check for existing cookies
        Cookie languageCookie = getCookieByName(request, "language");
        Cookie colorCookie = getCookieByName(request, "color");

        // Update or set language cookie
        if (languageCookie != null) {
            handleLanguageCookie(out, languageCookie, newLanguage);
        } else {
            setNewCookie(response, "language", newLanguage);
            out.print("Cookie set for language: " + newLanguage);
        }

        // Update or set color cookie
        if (colorCookie != null) {
            handleColorCookie(response, colorCookie, newColor);
        } else {
            setNewCookie(response, "color", newColor);
        }
        
        out.print("<body bgcolor='"+ colorCookie.getValue() +"'>");
        
    }

    /**
     * Handles the POST request by forwarding it to the doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Retrieves the cookie by its name.
     */
    private Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * Handles the language cookie update or setting.
     */
    private void handleLanguageCookie(PrintWriter out, Cookie cookie, String newLanguage) {
        String currentLanguage = cookie.getValue();
        if (currentLanguage.equalsIgnoreCase(newLanguage)) {
            out.print("Cookie already present for language: " + newLanguage);
        } else {
            cookie.setValue(newLanguage);
            out.print("Cookie updated to language: " + newLanguage);
        }
    }

    /**
     * Handles the color cookie update or setting.
     */
    private void handleColorCookie(HttpServletResponse response, Cookie cookie, String newColor) {
        cookie.setValue(newColor);
        response.addCookie(cookie);
    }

    /**
     * Sets a new cookie with the specified name and value.
     */
    private void setNewCookie(HttpServletResponse response, String name, String value) {
        Cookie newCookie = new Cookie(name, value);
        newCookie.setMaxAge(60 * 60 * 24 * 365); // Set cookie to expire in 1 year
        response.addCookie(newCookie);
    }
}
