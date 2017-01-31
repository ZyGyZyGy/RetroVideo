package be.vdab.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Genre;

public class GenreRepository extends AbstractRepository {
    
    private static final String FIND_ALL = "select id, naam from genres order by naam";
    
    private static final Logger LOGGER = 
	    Logger.getLogger(FilmRepository.class.getName());
    
    public Set<Genre> findAll() {
	try (Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
	    Set<Genre> genres = new LinkedHashSet<>();
//	    List<Genre> genres = new ArrayList<>();
	    while (resultSet.next()) {
		genres.add(resultSetNaarGenre(resultSet));
	    }
	    return genres;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
    private Genre resultSetNaarGenre(ResultSet resultSet) throws SQLException {
	return new Genre(resultSet.getLong("id"), resultSet.getString("naam"));
    }
}
