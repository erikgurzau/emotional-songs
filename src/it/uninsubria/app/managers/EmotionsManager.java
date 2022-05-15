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
    private String pathFile = "./data/Emozioni.txt";
    private Vector<Emotion> listEmotions;
    private HashMap<Integer, Emotion> mapEmotions;
    private FileManager fm;

    public EmotionsManager() {
        mapEmotions = new HashMap<>();
        fm = new FileManager(pathFile);
        loadData();
    }

    public void loadData() {
        listEmotions = loadData(fm.getContent());
    }

    private Vector<Emotion> loadData(Vector<String> dbEmotions) {
        Vector<Emotion> list = new Vector<>();
        mapEmotions.clear();
        for (String row: dbEmotions) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                String explanation = st.nextToken();
                Emotion emotion = new Emotion(id, name, explanation);
                list.add(emotion);
                mapEmotions.put(id, emotion);
            }
        }
        return list;
    }

    public Emotion getEmotion(int emotionId) {
        return mapEmotions.get(emotionId);
    }

    public Vector<Emotion> getListEmotions() {
        return listEmotions;
    }

}
