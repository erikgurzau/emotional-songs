package it.uninsubria.app.songs;

public class Song {
    private int id;
    private String title, author, genre;
    private int year;
    private int duration_ms;

    public Song(int id, String title, String author, String genre, int year, int duration_ms) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.duration_ms = duration_ms;
    }

    public int getId() {
        return id;
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

    public int getDuration() {
        return duration_ms;
    }

    public String millisToTime(){
        long second = (duration_ms / 1000) % 60;
        long minute = (duration_ms / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minute, second);
    }

    @Override
    public String toString() {
        return id + ";" + title + ";" + author + ";" + genre + ";" + year + ";" + duration_ms;
    }

}
