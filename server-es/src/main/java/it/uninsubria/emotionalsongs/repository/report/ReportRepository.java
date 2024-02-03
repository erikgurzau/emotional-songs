package it.uninsubria.emotionalsongs.repository.report;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.*;
import java.util.*;

/**
 * Questa classe è responsabile dell'interazione con i dati nel database
 * fornendo metodi relativi ai report emozionali delle canzoni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.DatabaseConfig
 * @see it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity
 * @see it.uninsubria.emotionalsongs.entity.report.ReportEntity
 * @see it.uninsubria.emotionalsongs.repository.Repository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class ReportRepository extends Repository<CanzoneEntity> {

    /**
     * Il costruttore della classe
     */
    public ReportRepository() { }

    /**
     * Recupera le informazioni del report emozionald relativo alla canzone specificata come argomento.
     * @param idCanzone L'ID della canzone di cui ottenere il report
     * @return Un'entità di tipo report.
     */
    public ReportEntity getReport(Integer idCanzone) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setIdCanzone(idCanzone);

        try {
            Connection connection = DatabaseConfig.getConnection();

            //QUERY CHE PRELEVA LE ULTIME 5 NOTE INSERITE
            String query = "SELECT id_stato_emozionale, COUNT(DISTINCT id_utente), AVG(intensita) AS media_punteggio, " +
                    "ARRAY_REMOVE(ARRAY_AGG(nota), NULL) AS ultime_5_note " +
                    //"ARRAY_AGG(nota) AS note " +
                    "FROM ( " +
                    "    SELECT e.id_stato_emozionale, p.id_utente, e.intensita, e.nota, " +
                    "    ROW_NUMBER() OVER (PARTITION BY e.id_stato_emozionale ORDER BY e.id DESC) AS rnum " +
                    "    FROM emozioni e " +
                    "    INNER JOIN assegnazioni_canzoni ac ON e.id_assegnazione = ac.id " +
                    "    INNER JOIN playlists p ON ac.id_playlist = p.id " +
                    "    WHERE ac.id_canzone = ? " +
                    ") AS subquery " +
                    "WHERE rnum <= 5 " +
                    "GROUP BY id_stato_emozionale";

            //QUERY CHE PRELEVA TUTTE LE NOTE INSERITE
            /*String query= "SELECT e.id_stato_emozionale, COUNT(DISTINCT p.id_utente), " +
                    "AVG(e.intensita) AS media_punteggio, " +
                    "ARRAY_AGG(e.nota) AS note " +
                    "FROM emozioni e " +
                    "INNER JOIN assegnazioni_canzoni ac ON e.id_assegnazione = ac.id " +
                    "INNER JOIN playlists p ON ac.id_playlist = p.id " +
                    "WHERE ac.id_canzone = ? " +
                    "GROUP BY e.id_stato_emozionale";
            */

            PreparedStatement statement = connection.prepareStatement(query);
            Logger.info("ReportRepository : getReport " + query);
            statement.setInt(1, idCanzone);
            ResultSet resultSet = statement.executeQuery();

            List<ReportEntity.EmotionalState> emotionalStates = new ArrayList<>();

            while (resultSet.next()) {
                ReportEntity.EmotionalState stateData = new ReportEntity.EmotionalState();
                int idStatoEmozionale = resultSet.getInt("id_stato_emozionale");
                int numUtenti = resultSet.getInt("count");
                double mediaPunteggio = resultSet.getDouble("media_punteggio");
                Array noteArray = resultSet.getArray("ultime_5_note");
                String[] ultime5Note = (String[]) noteArray.getArray();

                List<String> filtroNoteNull = new ArrayList<>();
                for (String note : ultime5Note) {
                    if (note != null && !note.isEmpty()) {
                        filtroNoteNull.add(note);
                    }
                }

                stateData.setIdStatoEmozionale(idStatoEmozionale);
                stateData.setNumUtenti(numUtenti);
                stateData.setMediaPunteggio(mediaPunteggio);
                stateData.setNote(filtroNoteNull);
                //stateData.setNote(List.of(note));

                emotionalStates.add(stateData);
            }

            reportEntity.setStatiEmozionali(emotionalStates);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportEntity;
    }

    //public ReportEntity getReport() { return new ReportEntity(); }

    /*public ReportEntity getReport() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT COUNT";
        //String query = "SELECT p.id, ur.id as id_utente, p.nome FROM playlists p INNER JOIN utenti_registrati ur ON p.id_utente = ur.id";
        Logger.info(this.getClass().getSimpleName() + ": findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, ReportEntity.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    */

    /*
    public List<PlaylistEntity> getAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT id, id_utente, nome FROM playlists";
        String query = "SELECT p.id, ur.id as id_utente, p.nome " +
                "FROM playlists p INNER JOIN utenti_registrati ur ON p.id_utente = ur.id";
        Logger.info(this.getClass().getSimpleName() + ": findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, PlaylistEntity.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/

}