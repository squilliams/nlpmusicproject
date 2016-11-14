/**
 * Created by Bimo on 13-Nov-16.
 */

import java.util.ArrayList;

public class classify {


    public static void classify(String tweetsumber){
        String[] listoftw = tweetsumber.split(" ");
        ArrayList<String> songcandidate = new ArrayList<String>();
        ArrayList<String> artistcandidate = new ArrayList<String>();
        int j, k = 0;

        for (int i = 0; i < listoftw.length; i++) {
            switch (listoftw[i].toLowerCase()) {
                case "by" :
                    j = i-1;
                    k = i + 1;
                    songcandidate.add(listoftw[j]);
                    artistcandidate.add(listoftw[k]);
                    if (j-1 >= 0) songcandidate.add(listoftw[j-1] + listoftw[j]);
                    if (k+1 <= listoftw.length) artistcandidate.add(listoftw[k] + listoftw[k+1]);
                    break;
                case "-" :
                    j = i-1;
                    k = i + 1;
                    songcandidate.add(listoftw[j]);
                    artistcandidate.add(listoftw[k]);
                    songcandidate.add(listoftw[k]);
                    artistcandidate.add(listoftw[j]);
                    if (j-1 >= 0) songcandidate.add(listoftw[j-1] + listoftw[j]);
                    if (k+1 <= listoftw.length) artistcandidate.add(listoftw[k] + listoftw[k+1]);
                    if (j-1 >= 0) artistcandidate.add(listoftw[j-1] + listoftw[j]);
                    if (k+1 <= listoftw.length) songcandidate.add(listoftw[k] + listoftw[k+1]);

                    break;
            }
        }
    };

}

