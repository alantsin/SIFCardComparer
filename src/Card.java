import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Card {
	
	private String id;
	private String cardAttribute;
	
	private String rarity;
	
	private int baseStat;
	
	private int bondPoints;
	
	private String skillType;
	private String skillDetails;
	
	public Card(JSONArray cardJSON, UserInput userInput, int cardNumber) {
		JSONObject obj;
		try {
			obj = cardJSON.getJSONObject(0);
			this.id = Integer.toString(obj.getInt("id"));
			this.cardAttribute = obj.getString("attribute");
			
			this.rarity = obj.getString("rarity");
			
			this.skillType = skillType(obj);
			
			// For card 1
			if (cardNumber == 1) {
			
				// If card 1 is idolized
				if (userInput.getCard1Idolized()) {
					
					this.baseStat = baseStatIdolized(obj);
					
					this.bondPoints = bondPoints();
					
				}
				
				// Else card 1 is not idolized
				else {
					
					this.baseStat = baseStatNotIdolized(obj);
					
					this.bondPoints = bondPoints() / 2;
					
				}
				
			}
			
			// For card 2
			else {
				
				// If card 2 is idolized
				if (userInput.getCard2Idolized()) {
					
					this.baseStat = baseStatIdolized(obj);
					
					this.bondPoints = bondPoints();
					
				}
				
				// Else card 2 is not idolized
				else {
					
					this.baseStat = baseStatNotIdolized(obj);
					this.bondPoints = bondPoints() / 2;
				}
				
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int baseStatIdolized(JSONObject obj) throws JSONException {
		// GET base stat
		if (this.cardAttribute == "Smile") {
			return obj.getInt("idolized_maximum_statistics_smile");
		}
		
		else if (this.cardAttribute == "Pure") {
			return obj.getInt("idolized_maximum_statistics_pure");
		}
		
		else if (this.cardAttribute == "Cool") {
			return obj.getInt("idolized_maximum_statistics_cool");
		}
		
		else {
			return 0;
		}
	}

	private int baseStatNotIdolized(JSONObject obj) throws JSONException {
		// GET not idolized base stats
		if (this.cardAttribute == "Smile") {
			return obj.getInt("non_idolized_maximum_statistics_smile");
		}
		
		else if (this.cardAttribute == "Pure") {
			return obj.getInt("non_idolized_maximum_statistics_pure");
		}
		
		else if (this.cardAttribute == "Cool") {
			return obj.getInt("non_idolized_maximum_statistics_cool");
		}
		
		else {
			return 0;
		}
	}

	private int bondPoints() {
		// GET bond points
		if (this.rarity == "N") {
			
			return 50;
			
		}
		
		else if (this.rarity == "R") {
			
			return 200;
			
		}
		
		else if (this.rarity == "SR") {
			
			return 500;
			
		}
		
		else if (this.rarity == "SSR") {
			
			return 750;
			
		}
		
		else if (this.rarity == "UR") {
			
			return 1000;
			
		}
		
		
		else {
			
			return 0;
			
		}
	}

	private String skillType(JSONObject obj) throws JSONException {
		if (this.rarity != "N") {
			this.skillDetails = obj.getString("skill_details");
			
			if (this.skillDetails.contains("score")) {
				return "Score";
			}
			
			else if (this.skillDetails.contains("turning")) {
				return "Perfect Lock";
			}
			
			else {
				return "Healer";
			}
		}
		
		else {
			return "";
		}
	}

	public String getId() {
		return id;
	}

	public String getCardAttribute() {
		return cardAttribute;
	}

	public String getRarity() {
		return rarity;
	}

	public int getBaseStat() {
		return baseStat;
	}

	public int getBondPoints() {
		return bondPoints;
	}

	public String getSkillType() {
		return skillType;
	}

	public String getSkillDetails() {
		return skillDetails;
	}
	
	
}
