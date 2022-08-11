package it.uninsubria.app.views;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.users.User;
import it.uninsubria.app.views.utils.DisplayColors;

import javax.security.auth.login.LoginContext;
import java.util.Scanner;
import java.util.Vector;

public class Display {

    public static final String CHECK = "✔️";
    public static final String X = "✘";
    public static final String WARNING = "⚠";
    public static final String INFO = "ℹ️";
    public static final String SQUARE_FILLED = "⬛";
    public static final String SQUARE_EMPTY = "⬜";




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



    /*public static void printMenu(boolean showTitle){
        if (showTitle) printTitle();
        System.out.println("\n(1) - Visualizza tutte le canzoni disponibili");
        System.out.println("(2) - Registrati all'applicazione");
        System.out.println("(3) - Crea una playlist");
        System.out.println("(0) - Esci");
    }*/

    public static void printMenu() {
        System.out.println("\n(1) - Login / Logout");
        System.out.println("(2) - Registrazione");
        System.out.println("(3) - Profilo");
        System.out.println("(4) - Statistiche");
        System.out.println("(5) - Crea una playlist");
        System.out.println("(6) - Recensisci una o più canzoni");
        System.out.println("(7) - Visualizza tutte le canzoni");
        System.out.println("(0) - Esci");
    }

    public static void printResearchOptions() {
        Display.printSectionTitle("\nRicerca Avanzata");
        System.out.println("(1) - Ricerca brano per titolo");
        System.out.println("(2) - Ricerca brano per autore ed anno");
    }

    public static void printCredits(){
        System.out.println(
                "Realizzato da Erik Gurzau, Alessia Metaj, Sara Biavaschi" +
                        "\n© 2022 Erik Gurzau, Alessia Metaj, Sara Biavaschi. Tutti i diritti riservati."
        );
    }

    public static void printError(String message){
        System.out.print(DisplayColors.RED + WARNING + " " + message + DisplayColors.RESET);
    }

    public static void printInfo(String message){
        System.out.print(DisplayColors.BLUE + message + DisplayColors.RESET);
    }

    public static void printSubtitle(String message){
        System.out.println(DisplayColors.GREEN_BOLD_BRIGHT + message + DisplayColors.RESET);
    }

    public static void printSectionTitle(String message){
        System.out.println(DisplayColors.CYAN_BOLD_BRIGHT + message + DisplayColors.RESET);
    }

    public static void printAuthSuccess(String message){
        String msgFormat = "|     %-" + (message.length() + 7) + "s|%n";
        System.out.println();
        System.out.println(DisplayColors.GREEN + "+" + "—".repeat(9 + message.length() + 4) + "+");
        System.out.format(msgFormat, CHECK + " " + message);
        System.out.println("+" + "—".repeat(9 + message.length() + 4) + "+" + DisplayColors.RESET);
    }

    public static void printAuthFailed(String message){
        String msgFormat = "|      %-" + (message.length() + 7) + "s  |%n";
        System.out.println();
        System.out.println(DisplayColors.RED + "+" + "—".repeat(9 + message.length() + 4) + "+");
        System.out.format(msgFormat, X + "️" + message);
        System.out.println("+" + "—".repeat(9 + message.length() + 4) + "+" + DisplayColors.RESET);
    }

    public static void printUserInfo(User user) {
        Display.printSubtitle("\nUTENTE SESSIONE");
        System.out.println(user.getName() + " " + user.getSurname() + " (" + user.getEmail() + ")");

    }

    public static void printListEmotions(Vector<Emotion> list) {
        Input in = new Input();
        Emotion em1 = new Emotion(1,"Amazement","Feeling of wonder or happiness");
        Emotion em2 = new Emotion(2,"Solemnity", "Feeling of transcendence, inspiration. Thrills.");
        Emotion em3 = new Emotion(3, "Tenderness", "Sensuality, affect, feeling of love");
        Emotion em4 = new Emotion(4, "Nostalgia", "Dreamy, melancholic, sentimental feelings");
        Emotion em5 = new Emotion (5, "Power", "Feeling of strong, heroic, triumphant, energetic");
        Emotion em6 = new Emotion(6, "Joy", "Feels like dancing, bouncy feeling, animated, amused");
        Emotion em7 = new Emotion(7, "Tension", "Feeling nervous, impatient, irritated");
        Emotion em8 = new Emotion(8, "Sadness", "Feeling depressed, sorrowful");

        list.add(em1); list.add(em2); list.add(em3); list.add(em4); list.add(em5); list.add(em6); list.add(em7); list.add(em8);

        String tableFormat = "| %-5s | %-32s | %-60s |%n";

        System.out.println();
        System.out.println("+———————+——————————————————————————————————+——————————————————————————————————————————————————————————————+");
        System.out.println("| ID    | Emozione                         | Descrizione                                                  |");
        System.out.println("+———————+——————————————————————————————————+——————————————————————————————————————————————————————————————+");

        for (int i = 0; i < list.size(); i++) {
            Emotion e = list.elementAt(i);
            System.out.format(tableFormat, e.getId(), e.getName(), e.getExplanation());
        }
    }

    public static void printListSongs(Vector<Song> list){
        if (list.isEmpty()) {
            System.out.println(" ");
        } else {
            Input in = new Input();
            String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";
            int page = 1, songsPerPage = 50;


            System.out.println();
            System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
            System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
            System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");

            for (int i = 0; i < list.size(); i++) {
                Song s = list.elementAt(i);
                System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

                if (i == (songsPerPage * page) - 1) {
                    System.out.println();
                    char risp = in.readYesNo("Vuoi continuare? (yes/no) : ");
                    System.out.println();

                    if (risp == 'n' || risp == 'N')
                        break;
                    page++;
                }
            }
        }
    }


}
