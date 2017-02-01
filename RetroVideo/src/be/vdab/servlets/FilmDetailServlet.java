package be.vdab.servlets;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Film;
import be.vdab.repositories.FilmRepository;

@WebServlet("/films/detail.htm")
public class FilmDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/filmdetail.jsp";
    private final FilmRepository filmRepository = new FilmRepository();

    @Resource(name = FilmRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	filmRepository.setDataSource(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	if (request.getParameter("id") != null) {
	    long id = Long.parseLong(request.getParameter("id"));
	    Optional<Film> optionalFilm = filmRepository.findFilmById(id);
	    if (optionalFilm.isPresent()) {
		Film film = optionalFilm.get();
		request.setAttribute("film", film);
	    }
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }
}
