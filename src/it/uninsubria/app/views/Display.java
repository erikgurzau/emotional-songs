package it.uninsubria.app.views;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.emotionalsongs.Feedback;
import it.uninsubria.app.managers.CommandManager;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.users.User;
import it.uninsubria.app.views.utils.DisplayColors;

import java.util.Vector;

/**
 * Classe che definisce il display dell'applicazione, si occupa di gestire
 * tutti gli elementi basilari dell'interfaccia utente
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see     it.uninsubria.app.views.utils.DisplayColors
 * @see     it.uninsubria.app.users.User
 * @see     it.uninsubria.app.managers.CommandManager
 * @see     it.uninsubria.app.songs.Song
 * @see     it.uninsubria.app.songs.Playlist
 * @see     it.uninsubria.app.input.Input
 * @see     it.uninsubria.app.emotionalsongs.Emotion
 */
public class Display {

    /**
     * Numero massimo di canzoni, da visualizzare a schermo, per pagina
     */
    public static final int MAX_SONG_PER_PAGE = 50;


    /**
     * Stampa a display il titolo dell'applicazione
     */
    public static void printTitle(){
        System.out.println("" +
                "████████\\ ██\\      ██\\  ██████\\ ████████\\ ██████\\  ██████\\  ██\\   ██\\  ██████\\  ██\\       ██████\\   ██████\\  ██\\   ██\\  ██████\\   ██████\\  \n" +
                "██  _____|███\\    ███ |██  __██\\\\__██  __|\\_██  _|██  __██\\ ███\\  ██ |██  __██\\ ██ |     ██  __██\\ ██  __██\\ ███\\  ██ |██  __██\\ ██  __██\\ \n" +
                "██ |      ████\\  ████ |██ /  ██ |  ██ |     ██ |  ██ /  ██ |████\\ ██ |██ /  ██ |██ |     ██ /  \\__|██ /  ██ |████\\ ██ |██ /  \\__|██ /  \\__|\n" +
                "█████\\    ██\\██\\██ ██ |██ |  ██ |  ██ |     ██ |  ██ |  ██ |██ ██\\██ |████████ |██ |     \\██████\\  ██ |  ██ |██ ██\\██ |██ |████\\ \\██████\\  \n" +
                "██  __|   ██ \\███  ██ |██ |  ██ |  ██ |     ██ |  ██ |  ██ |██ \\████ |██  __██ |██ |      \\____██\\ ██ |  ██ |██ \\████ |██ |\\_██ | \\____██\\ \n" +
                "██ |      ██ |\\█  /██ |██ |  ██ |  ██ |     ██ |  ██ |  ██ |██ |\\███ |██ |  ██ |██ |     ██\\   ██ |██ |  ██ |██ |\\███ |██ |  ██ |██\\   ██ |\n" +
                "████████\\ ██ | \\_/ ██ | ██████  |  ██ |   ██████\\  ██████  |██ | \\██ |██ |  ██ |████████\\\\██████  | ██████  |██ | \\██ |\\██████  |\\██████  |\n" +
                "\\________|\\__|     \\__| \\______/   \\__|   \\______| \\______/ \\__|  \\__|\\__|  \\__|\\________|\\______/  \\______/ \\__|  \\__| \\______/  \\______/");
    }


    /**
     * Stampa a display il menù generale di tutta l'applicazione
     */
    public static void printMenu() {
        System.out.println("\n(1) - Login / Logout");
        System.out.println("(2) - Registrazione");
        System.out.println("(3) - Ricerca un brano");
        System.out.println("(4) - Crea una playlist");
        System.out.println("(5) - Visualizza le tue playlist;");
        System.out.println("(5) - Recensisci una o più canzoni");
        System.out.println("(7) - Visualizza un report emozionale su una canzone");
        System.out.println("(8) - Visualizza un report emozionale su una tua playlist");
        System.out.println("(9) - Visualizza tutte le canzoni");
        System.out.println("(0) - Esci\n");
    }

