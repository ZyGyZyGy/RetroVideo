package be.vdab.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Film;
import be.vdab.entities.Genre;
import be.vdab.repositories.FilmRepository;
import be.vdab.repositories.GenreRepository;;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/films.jsp";
    private final GenreRepository genreRepository = new GenreRepository();
    private final FilmRepository filmRepository = new FilmRepository();

    @Resource(name = FilmRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	genreRepository.setDataSource(dataSource);
	filmRepository.setDataSource(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	if (request.getParameter("genreid") != null) {
	    long id = Long.parseLong(request.getParameter("genreid"));
	    List<Film> films = filmRepository.findByGenreId(id);
	    request.setAttribute("films", films);
	    request.setAttribute("geselecteeredeGenreId", id);
	}
	Set<Genre> genres = genreRepository.findAll();
	request.setAttribute("genres", genres);
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
