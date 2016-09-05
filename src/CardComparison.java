import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class CardComparison {
	
	private boolean over = false;
	
	private boolean special = false;
	
	private Card card1;
	private Card card2;
	
	private AbsoluteCalculator card1Absolute;
	private AbsoluteCalculator card2Absolute;
	private AverageCalculator  card1Average;
	private AverageCalculator  card2Average;
	
	public CardComparison(UserInput userInput) throws IOException {
		
		// Check if Card IDs are valid
		try {
			// GET total number of cards
			GetCardJSON cardJSONTotal = new GetCardJSON();
			
			JSONArray cardTotalArray = cardJSONTotal.readJSONFromURL("");
			JSONObject cardTotalObject = cardTotalArray.getJSONObject(0);
			
			if (Integer.parseInt(userInput.getCard1ID()) > cardTotalObject.getInt("count") ||
				Integer.parseInt(userInput.getCard2ID()) > cardTotalObject.getInt("count")) {
				
				over = true;
				
				return;
				
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// GET JSON data for cards
		GetCardJSON cardJSON = new GetCardJSON();
		
		// GET for Card 1
		JSONArray output1 = cardJSON.readJSONFromURL(userInput.getCard1ID());
		card1 = new Card(output1, userInput, 1);
		
		// GET for Card 2 if required
		if (!"0".equals(userInput.getCard2ID()) && !userInput.getCard2ID().equals(userInput.getCard1ID())) {
			JSONArray output2 = cardJSON.readJSONFromURL(userInput.getCard2ID());
			card2 = new Card(output2, userInput, 2);
		}
		
		// Else only one card
		else {
			card2 = card1;
		}
		
		// Check if Card is special
		if (card1.getSpecial() || card2.getSpecial()) {
			special = true;
			return;
		}
		
		// Calculate Base Stats of Card 1
		BaseStatsCalculator base1 = new BaseStatsCalculator(card1, userInput);
		
		// Calculate strength of Card 1
		if ("Absolute".equals(userInput.getCalculationMethod())) {
			card1Absolute = new AbsoluteCalculator(card1, userInput, base1.getFinalBaseStats());
			CardComparisonWindow.info1 = card1Absolute.getInfo();
		}
		else {
			card1Average = new AverageCalculator(card1, userInput, base1.getFinalBaseStats());
			CardComparisonWindow.info1 = card1Average.getInfo();
		}
		
		// If card 2 exists
		if (card1 != card2) {
			
			// Calculate Base Stats of Card 2
			BaseStatsCalculator base2 = new BaseStatsCalculator(card2, userInput);
			
			// Calculate strength of Card 2
			
			if ("Absolute".equals(userInput.getCalculationMethod())) {
				card2Absolute = new AbsoluteCalculator(card2, userInput, base2.getFinalBaseStats());
				CardComparisonWindow.info2 = card2Absolute.getInfo();
			}
			
			else {
				card2Average = new AverageCalculator(card2, userInput, base2.getFinalBaseStats());
				CardComparisonWindow.info2 = card2Average.getInfo();
			}
			
			
		}
		
		// Else no card 2
		else {
			CardComparisonWindow.info2 = CardComparisonWindow.info1;
		}
			
	}
		
	public boolean getOver() {
		return over;
	}
	
	public boolean getSpecial() {
		return special;
	}

	public Card getCard1() {
		return card1;
	}

	public Card getCard2() {
		return card2;
	}

}
