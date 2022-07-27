package it.uninsubria.app.emotionalsongs;

public class Emotion {
    private int id;
    private String name, explanation;

    public Emotion(int id, String name, String explanation) {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return "\n" + id + ";" + name + ";" + explanation;
    }
}
