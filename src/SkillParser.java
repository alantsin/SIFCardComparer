public class SkillParser {
	
	private String activationCondition;
	
	private double activationConditionNumber;
	
	private double probability;
	
	private String activationResult;
	
	private double activationResultNumber;

	private String[] skillDetailsPart1;
	private String[] skillDetailsPart2;
	private String[] skillDetailsPart3;
	
	public SkillParser(String skillDetails) {
		
		skillDetailsPart1 = skillDetails.split(",");
		
		activationCondition = skillDetailsPart1[0];
		
		activationConditionNumber = Double.parseDouble(skillDetailsPart1[0].replaceAll("[\\D]", "")); 
		
		skillDetailsPart2 = skillDetailsPart1[1].split("%");
		
		probability = Double.parseDouble(skillDetailsPart2[0].replaceAll("[\\D]", "")) * 0.01; 
		
		skillDetailsPart3 = skillDetailsPart2[1].split("\\.");
		
		
		// Check for decimal numbers
		if (skillDetailsPart3[1].substring(0, 1).matches("\\d+")) {
			
			activationResult = skillDetailsPart3[0] + "." + skillDetailsPart3[1];
			
			activationResultNumber = Double.parseDouble(skillDetailsPart3[0].substring(skillDetailsPart3[0].length() - 1, skillDetailsPart3[0].length()) + 
														"." + skillDetailsPart3[1].substring(0, 1));
			
		}
		
		else {
			
			activationResult = skillDetailsPart3[0];
			
			activationResultNumber = Double.parseDouble(skillDetailsPart3[0].replaceAll("[\\D]", "")); 
			
		}
		
	}
	
	public String getActivationCondition() {
		return activationCondition;
	}

	public double getActivationConditionNumber() {
		return activationConditionNumber;
	}

	public double getProbability() {
		return probability;
	}

	public String getActivationResult() {
		return activationResult;
	}

	public double getActivationResultNumber() {
		return activationResultNumber;
	}


}
