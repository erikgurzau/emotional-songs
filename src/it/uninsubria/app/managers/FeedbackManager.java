package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Feedback;
import it.uninsubria.app.managers.utils.FileManager;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class FeedbackManager {

    /**
     * Percorso del file delle emozioni provate dagli utenti
     */
    private String pathFileEmotions = "./data/Emozioni.txt";

    /**
     * Mappa con chiave un codice, composto dal nome della playlist e da l'ID utente (es. "Playlist-1")
     * e come valore una lista di recensioni dell'utente
     */
    private HashMap<String, Vector<Feedback>> mapFeedback;


    private HashMap<Integer, Vector<Feedback>> mapGroubBySong;
    private HashMap<Integer, Vector<Feedback>> mapGroubByEmotion;

    /**
     * Gestore I/O del file delle emozioni provate dagli utenti
     */
    private FileManager fm;

    /**
     * Costruttore
     */
    public FeedbackManager() {
        mapFeedback = new HashMap<>();
        fm = new FileManager(pathFileEmotions);
        loadData();
    }


    public void loadData() {
        parseData(fm.getContent());
    }

    private void parseData(Vector<String> rowsFile) {

        for (String row : rowsFile) {
            Vector<Feedback> listFeedback = new Vector<>();
            StringTokenizer st = new StringTokenizer(row, ";");
            String namePlaylist = st.nextToken();
            int userId = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                StringTokenizer feedbackTkn = new StringTokenizer(st.nextToken(), ",");
                int songId = Integer.parseInt(feedbackTkn.nextToken());
                int emotionId = Integer.parseInt(feedbackTkn.nextToken());
                int score = Integer.parseInt(feedbackTkn.nextToken());
                String note;
                if (feedbackTkn.hasMoreTokens())
                    note = feedbackTkn.nextToken();
                else note = "";
                
                listFeedback.add(new Feedback(namePlaylist, userId, songId, emotionId, score, note));
            }

            mapFeedback.put(namePlaylist + "-" + userId, listFeedback);

        }
    }

    public boolean saveFeedback(Vector<Feedback> listFeedback) {
        if (listFeedback.isEmpty()) return false;
        String s = listFeedback.get(0).getNamePlaylist() + ";" + listFeedback.get(0).getUserId() + ";";
        for (Feedback f : listFeedback) {
            s += f.toString();
        }
        return fm.println(s, 'a');
    }

    public int countFeedback(int songId, int emotionId) {
        int count = 0;
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            for (Feedback f : list) {
                if (f.getSongId() == songId && f.getEmotionId() == emotionId)
                    count++;
            }
        }
        return count;
    }

    public int totScoreFeedback(int songId, int emotionId) {
        int sum = 0;
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            for (Feedback f : list) {
                if (f.getSongId() == songId && f.getEmotionId() == emotionId)
                    sum += f.getScore();
            }
        }
        return sum;
    }
    
    public Vector<String> listNotes (int songId, int emotionId) {
        Vector<String> notes = new Vector<>();
        for (Map.Entry<String, Vector<Feedback>> entry : mapFeedback.entrySet()) {
            Vector<Feedback> list = entry.getValue();
            for (Feedback f : list) {
                if (f.getSongId() == songId && f.getEmotionId() == emotionId)
                    notes.add(f.getNote());
            }
        }
        return notes;
    }

}
