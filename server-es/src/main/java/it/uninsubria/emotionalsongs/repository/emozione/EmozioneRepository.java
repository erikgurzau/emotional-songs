package it.uninsubria.emotionalsongs.repository.emozione;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.emozione.EmozioneEntity;
import it.uninsubria.emotionalsongs.model.emozione.Emozione;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe è responsabile dell'interazione con i dati nel database
 * fornendo metodi relativi alle recensioni emozionali delle canzoni di una playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.DatabaseConfig
 * @see it.uninsubria.emotionalsongs.entity.emozione.EmozioneEntity
 * @see it.uninsubria.emotionalsongs.model.emozione.Emozione
 * @see it.uninsubria.emotionalsongs.repository.Repository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class EmozioneRepository extends Repository<EmozioneEntity> {

    /**
     * Il costruttore della classe.
     */
    public EmozioneRepository() { }

    /**
     * Inserisce una nuova recensione emozionale utilizzando i dati dell'oggetto Emozione forniti come argomento.
     * @param emozione I dati per la recensione emozionale
     * @return {@code true} se l'inserimento avviene con successo, {@code false} altrimenti
     */
    public boolean aggiungiEmozione(Emozione emozione) {
        Connection connection;
        PreparedStatement statement;
        String query =
                "INSERT INTO emozioni (id_assegnazione, id_stato_emozionale, intensita, nota) " +
                        "VALUES (:id_assegnazione, :id_stato_emozionale, :intensita, :nota)";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("id_assegnazione", emozione.getIdAssegnazione());
        mapParams.put("id_stato_emozionale", emozione.getIdStatoEmozionale());
        mapParams.put("intensita", emozione.getIntensita());
        mapParams.put("nota", emozione.getNota());

        query = replaceNamedParams(query, mapParams);
        Logger.info(this.getClass().getSimpleName() + ": createRecensione " + query);

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}