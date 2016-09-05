import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Card {
	
	private int cardNumber;
	
	private boolean special;
	
	private String name;
	private String year;
	private String mainUnit;
	private String subUnit;
	
	private String id;
	private String cardAttribute;
	
	private String rarity;
	private boolean promo;
	private String collection;
	
	private String baseStatType;
	private int baseStat;
	private int offStat;
	
	private int bondPoints;
	
	private String skillType;
	private String skillDetails;
	

	public Card(JSONArray cardJSON, UserInput userInput, int cardNumber) {
		JSONObject card;
		
		this.cardNumber = cardNumber;
		
		try {
			card = cardJSON.getJSONObject(0);
			
			this.special = card.getBoolean("is_special");
			
			if (this.special) {
				
				return;
				
			}
			
			this.rarity = card.getString("rarity");
			
			this.promo = card.getBoolean("is_promo");
			
			this.collection = card.getString("translated_collection");
			
			// GET idol information
			
			idolObject(card);
			
			// GET calculator information
			this.id = Integer.toString(card.getInt("id"));
			this.cardAttribute = card.getString("attribute");
			
			SkillDetails(card);
			
			// For card 1
			if (cardNumber == 1) {
			
				// If card 1 is idolized
				if (userInput.getCard1Idolized() || this.promo) {
					
					this.baseStat = baseStatIdolized(userInput, card);
					
					this.offStat = offStatIdolized(userInput, card);
					
					this.bondPoints = bondPoints();
					
				}
				
				// Else card 1 is not idolized
				else {
					
					this.baseStat = baseStatNotIdolized(userInput, card);
					
					this.offStat = offStatNotIdolized(userInput, card);
					
					this.bondPoints = bondPoints() / 2;
					
				}
				
			}
			
			// For card 2
			else {
				
				// If card 2 is idolized
				if (userInput.getCard2Idolized() || this.promo) {
					
					this.baseStat = baseStatIdolized(userInput, card);
					
					this.offStat = offStatIdolized(userInput, card);
					
					this.bondPoints = bondPoints();
					
				}
				
				// Else card 2 is not idolized
				else {
					
					this.baseStat = baseStatNotIdolized(userInput, card);
					
					this.offStat = offStatNotIdolized(userInput, card);
					
					this.bondPoints = bondPoints() / 2;
				}
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SkillDetails(JSONObject card) throws JSONException {
		if (!"N".equals(this.rarity)) {
			this.skillDetails = card.getString("skill_details");
			
			if (this.skillDetails.contains("score")) {
				this.skillType = "Score";
			}
			
			else if (this.skillDetails.contains("turning")) {
				this.skillType = "Perfect Lock";
			}
			
			else {
				this.skillType = "Healer";
			}
		}
		
		else {
			this.skillType = "";
		}
	}

	private void idolObject(JSONObject card) throws JSONException {
		
		JSONObject idol = card.getJSONObject("idol");
		this.name = idol.getString("name");
		this.year = idol.getString("year");
		
		if (!"N".equals(this.rarity)) {
			
		this.mainUnit = idol.getString("main_unit");
		this.subUnit = idol.getString("sub_unit");
		
		}
	}

	private int baseStatIdolized(UserInput userInput, JSONObject obj) throws JSONException {
		// GET base stat
		if ("Smile".equals(userInput.getAttribute())) {
			this.baseStatType = "Smile";
			return obj.getInt("idolized_maximum_statistics_smile");
		}
		
		else if ("Pure".equals(userInput.getAttribute())) {
			this.baseStatType = "Pure";
			return obj.getInt("idolized_maximum_statistics_pure");
		}
		
		else if ("Cool".equals(userInput.getAttribute())) {
			this.baseStatType = "Cool";
			return obj.getInt("idolized_maximum_statistics_cool");
		}
		
		else {
			return 0;
		}
	}

	private int baseStatNotIdolized(UserInput userInput, JSONObject obj) throws JSONException {
		// GET not idolized base stats
		if ("Smile".equals(userInput.getAttribute())) {
			this.baseStatType = "Smile";
			return obj.getInt("non_idolized_maximum_statistics_smile");
		}
		
		else if ("Pure".equals(userInput.getAttribute())) {
			this.baseStatType = "Pure";
			return obj.getInt("non_idolized_maximum_statistics_pure");
		}
		
		else if ("Cool".equals(userInput.getAttribute())) {
			this.baseStatType = "Cool";
			return obj.getInt("non_idolized_maximum_statistics_cool");
		}
		
		else {
			return 0;
		}
	}
	
	private int offStatIdolized(UserInput userInput, JSONObject card) throws JSONException {
		if (userInput.getCenterSkill().contains("12% Smile")) {
			
			if (userInput.getCenterSkill().contains("Pure")) {
				
				return card.getInt("idolized_maximum_statistics_pure");
				
			}
			
			else if (userInput.getCenterSkill().contains("Cool")) {
				
				return card.getInt("idolized_maximum_statistics_cool");
				
			}
			
			else {
				// Set Off Stat to equal Base Stat
				return this.baseStat;
				
			}
			
		}
		
		else if (userInput.getCenterSkill().contains("12% Pure")) {
			
			if (userInput.getCenterSkill().contains("Smile")) {
				
				return card.getInt("idolized_maximum_statistics_smile");
				
			}
			
			else if (userInput.getCenterSkill().contains("Cool")) {
				
				return card.getInt("idolized_maximum_statistics_cool");
				
			}
			
			else {
				// Set Off Stat to equal Base Stat
				return this.baseStat;
				
			}
			
		}
		
		else if (userInput.getCenterSkill().contains("12% Cool")) {
			
			if (userInput.getCenterSkill().contains("Smile")) {
				
				return card.getInt("idolized_maximum_statistics_smile");
				
			}
			
			else if (userInput.getCenterSkill().contains("Pure")) {
				
				return card.getInt("idolized_maximum_statistics_pure");
				
			}
			
			else {
				// Set Off Stat to equal Base Stat
				return this.baseStat;
				
			}
			
		}
		
		else {
			// Set Off Stat to equal Base Stat
			return this.baseStat;
			
		}
	}
	
	private int offStatNotIdolized(UserInput userInput, JSONObject card) throws JSONException {
		if (userInput.getCenterSkill().contains("12% Smile")) {
			
			if (userInput.getCenterSkill().contains("Pure")) {
				
				return card.getInt("non_idolized_maximum_statistics_pure");
				
			}
			
			else if (userInput.getCenterSkill().contains("Cool")) {
				
				return card.getInt("non_idolized_maximum_statistics_cool");
				
			}
			
			else {
				// Set Off Stat to equal Base Stat
				return this.baseStat;
				
			}
			
		}
		
		else if (userInput.getCenterSkill().contains("12% Pure")) {
			
			if (userInput.getCenterSkill().contains("Smile")) {
				
				return card.getInt("non_idolized_maximum_statistics_smile");
				
			}
			
			else if (userInput.getCenterSkill().contains("Cool")) {
				
				return card.getInt("non_idolized_maximum_statistics_cool");
				
			}
			
			else {
				// Set Off Stat to equal Base Stat
				return this.baseStat;
				
			}
			
		}
		
		else if (userInput.getCenterSkill().contains("12% Cool")) {
			
			if (userInput.getCenterSkill().contains("Smile")) {
				
				return card.getInt("non_idolized_maximum_statistics_smile");
				
			}
			
			else if (userInput.getCenterSkill().contains("Pure")) {
				
				return card.getInt("non_idolized_maximum_statistics_pure");
				
			}
			
			else {
				// Set Off Stat to equal Base Stat
				return this.baseStat;
				
			}
			
		}
		
		else {
			// Set Off Stat to equal Base Stat
			return this.baseStat;
			
		}
	}

	private int bondPoints() {
		// GET bond points
		if ("N".equals(this.rarity)) {
			
			return 50;
			
		}
		
		else if ("R".equals(this.rarity)) {
			
			return 200;
			
		}
		
		else if ("SR".equals(this.rarity)) {
			
			return 500;
			
		}
		
		else if ("SSR".equals(this.rarity)) {
			
			return 750;
			
		}
		
		else if ("UR".equals(this.rarity)) {
			
			return 1000;
			
		}
		
		
		else {
			
			return 0;
			
		}
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public boolean getSpecial() {
		return special;
	}
	
	public String getName() {
		return name;
	}

	public String getYear() {
		return year;
	}

	public String getMainUnit() {
		return mainUnit;
	}

	public String getSubUnit() {
		return subUnit;
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
	
	public boolean getPromo() {
		return promo;
	}
	
	public String getCollection() {
		return collection;
	}
	
	public String getBaseStatType() {
		return baseStatType;
	}

	public int getBaseStat() {
		return baseStat;
	}
	
	public int getOffStat() {
		return offStat;
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
