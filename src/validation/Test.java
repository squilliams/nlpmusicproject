package validation;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

public class Test {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		SongValidator sv = new SongValidator();
		System.out.println(sv.inDatabase("Ken Ishii", "Pneuma"));
	}
}