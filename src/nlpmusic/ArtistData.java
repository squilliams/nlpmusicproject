package nlpmusic;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toshiba
 */
public class ArtistData {
    private ArrayList<SongData> songs = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private String rep = "";
    
    public ArtistData() {}
    
    public ArtistData(ArrayList<String> name) {
        names = name;
        rep = name.get(0);
        for (String str : name) {
            if (rep.length() >= str.length())
                rep = str;
        }
    }        
                
    public void addName(String str) {
        if (!is(str)) {
            names.add(str.replaceAll("^\'|\'$", ""));
            if (rep == "" || rep.length() >= str.length())
                rep = str;
        }
    }
    
    public void addSong(SongData song) {
        songs.add(song);
    }
    
    public SongData getSong(int i) {
        return songs.get(i);
    }
    
    public ArrayList<SongData> getSongs() {
        return songs;
    }
    public String getName() {
        return rep;
    }
    
    public boolean is(String s) {
        return names.contains(s);
    }
    
    public int getTotalPlays() {
        int tem = 0;
        for (SongData song : songs) {
            tem += song.getCount();
        }
        return tem;
    }
    
    public int getNumTrack() {
        return songs.size();
    }
    
    public int getTrackPlays(int i) {
        return songs.get(i).getCount();
    }
    
    public String toString() {
        String tem = "";
        
        tem += "Artist Name : " + rep + "\n";        
        
        tem += "Song Count : " + getNumTrack() + " \n";        
        
        for (int i = 0; i < songs.size(); i++) { 
            tem += "Song " + (i+1) + " : " + songs.get(i).Name() + ", Plays = " + songs.get(i).getCount() + "\n";
        }
        tem += "Total Plays = " + getTotalPlays();
        return tem;
    }
}
