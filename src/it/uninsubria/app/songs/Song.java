package it.uninsubria.app.songs;

/**
 * Classe che definisce una canzone
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class Song {
    /**
     * ID univoco che identifica il brano all'interno del catalogo
     */
    private final int id;

    /**
     * Titolo del brano
     */
    private final String title;

    /**
     * Autore del brano
     */
    private final String author;

    /**
     * Genere musicale del brano
     */
    private final String genre;

    /**
     * Anno di pubblicazione del brano
     */
    private final int year;

    /**
     * Durata del brano in millisecondi
     */
    private final int duration_ms;

    /**
     * Costruttore di una canzone
     * @param id Intero che rappresenta l'ID associato alla canzone
     * @param title Stringa con il titolo della canzone
     * @param author Stringa con l'autore della canzone
     * @param genre Stringa con il genere musicale della canzone
     * @param year Intero che rappresenta l'anno di pubblicazione della canzone
     * @param duration_ms Intero che rappresenta la durata della canzone in millisecondi
     */
    public Song(int id, String title, String author, String genre, int year, int duration_ms) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.duration_ms = duration_ms;
    }

    /**
     * Getter dell'ID della canzone
     * @return Intero che rappresenta l'ID associato alla canzone
     */
    public int getId() {
        return id;
    }

    /**
     * Getter del titolo della canzone
     * @return Stringa con il titolo della canzone
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter dell'autore della canzone
     * @return Stringa con l'autore della canzone
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Getter del genere musicale della canzone
     * @return Stringa con il genere musicale della canzone
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Getter dell'anno di pubblicazione della canzone
     * @return Intero che rappresenta l'anno di pubblicazione della canzone
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter della durata della canzone in millisecondi
     * @return Intero che rappresenta la durata della canzone in millisecondi
     */
    public int getDuration() {
        return duration_ms;
    }

    /**
     * Converte i millisecondi in minuti e secondi nel formato "minuti:secondi"
     * @return Stringa con la conversione da millisecondi in minuti e secondi
     */
    public String millisToTime(){
        long second = (duration_ms / 1000) % 60;
        long minute = (duration_ms / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minute, second);
    }

    /**
     * Ritorna una stringa che contiene le informazioni del brano
     * @return String che contiene i dati del brano divisi dal separatore ';'
     */
    public String toString() {
        return id + ";" + title + ";" + author + ";" + genre + ";" + year + ";" + duration_ms;
    }

}