    /**
     * Stampa a display il menù per la ricerca di un brano
     */
    public static void printResearchOptions() {
        Display.printSectionTitle("\nRicerca Avanzata");
        System.out.println("(1) - Ricerca brano per titolo");
        System.out.println("(2) - Ricerca brano per autore ed anno\n");
    }

    /**
     * Stampa a display gli autori dell'applicazione
     */
    public static void printCredits(){
        System.out.println(
                "Realizzato da Erik Gurzau, Alessia Metaj, Sara Biavaschi" +
                "\n© 2022 Erik Gurzau, Alessia Metaj, Sara Biavaschi. Tutti i diritti riservati."
        );
    }

    /**
     * Stampa a video un messaggio di errore di colore rosso e con l'icona del WARNING
     * @param message Stringa contenente il messaggio da visualizzare
     */
    public static void printError(String message){
        System.out.print(DisplayColors.RED + message + DisplayColors.RESET);
    }

    /**
     * Stampa a display un messaggio generico di colore blu e con l'icona di INFO
     * @param message Stringa contenente il messaggio da visualizzare
     */
    public static void printInfo(String message){
        System.out.print(DisplayColors.BLUE + message + DisplayColors.RESET);
    }

    /**
     * Stampa a display un titolo riguardante la sezione del menù scelta dall'utente
     * @param subtitle Stringa contenete il titolo da visualizzare
     */
    public static void printSubtitle(String subtitle){
        System.out.println(DisplayColors.YELLOW_BRIGHT + subtitle + DisplayColors.RESET);
    }

    /**
     * Stampa a display un titolo riguardante la sezione del menù scelta dall'utente
     * @param subtitle Stringa contenete il titolo da visualizzare
     * @param eof Booleano per inserire il carattere di end of line '\n'
     */
    public static void printSubtitle(String subtitle, boolean eof){
        if (eof) printSubtitle(subtitle);
        else System.out.print(DisplayColors.GREEN_BOLD_BRIGHT + subtitle + DisplayColors.RESET);
    }


    /**
     * Stampa a display un sotto-titolo riguardante la sezione del menù scelta dall'utente
     * @param sectionTitle Stringa contenete il sotto-titolo da visualizzare
     */
    public static void printSectionTitle(String sectionTitle){
        System.out.println(DisplayColors.CYAN_BOLD_BRIGHT + sectionTitle + DisplayColors.RESET);
    }

    /**
     * Stampa a display un sotto-titolo riguardante la sezione del menù scelta dall'utente.
     * In aggiunta è possibile scegliere se aggiungere una andata a capo alla fine del testo
     * @param sectionTitle Stringa contenete il sotto-titolo da visualizzare
     * @param endOfLine Booleano per aggiungere il carattere '\n'.
     *                  Se {@code = true} viene aggiunto il carattere '\n'.
     *                  Altrimenti {@code = false} non viene aggiunto.
     */
    public static void printSectionTitle(String sectionTitle, boolean endOfLine){
        if (endOfLine)
            printSectionTitle(sectionTitle);
        else
            System.out.print(DisplayColors.CYAN_BOLD_BRIGHT + sectionTitle + DisplayColors.RESET);

    }

    /**
     * Stampa a display un riquardo di colore giallo che segnala all'utente che
     * una determinata operazione è avvenuta con successo
     * @param message Stringa da visualizzare con il messaggio di operazione riuscita
     */
    public static void printBoxSuccess(String message){
        int spaceX = 8;
        int totSpace = (spaceX * 2) + message.length();
        System.out.println();
        System.out.println(DisplayColors.GREEN + "+" + "-".repeat(totSpace) + "+");
        System.out.println("|" + " ".repeat(8) + message + " ".repeat(8) + "|");
        System.out.println("+" + "-".repeat(totSpace) + "+" + DisplayColors.RESET);
    }

