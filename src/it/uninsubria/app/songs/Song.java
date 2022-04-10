package it.uninsubria.app.songs;

public class Song {
    private String title, author, genre;
    private int year;
    private long duration_ms;

    public Song(String title, String author, String genre, int year, long duration_ms) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.duration_ms = duration_ms;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public long getDuration() {
        return duration_ms;
    }

    public String millisToTime(){
        long second = (duration_ms / 1000) % 60;
        long minute = (duration_ms / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minute, second);
    }

    @Override
    public String toString() {
        return title + ";" + author + ";" + genre + ";" + year + ";" + duration_ms;
    }

}
