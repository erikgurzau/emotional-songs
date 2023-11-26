package it.uninsubria.emotionalsongs.repository.assegnazioni_canzoni;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity;
import it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.*;
import java.util.*;

public class AssegnCanzRepository extends Repository<AssegnCanzEntity> {
    public AssegnCanzRepository() { }

    public boolean insertAssegnazione(AssegnCanzone assegnazione) {
        Connection connection;
        PreparedStatement statement;
        Map<String, Object> mapParams = new HashMap<>();
        boolean res = false;

        List<Integer> canzoni = assegnazione.getIdCanzone();
        for (Integer canzone: canzoni) {
            String query = "INSERT INTO assegnazioni_canzoni (id_playlist, id_canzone) VALUES (:id_playlist, :id_canzone)";
            mapParams.put("id_playlist", assegnazione.getIdPlaylist());
            mapParams.put("id_canzone", canzone);

            query = replaceNamedParams(query, mapParams);
            Logger.info("AssegnCanzRepository: insertAssegnazione " + query);
            try {
                connection = DatabaseConfig.getConnection();
                statement = connection.prepareStatement(query);
                statement.executeUpdate();
                res = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
                res = false;
            }
        }
        return res;
    }
}