    /**
     * Stampa a display un riquardo di colore rosso che segnala all'utente che
     * una determinata operazione non è avvenuta con successo
     * @param message Stringa da visualizzare con il messaggio di operazione fallita
     */
    public static void printBoxFailed(String message){
        int spaceX = 8;
        int totSpace = (spaceX * 2) + message.length();
        System.out.println();
        System.out.println(DisplayColors.RED + "+" + "-".repeat(totSpace) + "+");
        System.out.println("|" + " ".repeat(spaceX) + message + " ".repeat(spaceX) + "|");
        System.out.println("+" + "-".repeat(totSpace) + "+" + DisplayColors.RESET);

    }

    /**
     * Stampa a video le informazioni dell'utente che ha eseguito il login nell'applicazione
     * @param user Utente autenticato dell'applicazione
     */
    public static void printUserInfo(User user) {
        Display.printSubtitle("\nUTENTE SESSIONE");
        System.out.println(user.getName() + " " + user.getSurname() + " (" + user.getEmail() + ")");

    }

    /**
     * Stampa una pausa del sistema, premendo il tasto 'Invio' si continua con il regolare flusso del programma
     * @param in Input che legge dalla console il tasto 'Invio'
     */
    public static void printSystemPause(Input in) {
        in.readEnter("\nPremi un 'invio' per continuare...");
    }


    /**
     * Stampa una tabella con le informazioni dei brani presenti nella lista.
     * Se la lista ha un grandezza maggiore di MAX_SONGS_PER_PAGE, allora
     * verranno stampati MAX_SONGS_PER_PAGE brani e verrà chiesto all'utente
     * se vuole visualizzare i prossimi MAX_SONGS_PER_PAGE,
     * e così via fino alla fine della lista
     * @param list Lista di canzoni
     */
    public static void printListSongs(Vector<Song> list){
        if (list.isEmpty()) {
            System.out.println();
            Display.printError("Nessuna canzone trovata!\n");
        } else {
            Input in = new Input();
            String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";
            int page = 1;
            boolean isPaged = list.size() / MAX_SONG_PER_PAGE > 1;


            System.out.println();
            System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");
            System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
            System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");

            for (int i = 0; i < list.size(); i++) {
                Song s = list.elementAt(i);
                System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

                if (i == (MAX_SONG_PER_PAGE * page) - 1) {
                    System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");
                    char risp = in.readYesNo("Vuoi continuare? (yes/no) : ");
                    System.out.println();

                    if (risp == 'n' || risp == 'N')
                        break;
                    page++;
                }
            }
            if (!isPaged)
                System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");
        }
    }

    /**
     * Stampa una tabella con le informazioni dei brani presenti nella playlist.
     * @param app Manager per ricavare le informazioni di una canzone mediante l'ID della canzone presente nella playlist
     * @param list Lista di playlist create da un utente X
     */
    public static void printPlaylist(CommandManager app, Vector<Playlist> list) {
        if (list.isEmpty()) {
            Display.printError("Nessuna playlist creata da te! ");
        } else {
            String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";

            for (Playlist p : list) {
                System.out.println(DisplayColors.CYAN_BOLD_BRIGHT + p.getName() + DisplayColors.RESET);
                System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");
                System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
                System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");
                for (int songId : p.getListIdSongs()){
                    Song s = app.getSongById(songId);
                    System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

                }
                System.out.println("+-------+---------------------------------------------------+------------------------------------------+--------+----------+--------+");
                System.out.println();
            }


        }
    }


