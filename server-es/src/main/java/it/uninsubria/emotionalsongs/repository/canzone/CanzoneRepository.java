package it.uninsubria.emotionalsongs.repository.canzone;


import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.service.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CanzoneRepository extends Repository<CanzoneEntity> {

    public CanzoneRepository() { }

    @Override
    public List<CanzoneEntity> findAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT c.id, c.autore, c.titolo, c.anno, c.durata_ms, " +
                "   gm.id as id_genere_musicale, gm.nome as nome_genere_musicale " +
                "   FROM Canzoni c INNER JOIN Generi_Musicali gm ON c.id_genere = gm.id" +
                "   LIMIT 50";
        LoggerService.info(this.getClass().getSimpleName() + ": findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, CanzoneEntity.class);
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }

    @Override
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
        LoggerService.info(this.getClass().getSimpleName() + ": findById " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, CanzoneEntity.class).stream().findFirst();
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return Optional.empty();
        }
    }

}
