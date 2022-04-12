package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.views.Display;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class EmotionalSongs {

    public static String[] generi={"Pop","Rock","Jazz","Techno","Country","Ballad","House","Neomelodico","Funk","Raggae","Rap","Trap","Metal","Indie","Latino"};
    public static  Vector<String> formatsong(){
        Vector<String> listaStringhe;
        FileManager fm = new FileManager("./data/Canzoni.txt");
        listaStringhe= fm.getContent();

        return listaStringhe;
    }


    public static String readGenre(){
        Random rand= new Random();
        int x=rand.nextInt(generi.length);
        return generi[x];
    }

    public static long readLength(){
        Random rand=new Random();
        int min=150000, max=300000;
        long x=rand.nextLong((max-min)+1)+min;
        return x;
    }
    public static Vector<Song> scriviFile(){
        Vector<String> list= formatsong();
        Vector<Song> canzoni = new Vector<Song>();
        for (String riga:list){
            StringTokenizer stk= new StringTokenizer(riga,",");
            while(stk.hasMoreTokens()){
                String indice=stk.nextToken();
                String autore=stk.nextToken();
                String titolo=stk.nextToken();
                String anno=stk.nextToken();
                String b=stk.nextToken();
                String c=stk.nextToken();
                String d=stk.nextToken();
                String e=stk.nextToken();
                String f= stk.nextToken();

                Song song=new Song(titolo,autore,readGenre(),Integer.parseInt(anno),readLength());
                canzoni.add(song);
            }
        }
        return canzoni;
    }
    public static void registrasong(){
        FileManager fm= new FileManager("./data/Canzoni.txt");
        Vector<Song> listasong=scriviFile();
        fm.clear();
        for(int i=0;i< listasong.size();i++){
            fm.println(listasong.get(i).toString(),'a');
        }
    }
    public static void main(String[] args) {

        scriviFile();

        //Display.printMenu();
    }
}
