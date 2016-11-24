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
        this.artist = artis;
        this.song = lagu;
    }
}
