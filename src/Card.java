import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Card {
	
	private String id;
	private String attribute;
	
	private int smileStatMax;
	private int pureStatMax;
	private int coolStatMax;
	
	private int smileStatIdolizedMax;
	private int pureStatIdolizedMax;
	private int coolStatIdolizedMax;
	
	private String rarity;
	
	private String skillType;
	private String skillDetails;
	
	public Card(JSONArray cardJSON) {
		JSONObject obj;
		try {
			obj = cardJSON.getJSONObject(0);
			this.id = Integer.toString(obj.getInt("id"));
			this.attribute = obj.getString("attribute");
			
			this.smileStatMax = obj.getInt("non_idolized_maximum_statistics_smile");
			this.pureStatMax  = obj.getInt("non_idolized_maximum_statistics_pure");
			this.coolStatMax  = obj.getInt("non_idolized_maximum_statistics_cool");
			
			this.smileStatIdolizedMax = obj.getInt("idolized_maximum_statistics_smile");
			this.pureStatIdolizedMax  = obj.getInt("idolized_maximum_statistics_pure");
			this.coolStatIdolizedMax  = obj.getInt("idolized_maximum_statistics_cool");
			
			this.rarity = obj.getString("rarity");
			
			if (this.rarity != "N") {
				this.skillDetails = obj.getString("skill_details");
				
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
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getID() {
		return id;
	}	

	public String getAttribute() {
		return attribute;
	}

	public int getSmileStatMax() {
		return smileStatMax;
	}

	public int getPureStatMax() {
		return pureStatMax;
	}

	public int getCoolStatMax() {
		return coolStatMax;
	}

	public int getSmileStatIdolizedMax() {
		return smileStatIdolizedMax;
	}

	public int getPureStatIdolizedMax() {
		return pureStatIdolizedMax;
	}

	public int getCoolStatIdolizedMax() {
		return coolStatIdolizedMax;
	}

	public String getRarity() {
		return rarity;
	}

	public String getSkillType() {
		return skillType;
	}

	public String getSkillDetails() {
		return skillDetails;
	}
	
}
