package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.managers.utils.FileManager;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class EmotionsManager {
    /**
     * Percorso del file delle emozioni da scegliere per recensire una canzone
     */
    private String pathFile = "./data/ListaEmozioni.txt";

    /**
     * Lista di emozioni
     */
    private Vector<Emotion> listEmotions;

    /**
     * Gestore I/O del file della lista di emozioni
     */
    private FileManager fm;


    /**
     * Costruttore
     */
    public EmotionsManager() {
        fm = new FileManager(pathFile);
        loadData();
    }

    /**
     * Legge i dati dal file e li converte in una lista di emozioni
     */
    public void loadData() {
        listEmotions = parseData(fm.getContent());
    }

    /**
     * Converte una lista di stringhe, che corrispondono alle righe del file .txt
     * contenenti tutte le emozioni disponibili, in una lista di emozioni.
     * I dati nel file sono salvati con il seguente formato:
     * idEmozione;categoria;descrizione
     * dove ogni riga rappresenta una diversa emozione
     * @param rowsFile Lista di stringhe con le informazione delle emozioni
     * @return Una lista di emozioni
     */
    private Vector<Emotion> parseData(Vector<String> rowsFile) {
        Vector<Emotion> list = new Vector<>();
        for (String row: rowsFile) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                String explanation = st.nextToken();
                Emotion emotion = new Emotion(id, name, explanation);
                list.add(emotion);
            }
        }
        return list;
    }

    public Emotion getEmotion(int emotionId) {
        for (Emotion e : listEmotions)
            if (e.getId() == emotionId)
                return e;
        return null;
    }

    public Vector<Emotion> getListEmotions() {
        return listEmotions;
    }

    public int size() {
        return listEmotions.size();
    }
}
