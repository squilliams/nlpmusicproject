package nlpmusic;

/**
 * Created by Bimo on 15-Nov-16.
 */

import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MusicDataLoader {
    
    String file;
    
    public MusicDataLoader(String path) {
        file = path;
    }
    
    public ArrayList<musicdata> load() {
        ArrayList<musicdata> localmusiclist = new ArrayList<musicdata>();
        JSONParser parser = new JSONParser();
        ArrayList<String> listoftwit = new ArrayList<String>();
        ArrayList<musicdata> candidatelist = new ArrayList<musicdata>();
        String temp;
        String content;
        String cleancontent;
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        String hashtagPattern = "#\\w+";
        String bintangPattern = "\\*";
        String retweetpattern = "RT @\\w+:";
        try {

            File f = new File(file);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()){
                temp = scanner.nextLine();
                JSONObject tweet = (JSONObject) parser.parse(temp);
                content = (String) tweet.get("content");

                cleancontent = removeUnused(content, hashtagPattern);
                cleancontent = removeUnused(cleancontent, urlPattern);
                cleancontent = removeUnused(cleancontent, bintangPattern);
                cleancontent = removeUnused(cleancontent, retweetpattern);                
                listoftwit.add(cleancontent.toLowerCase());

            }            
            
            // Menambahkan daftar kandidat
            for (int i=0;i<listoftwit.size();i++){
                addtomusiclist(listoftwit.get(i), localmusiclist);
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return localmusiclist;
    }
    private static String removeUnused(String commentstr, String pattern){

        String removePattern = pattern;
        Pattern p = Pattern.compile(removePattern);
        Matcher m = p.matcher(commentstr);

        int i = 0;
        while (m.find()) {
            commentstr = m.replaceAll("");
            i++;
        }

        return commentstr;
    }

    private static void addtomusiclist(String input, ArrayList<musicdata> localmusiclist){
        
        Pattern p0 = Pattern.compile("[.]*\"([^\"]*)\"[.]*");

        Matcher matcher0 = p0.matcher(input);

        if (matcher0.find()){
            String lagu = matcher0.group();
            String artis = removeUnused(input, "[.]*\"([^\"]*)\"[.]*");
            localmusiclist.add(new musicdata(artis, lagu));
        }else{
            String sep1 = "-";
            String sep2 = "by";

            Pattern p1 = Pattern.compile(sep1);

            //Masalah match dengan pola "-"
            Matcher matcher1 = p1.matcher(input);
            int count = 0;
            while(matcher1.find()){
                count++;
            }
            if (count > 0) {
                String[] candidate = input.split("-");
                int i = 0;
                for (i=0; i<candidate.length-1; i++) {
                    localmusiclist.add(new musicdata(candidate[i].trim(), candidate[i+1].trim()));
                    localmusiclist.add(new musicdata(candidate[i+1].trim(), candidate[i].trim()));
                }
            }

            Pattern p2 = Pattern.compile(sep2);

            //Masalah match dengan pola by
            Matcher matcher2 = p2.matcher(input);
            int count2 = 0;
            while (matcher2.find()){
                count2++;
            }
            if (count2 > 0){
                String[] candidate = input.split("by");
                localmusiclist.add(new musicdata(candidate[1].trim(), candidate[0].trim()));
            }
        }        
    }
}

