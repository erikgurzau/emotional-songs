package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.views.Display;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class EmotionalSongs {

    /**
     * Array di stringhe contenenti le tipologie di generei musicali
     */
    public static String[] generi={"Pop","Rock","Jazz","Techno","Country","Ballad","House","Funk","Raggae","Rap","Trap","Metal","Indie","Latino"};

    /**
     * Valore intero che rappresenta la durata minima di una canzone
     */
    public static final int MIN_DURATION = 150000;

    /**
     * Valore intero che rappresenta la durata massima di una canzone
     */
    public static final int MAX_DURATION = 300000;


    /**
     * Attraverso il metodo getContent() di FileManager, prende le righe del file "Canzoni.txt"
     * e le inserisce in una lista (Vector).
     * Ogni nodo della lista contiene una riga del file
     * @return La lista contenente le righe del file
     */
    public static  Vector<String> getListOfFileLines(){
        FileManager fm = new FileManager("./data/Canzoni.txt");
        Vector<String> listaStringhe = fm.getContent();
        return listaStringhe;
    }
    

    /**
     * Genera un numero intero casuale x compreso tra 0 e N, dove N è la lunghezza dell'array di generi possibili,
     * e ritorna l'elemento dell'array in posizione x.
     * @return Il genere in posizione x, in formato String, nell'array di generi
     */
    public static String getRandomGenre(){
        Random rand = new Random();
        int x = rand.nextInt(generi.length);
        return generi[x];
    }
    
    

    /**
     * Genera un numero intero casuale tra min e max
     * @param min il valore minimo
     * @param max il valore massimo
     * @return Il numero intero estratto randomicamente
     */
    public static int getRandomInt(int min, int max){
        Random rand = new Random();
        int x = rand.nextInt((max - min) + 1) + min;
        return x;
    }
    
    

    /**
     * Formatta, cioè prende e modifica i dati dal file delle canzoni in modo tale che abbiano un senso e
     * un'utiità per quello che dobbiamo fare.
     * Prende i  dati "sporchi" dal file e utilizziamo le informazioni che ci servono per creare
     * una lista di canzoni
     * @return La lista di canzoni
     */
    public static Vector<Song> formatDataSongs(){
        Vector<String> list= getListOfFileLines(); // Lista che contiene le righe del file in formato String
        Vector<Song> canzoni = new Vector<Song>(); // Creo una lista di canzoni di tipo Song

        for (String riga:list){ // Itero la lista delle righe per ricavare le informazione che mi servono
            StringTokenizer stk= new StringTokenizer(riga,","); // Divido i dati della singola riga

            /*
            Non c'è più bisogno del ciclo while perchè mi sono accorto che era inutile,
            perchè a noi ci interessano solo pochi token e non tutti,
            quindi non c'è bisogno di iterarli tutti fino alla fine.
            Possiamo decidere noi quali prendere
            */
            stk.nextToken(); // non ci serve salvare questo dato in nessuna varibile, chiamiamo questo metodo solo per saltare al token successivo
            String autore = stk.nextToken();
            String titolo = stk.nextToken();
            String anno = stk.nextToken();

            /*
            Non ce più bisogno di fare "String c = stk.nextToken();" per prendere i dati inutili,
            perchè arrivati a questo punto il ciclo foreach prenderà un'altra riga e ingnoreremo i dati inutili
             */

            Song song = new Song(titolo, autore, getRandomGenre(), Integer.parseInt(anno), getRandomInt(MIN_DURATION, MAX_DURATION));
            canzoni.add(song);

        }
        return canzoni;
    }
    
    
    /**
     * Aggiorna il file con la lista di Canzoni, ogni riga rappresenta una canzone e i dati sono separati dal ';'
     */
    public static void updateFile(){
        FileManager fm = new FileManager("./data/Canzoni.txt");
        Vector<Song> listasong = formatDataSongs(); // prende la lista di canzoni
        fm.clear(); /* cancella il contenuto del file "Canzoni.txt", lo facciamo perchè in questo punto del codice
        il file contiene tutti i dati "sporchi", che non ci servono.
        Cancelliamo queste informazioni intuili prima di mettere quele giuste e corrette per noi attraverso la lista di Canzoni
        */
        for(int i = 0; i < listasong.size(); i++){
            fm.println(listasong.get(i).toString(),'a'); // scrive nel file il toString() delle conzoni, in modalità append 'a', cioè aggiunge senza cancellare il contenuto già esistente
        }
    }
    
    public static void main(String[] args) {
        
    }
}
