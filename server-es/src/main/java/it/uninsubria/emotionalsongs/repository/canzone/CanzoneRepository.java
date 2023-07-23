package it.uninsubria.emotionalsongs.repository.canzone;


import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static it.uninsubria.emotionalsongs.utils.Utils.isNull;

public class CanzoneRepository extends Repository<CanzoneEntity> {

    public CanzoneRepository() { }

    public List<CanzoneEntity> getAll(Integer numeroPagina, Integer dimensionePagina) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM Canzoni c INNER JOIN Generi_Musicali gm ON c.id_genere = gm.id" +
                "   LIMIT " + dimensionePagina + " OFFSET " + numeroPagina;;
        Logger.info(this.getClass().getSimpleName() + ": findAll " + query);
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

    public Optional<CanzoneEntity> findById(Integer id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM Canzoni c INNER JOIN Generi_Musicali gm ON c.id_genere = gm.id" +
                "   WHERE c.id = :id";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("id", id);
        query = replaceNamedParams(query, mapParams);
        Logger.info(this.getClass().getSimpleName() + ": findById " + query);
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

    public Integer getTotaleCanzoni() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT COUNT(*) FROM Canzoni c";
        Logger.info(this.getClass().getSimpleName() + ": getTotaleCanzoni " + query);
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
