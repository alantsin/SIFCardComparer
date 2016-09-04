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
	
	private String finalAnswer;

	private boolean isThereCard2 = false;
	
	private Card card1;
	private Card card2;
	
	public CardComparison(UserInput userInput) throws IOException {
		
		// GET JSON data for cards
		GetCardJSON cardJSON = new GetCardJSON();
		
		// GET for Card 1
		JSONArray output1 = cardJSON.readJSONFromURL(userInput.getCard1ID());
		card1 = new Card(output1, userInput, 1);
		
		// Calculate Base Stats of Card 1
		BaseStatsCalculator base1 = new BaseStatsCalculator(card1, userInput);
		
		
		// Calculate strength of Card 1
		if ("Absolute".equals(userInput.getCalculationMethod())) {
			
			AbsoluteCalculator absoluteCalculator1 = new AbsoluteCalculator(card1, userInput, base1.getFinalBaseStats());
			
			
		}
		
		else {
			
			AverageCalculator averageCalculator1 = new AverageCalculator(card1, userInput);
			
		}
		
		// GET for Card 2 if required
		if (!"0".equals(userInput.getCard2ID()) && !userInput.getCard2ID().equals(userInput.getCard1ID())) {
			JSONArray output2 = cardJSON.readJSONFromURL(userInput.getCard2ID());
			card2 = new Card(output2, userInput, 2);
			
			isThereCard2 = true;
			
			// Calculate Base Stats of Card 2
			BaseStatsCalculator base2 = new BaseStatsCalculator(card2, userInput);
			
			// Calculate strength of Card 2
				
			if ("Absolute".equals(userInput.getCalculationMethod())) {
				
				AbsoluteCalculator absoluteCalculator2 = new AbsoluteCalculator(card2, userInput, base2.getFinalBaseStats());
				
			}
			
			else {
				
				AverageCalculator averageCalculator2 = new AverageCalculator(card2, userInput);
				
			}
				
		}
		
		// Else only one card
		else {
			
			card2 = card1;
			
		}
		
		
		if (isThereCard2) {
			
			CompareCards compare = new CompareCards();
			
		}

		
	}
	
	public String getFinalAnswer() {
		return finalAnswer;
	}

}
