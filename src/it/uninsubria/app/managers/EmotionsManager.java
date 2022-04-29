package it.uninsubria.app.managers;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.managers.utils.FileManager;

import java.util.StringTokenizer;
import java.util.Vector;

public class EmotionsManager {
    private String pathFile;
    private Vector<Emotion> listEmotions;
    private FileManager fm;

    public EmotionsManager(String pathFile) {
        this.pathFile = pathFile;
        fm = new FileManager(pathFile);
        loadData();
    }

    public void loadData() {
        listEmotions = loadData(fm.getContent());
    }

    private Vector<Emotion> loadData(Vector<String> dbEmotions) {
        Vector<Emotion> list = new Vector<>();
        for (String row: dbEmotions) {
            StringTokenizer st = new StringTokenizer(row, ";");
            while (st.hasMoreTokens()){
                String name = st.nextToken();
                String explanation = st.nextToken();
                list.add(new Emotion(name, explanation));
            }
        }
        return list;
    }

    public Vector<Emotion> getListEmotions() {
        return listEmotions;
    }

    public Vector<Song> getListEmotions(int idxFrom, int idxTo){
        return new Vector(listEmotions.subList(idxFrom, idxTo));
    }
}
