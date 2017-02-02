package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/rapport.htm")
public class RapportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/rapport.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
//	HttpSession session = request.getSession(false);
//	if (session != null) {
//	    boolean gelukt = (boolean) session.getAttribute("gelukt");
//	    request.setAttribute("gelukt", gelukt);
//	}
	
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
