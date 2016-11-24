package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.discogs.api.ApiClient;
import com.discogs.api.exceptions.ApiRequestException;
import com.discogs.api.model.SearchResults;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Test {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws ClientProtocolException, IOException {
		ApiClient client = new ApiClient();
		try {
			//Artist artist = client.getArtist(1);
			//Release release = client.getRelease("1");
			//Master master = client.getMaster("8471");
			//Label label = client.getLabel("Planet E");
			Map<String, String> params = new HashMap<String, String>();
			params.put("q", "Ken Ishii - Pneuma");
			//params.put("type", "release");
			long start = System.nanoTime();
			SearchResults search = client.search(params);
			long elapsedTime = System.nanoTime() - start;
			System.out.println(elapsedTime);
			//Listing listing = client.getListing("41578241");
			//Order order = client.getOrder()
			//System.out.println(listing.getRelease());

			//PriceBean fee = client.getFee(10.2333F, null);
			//User user = client.getProfile("rihanna");
			System.out.println(search);
		} catch (ApiRequestException e) {
			e.printStackTrace();
		}
	}
}