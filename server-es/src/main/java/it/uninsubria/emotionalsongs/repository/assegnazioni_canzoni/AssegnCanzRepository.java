package it.uninsubria.emotionalsongs.repository.assegnazioni_canzoni;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity;
import it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.*;
import java.util.*;

/**
 * Questa classe è responsabile dell'interazione con i dati nel database
 * fornendo metodi relativi alle assegnazioni di canzoni ad una playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.DatabaseConfig
 * @see it.uninsubria.emotionalsongs.entity.assegnazioni_canzoni.AssegnCanzEntity
 * @see it.uninsubria.emotionalsongs.model.assegnazioni_canzoni.AssegnCanzone
 * @see it.uninsubria.emotionalsongs.repository.Repository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class AssegnCanzRepository extends Repository<AssegnCanzEntity> {

    /**
     * Costruttore della classe.
     */
    public AssegnCanzRepository() { }

    /**
     * Inserisce una nuova assegnazione ad una playlist.
     * @param assegnazione L'assegnazione da inserire
     * @return {@code true} se l'inserimento avviene con successo, {@code false} altrimenti
     */
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