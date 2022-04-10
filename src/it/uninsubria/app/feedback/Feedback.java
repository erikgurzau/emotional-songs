package it.uninsubria.app.feedback;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;

public class Feedback {
    private User user;
    private Song song;
    private Emotion emotion;
    private int score;
    private String notes;

    public Feedback(User user, Song song, Emotion emotion, int score, String notes) {
        this.user = user;
        this.song = song;
        this.emotion = emotion;
        this.score = score;
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
