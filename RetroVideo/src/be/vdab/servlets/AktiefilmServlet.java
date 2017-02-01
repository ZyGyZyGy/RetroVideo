// NIET MEER NODIG: TO BE DELETED
package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Film;
import be.vdab.repositories.FilmRepository;

@WebServlet("/aktiefilm.htm")
public class AktiefilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/films.jsp";
    private final FilmRepository filmRepository = new FilmRepository();
    private static final long GENRE_ID = 1;

    @Resource(name = FilmRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	filmRepository.setDataSource(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Film> films = filmRepository.findByGenreId(GENRE_ID);
	request.setAttribute("films", films);
//	Film film = filmRepository.findFilmById(1);
//	request.setAttribute("film", film);
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
