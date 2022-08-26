package it.uninsubria.app.emotionalsongs;

public class Feedback {
    private String namePlaylist;
    private int userId;
    private int songId;
    private int emotionId;
    private int score; // da 1 a 5
    private String note;

    public Feedback(String namePlaylist, int userId, int songId, int emotionId, int score, String note) {
        this.namePlaylist = namePlaylist;
        this.userId = userId;
        this.songId = songId;
        this.emotionId = emotionId;
        this.score = score;
        this.note = note;
    }
    
    public Feedback (String namePlaylist, int userId, int songId, int emotionId, int score) {
        this.namePlaylist = namePlaylist;
        this.userId = userId;
        this.songId = songId;
        this.emotionId = emotionId;
        this.score = score;
        this.note = "";
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public int getUserId() {
        return userId;
    }

    public int getSongId() {
        return songId;
    }

    public int getEmotionId() {
        return emotionId;
    }

    public int getScore() {
        return score;
    }

    public String getNote() {
        return note;
    }

    public String toString() {
        return songId + "," +
                emotionId + "," +
                score +
                (note.isEmpty() ? ";" : "," + note + ";" );
    }
}
