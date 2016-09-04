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
		System.out.println(activationCondition);
		
		activationConditionNumber = Double.parseDouble(skillDetailsPart1[0].replaceAll("[\\D]", "")); 
		System.out.println(Double.toString(activationConditionNumber));
		
		skillDetailsPart2 = skillDetailsPart1[1].split("%");
		
		probability = Double.parseDouble(skillDetailsPart2[0].replaceAll("[\\D]", "")) * 0.01; 
		System.out.println(Double.toString(probability));
		
		skillDetailsPart3 = skillDetailsPart2[1].split("\\.");
		
		activationResult = skillDetailsPart3[0];
		activationResultNumber = Double.parseDouble(skillDetailsPart3[0].replaceAll("[\\D]", "")); 
		System.out.println(Double.toString(activationResultNumber));
		
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