    /**
     * Stampa in una tabella un report sui tag emozionali di una canzone
     * @param app Manager per ricavare le informazioni delle emozioni e calcolare i parametri per generare il report
     * @param songId Intero che rappresenta l'ID della canzone sulla quale bisogna calcolare i parametri per generare il report
     */
    public static boolean printReportSong(CommandManager app, int songId) {
        String tableFormat = "| %-25s | %-13s | %-8s |%n";

        if (app.hasFeedback(songId)) {
            System.out.println();
            System.out.println("+---------------------------+---------------+----------+");
            System.out.println("| Emozione                  | Recensioni    | Media    |");
            System.out.println("+---------------------------+---------------+----------+");

            int totFeedback = app.countFeedback(songId);
            for (Emotion e: app.getEmotionList()){
                int totScoreFeedback = app.totScoreFeedback(songId, e.getId());
                double media = totScoreFeedback > 0 ? totScoreFeedback / (double)totFeedback : 0;

                System.out.format(tableFormat, e.getCategory(), totFeedback, media);
            }
            System.out.println("+---------------------------+---------------+----------+");
            return true;
        }
        else {
            System.out.println();
            Display.printError("Questa canzone non ha ancora ricevuto nessuna recensione emozionale!\n");
            return false;
        }
    }

    /**
     * Stampa in una tabella un report sui tag emozionali delle canzoni di una playlist
     * @param app Manager per ricavare le informazioni delle emozioni e calcolare i parametri per generare il report
     * @param p Playlist sulla quale calcolare il report dei tag emozionali
     */
    public static void printReportPlaylist(CommandManager app, Playlist p){
        Vector<Emotion> listEmotion = app.getEmotionList();

        // HEADER DELLA TABELLA
        System.out.println("\n+--------------------------------------------------+" + "-------------+".repeat(listEmotion.size() + 1));
        System.out.print("|                                                  |");
        for (int i = 0; i < listEmotion.size(); i++) { // ciclo per le emozioni
            int spaceAfterName = 11 - listEmotion.get(i).getCategory().length();
            System.out.print("  " + listEmotion.get(i).getCategory() + " ".repeat(spaceAfterName) + "|");
        }
        System.out.println("             |");
        System.out.println("|                     Brano                        +" + "-------------+".repeat(listEmotion.size()) + "  Recensioni |");
        System.out.print("|                                                  |");
        for (int i = 0; i < listEmotion.size(); i++) {
            System.out.print("    Media    |");
        }
        System.out.println("    totali   |");
        System.out.println("+--------------------------------------------------+" + "-------------+".repeat(listEmotion.size() + 1));


        // BODY DELLA TABELLA
        for (int songId : p.getListIdSongs()) {
            Song s = app.getSongById(songId);
            int totFeedback = app.countFeedback(songId);

            System.out.print("| " + s.getTitle() + " ".repeat(49 - s.getTitle().length()) + "|");
            for (int i = 0; i < listEmotion.size(); i++) {
                Emotion e = listEmotion.get(i);

                int totScoreFeedback = app.totScoreFeedback(songId, e.getId());
                double media = totScoreFeedback > 0 ? totScoreFeedback / (double)totFeedback : 0;

                System.out.print("     " + media + " ".repeat(8 - (media + "").length()) + "|");
            }
            System.out.println("     " + totFeedback + " ".repeat(8 - (totFeedback + "").length()) + "|");
        }
        System.out.println("+--------------------------------------------------+" + "-------------+".repeat(listEmotion.size() + 1));


    }

    /**
     * Stampa a video tutti i commenti rilasciati dagli utenti per una determinata canzone.
     * Ogni commento viene anteceduto dal nome dell'utente che lo ha rilasciato.
     * @param app Manager per ricavare le informazioni dei commenti di una canzone mediante il suo ID
     * @param songId Intero che rappresenta l'ID della canzone della quale bisogna ricavare i commenti rilasciati
     */
    public static void printComments(CommandManager app, int songId) {

        for (Emotion e: app.getEmotionList()) {
            Vector<Feedback> listFeedback = app.getFeedbacksIfHasNote(songId, e.getId());
            System.out.println("\n* " + e.getCategory());
            if (!listFeedback.isEmpty()) {
                for (Feedback f : listFeedback) {
                    Display.printSectionTitle(app.getUserById(f.getUserId()).getName(), false);
                    System.out.println(": " + f.getNote());
                }
            }
            else Display.printInfo("Ancora nessun commento da parte degli utenti per questa canzone\n");
        }
    }


}
