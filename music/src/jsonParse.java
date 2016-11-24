/**
 * Created by Bimo on 15-Nov-16.
 */

import jdk.nashorn.internal.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class jsonParse {

    public static void main(String args[]){
        try {

            //File file = new File("src/main/java/read.json");

            String isifile = readFile("src/main/java/read.json", StandardCharsets.UTF_8);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
