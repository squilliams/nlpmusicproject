package nlpmusic;

import java.util.ArrayList;

/**
 * Created by Bimo on 23-Nov-16.
 */
public class musicdata {
    private String artist;
    private String song;

    public String getArtist(){
        return this.artist;
    }

    public String getSong(){
        return this.song;
    }

    public musicdata(String artis, String lagu){
        this.artist = artis.trim();
        this.song = lagu.trim();
    }
    
    public String toString() {
        return this.song + " by " + this.artist;
    }    
    
    public static void removeNoise(ArrayList<musicdata> arr) {
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i).getSong().length() < 2 || arr.get(i).getArtist().length() < 2)
                arr.remove(i);
        }
    }
    
    public static ArrayList<String> convert(ArrayList<musicdata> arr, boolean artist) {
        ArrayList<String> temp = new ArrayList<>();
        
        if (artist)
            for (musicdata data: arr) {
                temp.add(data.getArtist());
            }
        else
            for (musicdata data: arr) {
                temp.add(data.getSong());
            }
        return temp;
    }
}
