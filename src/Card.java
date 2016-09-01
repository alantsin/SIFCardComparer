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
			id = Integer.toString(obj.getInt("id"));
			attribute = obj.getString("attribute");
			
			smileStatMax = obj.getInt("non_idolized_maximum_statistics_smile");
			pureStatMax  = obj.getInt("non_idolized_maximum_statistics_pure");
			coolStatMax  = obj.getInt("non_idolized_maximum_statistics_cool");
			
			smileStatIdolizedMax = obj.getInt("idolized_maximum_statistics_smile");
			pureStatIdolizedMax  = obj.getInt("idolized_maximum_statistics_pure");
			coolStatIdolizedMax  = obj.getInt("idolized_maximum_statistics_cool");
			
			rarity = obj.getString("rarity");
			
			if (rarity != "N") {
				skillDetails = obj.getString("skill_details");
				
				if (skillDetails.contains("score")) {
					skillType = "Score";
				}
				
				else if (skillDetails.contains("turning")) {
					skillType = "Perfect Lock";
				}
				
				else {
					skillType = "Healer";
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
