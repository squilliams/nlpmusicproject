package validation;

import com.discogs.api.ApiClient;
import com.discogs.api.exceptions.ApiRequestException;
import com.discogs.api.model.SearchResults;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muhtarh on 24/11/2016.
 */
public class SongValidator {
    private ApiClient client;

    public SongValidator() {
        client = new ApiClient();
    }

    public boolean inDatabase(String singer, String song) {
        boolean found = false;
        String query = singer + " - " + song;
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        try {
            SearchResults search = client.search(params);
            int numItem = (int) search.getPagination().getItems();
            found = numItem > 0;
        } catch (ApiRequestException e) {
            e.printStackTrace();
        }
        return  found;
    }
}
