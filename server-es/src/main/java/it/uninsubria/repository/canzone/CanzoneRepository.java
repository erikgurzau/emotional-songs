package it.uninsubria.repository.canzone;


import it.uninsubria.assembler.canzone.CanzoneAssembler;
import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.config.DatabaseConfig;
import it.uninsubria.entity.canzone.CanzoneEntity;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.repository.Repository;
import it.uninsubria.service.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CanzoneRepository extends Repository<CanzoneEntity> {

    private static final String SELECT_SONG_BY_ID = "SELECT * FROM Canzoni WHERE id = ?";
    private static final String SELECT_ALL = "SELECT c.id, c.autore, c.titolo, c.anno, gm.id as genere_musicale_id, gm.nome as genere_musicale_nome, c.durata_ms" +
            "   FROM Canzoni c " +
            "   INNER JOIN Generi_Musicali gm ON c.id_genere = gm.id" +
            "   LIMIT 50";

    public CanzoneRepository() {

    }

    @Override
    public List<CanzoneEntity> findAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
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

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_SONG_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, CanzoneEntity.class).stream().findFirst();
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public CanzoneEntity save(CanzoneEntity canzoneEntity) {
       return null;
    }
}
