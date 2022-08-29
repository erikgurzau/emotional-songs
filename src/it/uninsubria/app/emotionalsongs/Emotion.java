package it.uninsubria.app.emotionalsongs;

/**
 * Classe che definisce un'emozione che è possibile provare quando si ascolta
 * una determinata canzone
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 */
public class Emotion {
    /**
     * Intero che identifica l'ID dell'emozione
     */
    private final int id;

    /**
     * Stringa che rappresenta la categoria dell'emozione
     */
    private final String category;

    /**
     * Stringa che rappresenta una breve descrizione dell'emozione
     */
    private final String explanation;

    /**
     * Costruttore di una emozione
     * @param id Intero che identifica l'ID dell'emozione
     * @param category Stringa che rappresenta la categoria dell'emozione
     * @param explanation Stringa che rappresenta una breve descrizione dell'emozione
     */
    public Emotion(int id, String category, String explanation) {
        this.id = id;
        this.category = category;
        this.explanation = explanation;
    }

    /**
     * Getter dell'ID dell'emozione
     * @return Intero che identifica l'ID dell'emozione
     */
    public int getId() {
        return id;
    }

    /**
     * Getter della categoria dell'emozione
     * @return Stringa che rappresenta la categoria dell'emozione
     */
    public String getCategory() {
        return category;
    }

    /**
     * Getter della descrizione dell'emozione
     * @return Stringa che rappresenta una breve descrizione dell'emozione
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Ritorna una stringa che contiene le informazioni dell'emozione
     * @return String che contiene i dati dell'emozione divisi dal separatore ';'
     */
    public String toString() {
        return "\n" + id + ";" + category + ";" + explanation;
    }
}
