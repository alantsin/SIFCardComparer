import org.json.JSONArray;
import org.json.JSONException;

import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetCardJSON {

	public String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

	public JSONArray readJSONFromURL(UserInput userInput) throws IOException {
		// Use School Idol Tomodachi's API to get card data
		String baseURL = "http://schoolido.lu/api/cards/";
		String URL = baseURL + userInput.getCard1ID();
        InputStream is = new URL(URL).openStream();
        JSONArray json = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = "[ " + readAll(rd) + " ]";
            json = new JSONArray(jsonText);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return json;
    }
}

