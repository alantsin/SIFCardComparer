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
	
	private boolean isThereCard2 = false;
	
	private String finalAnswer;
	
	public CardComparison(UserInput userInput) throws IOException {
		
		// GET JSON data for cards
		GetCardJSON cardJSON = new GetCardJSON();
		
		// GET for Card 1
		JSONArray output1 = cardJSON.readJSONFromURL(userInput);
		Card card1 = new Card(output1);
		
		// GET for Card 2 if required
		if (userInput.getCard2ID() != "0" || userInput.getCard2ID() != userInput.getCard1ID()) {
			JSONArray output2 = cardJSON.readJSONFromURL(userInput);
			Card card2 = new Card(output2);
			isThereCard2 = true;
		}
		
		// Calculate strength of Card 1
		if (userInput.getCalculationMethod() == "Absolute") {
			AbsoluteCalculator absoluteCalculator1 = new AbsoluteCalculator();
		}
		else {
			AverageCalculator averageCalculator1 = new AverageCalculator();
		}
		
		// Calculate strength of Card 2 and compare if required
		if (isThereCard2 == true) {
			
			if (userInput.getCalculationMethod() == "Absolute") {
				AbsoluteCalculator absoluteCalculator2 = new AbsoluteCalculator();
			}
			else {
				AverageCalculator averageCalculator2 = new AverageCalculator();
			}
			
			CompareCards compare = new CompareCards();
			
		}
		
	}

	public String getFinalAnswer() {
		return finalAnswer;
	}

}
