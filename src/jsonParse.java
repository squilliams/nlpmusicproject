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

public class jsonParse {

    static ArrayList<musicdata> localmusiclist = new ArrayList<musicdata>();

    public static void main(String args[]) {
        JSONParser parser = new JSONParser();
        ArrayList<String> listoftwit = new ArrayList<String>();
        ArrayList<musicdata> candidatelist = new ArrayList<musicdata>();
        String temp;
        String content;
        String cleancontent;
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        String hashtagPattern = "#\\w+";
        String bintangPattern = "\\*";
        try {

            File f = new File("music.json");
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()){
                temp = scanner.nextLine();
                JSONObject tweet = (JSONObject) parser.parse(temp);
                content = (String) tweet.get("content");

                cleancontent = removeUnused(content, hashtagPattern);
                cleancontent = removeUnused(cleancontent, urlPattern);
                cleancontent = removeUnused(cleancontent, bintangPattern);

                listoftwit.add(cleancontent);

            }
            int i = 0;

            // Menambahkan daftar kandidat
            for (i=0;i<listoftwit.size();i++){
                addtomusiclist(listoftwit.get(i));
            }

            System.out.println(listoftwit.get(10));
            System.out.println(localmusiclist.get(25).getSong());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    private static void addtomusiclist(String input){
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
                localmusiclist.add(new musicdata(candidate[i], candidate[i+1]));
                localmusiclist.add(new musicdata(candidate[i+1], candidate[i]));
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
            localmusiclist.add(new musicdata(candidate[1], candidate[0]));
        }

    }
}

