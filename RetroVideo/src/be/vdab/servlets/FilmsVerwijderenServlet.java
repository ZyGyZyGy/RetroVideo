package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.repositories.FilmRepository;

@WebServlet("/films/verwijderen.htm")
public class FilmsVerwijderenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
    private static final String REDIRECT_URL = "%s/films/reserveren.htm";
    private final FilmRepository filmRepository = new FilmRepository();
    private static final String MANDJE = "mandje";

    @Resource(name = FilmRepository.JNDI_NAME) 
    public void setDataSource(DataSource dataSource) { 
	filmRepository.setDataSource(dataSource); 
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession();
	if (request.getParameterValues("id") != null) {
	    @SuppressWarnings("unchecked")
	    Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
	    if (mandje != null) {
		Set<Long> films = Arrays.stream(request.getParameterValues("id"))
			.map(id -> Long.parseLong(id))
			.collect(Collectors.toSet());
		mandje.removeAll(films);
		session.setAttribute(MANDJE, mandje);
	    }
	    response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
	} else {
	    @SuppressWarnings("unchecked")
	    Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
	    request.setAttribute("filmsInMandje", mandje.stream()
			.map(id -> filmRepository.findFilmById(id))
			.filter(optionalId -> optionalId.isPresent())
			.map(optionalId -> optionalId.get())
			.collect(Collectors.toList())
			);
	    request.setAttribute("totaal",
			mandje.stream()
			.map(id -> filmRepository.findFilmById(id))
			.filter(optionalId -> optionalId.isPresent())
			.map(optionalId -> optionalId.get())
			.map(film -> film.getPrijs())
			.reduce(BigDecimal.ZERO, (vorigeSom, prijs) -> vorigeSom.add(prijs))
			);
	    request.setAttribute("verwijderFout", "niets geselecteerd");
	    request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
    }

}
