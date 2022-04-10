package it.uninsubria.app.emotionalsongs;

public class Emotion {
    private String name, explanation;

    public Emotion(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }


}
