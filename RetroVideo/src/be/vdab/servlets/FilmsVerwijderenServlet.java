package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.FilmRepository;

@WebServlet("/films/verwijderen.htm")
public class FilmsVerwijderenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
    private final FilmRepository filmRepository = new FilmRepository();

    @Resource(name = FilmRepository.JNDI_NAME) 
    public void setDataSource(DataSource dataSource) { 
	filmRepository.setDataSource(dataSource); 
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	if (request.getParameterValues("id") != null) {
	    
	}
    }

}
