/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpmusic;

import java.util.ArrayList;

/**
 *
 * @author toshiba
 */
public class SongData {
    private ArrayList<String> names = new ArrayList<>();
    private String rep = "";
    private int count = 0;
    
    public SongData() {}
    
    public void addName(String str) {
        if (!is(str)) {
            names.add(str.replaceAll("^\'|\'$", ""));
            if (rep == "" || rep.length() >= str.length())
                rep = str;
        }
        addCount();
    }
    
    public String Name() {
        return rep;
    }
    
    public boolean is(String s) {
        return names.contains(s);
    }
    
    public int getCount() {
        return count;
    }
    
    public void addCount(){
        count++;
    }
    
    public static void main(String[] args) {
        SongData tes = new SongData();
        tes.addName("ABC");
        tes.addName("AB");
        tes.addName("A");
        tes.addName("ABCD");
        System.out.println(tes.is("ABCDa"));
    }
}
