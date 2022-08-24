package it.uninsubria.app.views;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.emotionalsongs.EmotionalSongs;
import it.uninsubria.app.managers.EmotionsManager;
import it.uninsubria.app.managers.SongsManager;
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
 * @see     it.uninsubria.app.users.User
 * @see     it.uninsubria.app.views.utils.DisplayColors
 */
public class Display {

    /**
     * Icon del check
     */
    public static final String CHECK = "✔️";

    /**
     * Icon della X
     */
    public static final String X = "✘";

    /**
     * Icona del warning
     */
    public static final String WARNING = "⚠";

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
        System.out.println("(3) - Statistiche");
        System.out.println("(4) - Crea una playlist");
        System.out.println("(5) - Recensisci una o più canzoni");
        System.out.println("(6) - Ricerca un brano");
        System.out.println("(7) - Visualizza un report emozionale per un brano");
        System.out.println("(8) - Visualizza tutte le canzoni");
        System.out.println("(0) - Esci");
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
        System.out.print(DisplayColors.RED + WARNING + " " + message + DisplayColors.RESET);
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
        System.out.println(DisplayColors.GREEN_BOLD_BRIGHT + subtitle + DisplayColors.RESET);
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
        String msgFormat = "|     %-" + (message.length() + 7) + "s|%n";
        System.out.println();
        System.out.println(DisplayColors.GREEN + "+" + "—".repeat(9 + message.length() + 4) + "+");
        System.out.format(msgFormat, CHECK + " " + message);
        System.out.println("+" + "—".repeat(9 + message.length() + 4) + "+" + DisplayColors.RESET);
    }

    /**
     * Stampa a display un riquardo di colore rosso che segnala all'utente che
     * una determinata operazione non è avvenuta con successo
     * @param message Stringa da visualizzare con il messaggio di operazione fallita
     */
    public static void printBoxFailed(String message){
        String msgFormat = "|      %-" + (message.length() + 7) + "s  |%n";
        System.out.println();
        System.out.println(DisplayColors.RED + "+" + "—".repeat(9 + message.length() + 4) + "+");
        System.out.format(msgFormat, X + "️" + message);
        System.out.println("+" + "—".repeat(9 + message.length() + 4) + "+" + DisplayColors.RESET);
    }

    /**
     * Stampa a video le informazioni dell'utente che ha eseguito il login nell'applicazione
     * @param user Utente autenticato dell'applicazione
     */
    public static void printUserInfo(User user) {
        Display.printSubtitle("\nUTENTE SESSIONE");
        System.out.println(user.getName() + " " + user.getSurname() + " (" + user.getEmail() + ")");

    }

    public static void printSystemPause(Input in) {
        in.readString("\nPremi un tasto per continuare...",true);
    }


    /**
     * Stampa una tabella con le informazioni dei brani presenti nella lista.
     * Se la lista ha un grandezza maggiore di MAX_SONGS_PER_PAGE, allora
     * verranno stampati MAX_SONGS_PER_PAGE brani e verrà chiesto all'utente
     * se vuole visualizzare i prossimi MAX_SONGS_PER_PAGE, e così via fino alla
     * fine della lista
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
            System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
            System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
            System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");

            for (int i = 0; i < list.size(); i++) {
                Song s = list.elementAt(i);
                System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

                if (i == (MAX_SONG_PER_PAGE * page) - 1) {
                    System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
                    char risp = in.readYesNo("Vuoi continuare? (yes/no) : ");
                    System.out.println();

                    if (risp == 'n' || risp == 'N')
                        break;
                    page++;
                }
            }
            if (!isPaged)
                System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
        }
    }

    public static void printPlaylist(Vector<Playlist> list, SongsManager songsManager) {
        if (list.isEmpty()) {
            Display.printError("Nessuna playlist creata da te! ");
        } else {
            String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";

            for (Playlist p : list) {
                System.out.println(DisplayColors.CYAN_BOLD_BRIGHT + p.getName() + DisplayColors.RESET);
                System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
                System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
                System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
                for (int songId : p.getListIdSongs()){
                    Song s = songsManager.getSong(songId);
                    System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

                }
                System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
                System.out.println();
            }


        }
    }

    public static void printListEmotions(Vector<Emotion> emotionsList) {
        String tableFormat = "| %-5s | %-32s | %-60s |%n";

        System.out.println("+———————+——————————————————————————————————+——————————————————————————————————————————————————————————————+");
        System.out.println("| ID    | Emozione                         | Descrizione                                                  |");
        System.out.println("+———————+——————————————————————————————————+——————————————————————————————————————————————————————————————+");

        for (Emotion e: emotionsList) {
            System.out.format(tableFormat, e.getId(), e.getCategory(), e.getExplanation());
        }
        System.out.println("+———————+——————————————————————————————————+——————————————————————————————————————————————————————————————+");

    }


    public static void printReportEmotionalTag(EmotionalSongs app, int songId) {
        String tableFormat = "| %-25s | %-13s | %-8s |%n";
        System.out.println();
        System.out.println("+———————————————————————————+———————————————+——————————+");
        System.out.println("| Emozione                  | Recensioni    | Media    |");
        System.out.println("+———————————————————————————+———————————————+——————————+");

        for (Emotion e: app.getEmotionList()){
            int totFeedback = app.countFeedback(songId, e.getId());
            int totScoreFeedback = app.totScoreFeedback(songId, e.getId());
            double media = totScoreFeedback > 0 ? totScoreFeedback / (double)totFeedback : 0;
            //Emotion emotionMostCommon = app.emotionMostCommon(songId);
            //Emotion emotionLessCommon = app.emotionLessCommon(songId);
            System.out.format(tableFormat, e.getCategory(), totFeedback, media);
        }
        System.out.println("+———————————————————————————+———————————————+——————————+");
    }
    
    public static void printListNotes(EmotionalSongs app, int songId) {
        for (Emotion e: app.getEmotionList()) {
            Vector<String> listNotes = app.listNotes(songId, e.getId());
            System.out.println("* " + e.getCategory() + ":");
            if(listNotes.isEmpty()) {
                printInfo("Ancora nessun commento da parte degli utenti\n");
            } else {
                Arrays.stream(listNotes.toArray()).forEach(System.out::println);
            }
        }
    }

}
