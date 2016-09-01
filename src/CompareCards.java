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

public class CompareCards {
	public String Compare(Card card1, Card card2) {
		// TODO: Assign attribute from UI
		String attribute = "Smile";
		
		// Difference in points, maybe used in further calculations
		int difference = CompareBaseStats(card1, card2, attribute);
		
		// Simple comparison TODO: Complex comparison
		if (card1.getSmileStatIdolizedMax() > card2.getSmileStatIdolizedMax()) {
			return "card1";
		}
		else {
			return "card2";
		}
	}
	
	// Compares base stats and calculates the difference.
	private int CompareBaseStats(Card card1, Card card2, String attribute) {
		if (attribute == "Smile") {
			if (card1.getSmileStatIdolizedMax() > card2.getSmileStatIdolizedMax()) {
				int difference = card1.getSmileStatIdolizedMax() - card2.getSmileStatIdolizedMax();
				System.out.println("Card ID #" + card1.getID() + " has " + Integer.toString(difference) + " more " + attribute + 
									" points than Card ID #" + card2.getID());
				return difference;
			}
			else {
				int difference = card2.getSmileStatIdolizedMax() - card1.getSmileStatIdolizedMax();
				System.out.println("Card ID #" + card1.getID() + " has " + Integer.toString(difference) + " more " + attribute + 
						" points than Card ID #" + card2.getID());				
				return difference;
			}
		}
		
		else if (attribute == "Pure") {
			if (card1.getPureStatIdolizedMax() > card2.getPureStatIdolizedMax()) {
				int difference = card1.getPureStatIdolizedMax() - card2.getPureStatIdolizedMax();
				System.out.println("Card ID #" + card1.getID() + " has " + Integer.toString(difference) + " more " + attribute + 
						" points than Card ID #" + card2.getID());
				return difference;
			}
			else {
				int difference = card2.getPureStatIdolizedMax() - card1.getPureStatIdolizedMax();
				System.out.println("Card ID #" + card1.getID() + " has " + Integer.toString(difference) + " more " + attribute + 
						" points than Card ID #" + card2.getID());				
				return difference;
			}
		}
		
		else if (attribute == "Cool") {
			if (card1.getCoolStatIdolizedMax() > card2.getCoolStatIdolizedMax()) {
				int difference = card1.getCoolStatIdolizedMax() - card2.getCoolStatIdolizedMax();
				System.out.println("Card ID #" + card1.getID() + " has " + Integer.toString(difference) + " more " + attribute + 
						" points than Card ID #" + card2.getID());
				return difference;
			}
			else {
				int difference = card2.getSmileStatIdolizedMax() - card1.getSmileStatIdolizedMax();
				System.out.println("Card ID #" + card1.getID() + " has " + Integer.toString(difference) + " more " + attribute + 
						" points than Card ID #" + card2.getID());				
				return difference;
			}
		}
		
		else {
			System.out.println("Error, no attribute");
			System.exit(0);
		}
		
		return 0;

	}
	
	private int CompareSkillBonus(Card card1, Card card2) {
		return 0;
	}
}
