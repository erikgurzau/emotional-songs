package it.uninsubria.app.emotionalsongs;

public class Emotion {
    private int id;
    private String category;
    private String explanation;

    public Emotion(int id, String category, String explanation) {
        this.id = id;
        this.category = category;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return "\n" + id + ";" + category + ";" + explanation;
    }
}
