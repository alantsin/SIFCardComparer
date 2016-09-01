import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CardComparison {
	
	// The two cards to compare; TODO: UI for selecting cards
	public static String card1ID = "30";
	public static String card2ID = "31";

	public static void main(String[] args) throws IOException {
		
		// GET JSON for both cards
		GetCardJSON cardJSON = new GetCardJSON();
		
		JSONArray output1 = cardJSON.readJSONFromURL(card1ID);
		JSONArray output2 = cardJSON.readJSONFromURL(card2ID);
		
		// Assign values to card variables
		Card card1 = new Card(output1);
		Card card2 = new Card(output2);
		
		// Compare cards
		CompareCards compare = new CompareCards();
		String result = compare.Compare(card1, card2);
		
		// Output answer
		System.out.println(result + " is the stronger card.");
	}

}
