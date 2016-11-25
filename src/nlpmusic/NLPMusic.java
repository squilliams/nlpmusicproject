/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpmusic;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author toshiba
 */
public class NLPMusic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        
        MusicDataLoader load = new MusicDataLoader("music.json");
        ArrayList<musicdata> arr = load.load();
        musicdata.removeNoise(arr);
        ArrayList<String> tem = musicdata.convert(arr, true);
        StringClusterer clust = new StringClusterer(1, 2, 10000);
        ArrayList<ArrayList<String>> ret = clust.cluster(tem);
        
        
        ArrayList<ArtistData> artists = new ArrayList<>();
        
        for (ArrayList<String> ar : ret) {            
            ArtistData ard = new ArtistData();
            for (String s : ar) {
                ard.addName(s);
            }
            artists.add(ard);            
        }
        int a = 0;
        for (musicdata music : arr) {
            //System.out.println("*" + (a++) + "* " +music.toString());
            for (ArtistData artist : artists) {
                if (artist.is(music.getArtist())) {
                    //System.out.println("\t A : " + artist.getName());
                    boolean changed = false;
                    for (SongData song : artist.getSongs()) {
                        if (song.is(music.getSong())) {
                            //System.out.println("\t S : " + song.Name());
                            song.addCount();
                            changed = true;
                            break;
                        }
                    }
                    if (!changed) {
                        //System.out.println("NEW");
                        SongData song = new SongData();
                        song.addName(music.getSong());
                        artist.addSong(song);
                    }
                    break;
                }
            }
        }
        int iter = 0;
        for (int i = 0; i < artists.size(); i++) {
            System.out.println("\n*" + (iter++) + "*\n");
            System.out.println(artists.get(i).toString());
            if (artists.get(iter).getTotalPlays() < artists.get(i).getTotalPlays())
                iter = i;
        }   
        
        
        System.out.println("\n\nTOP ARTIST");
        System.out.println(artists.get(iter));
        System.out.println("\n\nTotal Docs = " + arr.size());
    }
    
}
