package it.uninsubria.emotionalsongs.repository.canzone;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Questa classe è responsabile dell'interazione con i dati nel database
 * fornendo metodi relativi alle canzoni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.DatabaseConfig
 * @see it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity
 * @see it.uninsubria.emotionalsongs.repository.Repository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class CanzoneRepository extends Repository<CanzoneEntity> {

    /**
     * Costruttore della classe.
     */
    public CanzoneRepository() { }

    /**
     * Recupera un elenco paginato di tutte le canzoni presenti nel database.
     * @param numeroPagina Il numero di pagina
     * @param dimensionePagina La dimensione della pagina
     * @return Una lista di entità di tipo canzone
     */
    public List<CanzoneEntity> getAll(Integer numeroPagina, Integer dimensionePagina) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM canzoni c INNER JOIN generi_musicali gm ON c.id_genere = gm.id" +
                "   LIMIT " + dimensionePagina + " OFFSET " + numeroPagina;;
        Logger.info("CanzoneRepository : findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, CanzoneEntity.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce la canzone corrispondente all'ID specificato, se presente.
     * @param id L'ID della canzone da cercare
     * @return Un'entità di tipo canzone
     */
    public Optional<CanzoneEntity> findById(Integer id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM canzoni c INNER JOIN generi_musicali gm ON c.id_genere = gm.id" +
                "   WHERE c.id = :id";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("id", id);
        query = replaceNamedParams(query, mapParams);
        Logger.info("CanzoneRepository : findById " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, CanzoneEntity.class).stream().findFirst();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Restituisce le canzoni che contengono nel titolo la stringa ricercata.
     * @param ricerca La stinga della ricerca
     * @return Una lista di entità di tipo canzone
     */
    public List<CanzoneEntity> findByTitolo(String ricerca) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM canzoni c INNER JOIN generi_musicali gm ON c.id_genere = gm.id" +
                "   WHERE c.titolo LIKE ?";
        Logger.info("CanzoneRepository : findByTitolo " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + ricerca + "%");
            resultSet = statement.executeQuery();

            List<CanzoneEntity> result = resultSetToList(resultSet, CanzoneEntity.class);
            connection.close();
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce le canzoni che possiedono l'autore e l'anno di pubblicazione forniti come argomento.
     * @param autore L'autore della canzone da cercare
     * @param anno L'anno della canzone da cercare
     * @return Una lista di entità di tipo canzone
     */
    public List<CanzoneEntity> findByAutoreAnno(String autore, Integer anno) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        System.out.println("ciao");

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM canzoni c INNER JOIN generi_musicali gm ON c.id_genere = gm.id" +
                "   WHERE c.autore = ? AND c.anno = ?";
        Logger.info("CanzoneRepository : findByAutoreAnno " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, autore);
            statement.setInt(2, anno);
            resultSet = statement.executeQuery();

            List<CanzoneEntity> result = resultSetToList(resultSet, CanzoneEntity.class);
            connection.close();
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce il numero totale di canzoni presenti nel database.
     * @return L'intero che rappresenta il totale delle canzoni
     */
    public Integer getTotaleCanzoni() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT COUNT(*) FROM Canzoni c";
        Logger.info("CanzoneRepository : getTotaleCanzoni " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            int totale = 0;
            if (resultSet.next())
                totale = resultSet.getInt(1);
            return totale;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}