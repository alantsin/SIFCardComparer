public class AbsoluteCalculator {
	
	private int noteCount;
	
	private double perfectPercentage = 1.00;
	
	private double perfectHit = 1.00;
	
//	Not needed for Absolute
//	private double greatHit = 0.88;
	
	private double comboMultiplier[] = {1.00, 1.10, 1.15, 1.20, 1.25, 1.30, 1.35};
	
//  Assume no hold notes
	private double noteType = 1.00;
	
	private double attributeMultiplier = 1.00;
	
	private double groupMultiplier = 1.00;
	
	private double scoreContribution;
	
	private int skillFrequency;
	
	private double skillContribution;
	
	private int finalScore;
	
	private FinalInformation info;
	
	public AbsoluteCalculator(Card card, UserInput userInput, int finalBaseStats) {
		
		if (userInput.getAttribute().equals(card.getCardAttribute())) {
			
			attributeMultiplier = 1.10;
			
		}
		
		if (!"N".equals(card.getRarity())) {
		
			if (userInput.getSong().equals(card.getMainUnit())) {
			
				groupMultiplier = 1.10;
			
			}
		
		}
		
		scoreContribution = scoreContribution(userInput, finalBaseStats);
		System.out.println(Double.toString(scoreContribution));
		
		
		skillContribution(card, userInput);
		System.out.println(Double.toString(skillContribution));
		
	}

	private void skillContribution(Card card, UserInput userInput) {
		
		if (!"N".equals(card.getRarity())) {
			
			SkillParser skillParser = new SkillParser(card.getSkillDetails());
			
			skillFrequency = skillFrequency(userInput, skillParser);
				
				// Check if SIS selected
				if (!"None".equals(userInput.getCard1SIS())) {
					
					// Check promo status and rarity
					if (!card.getPromo() && ("UR".equals(card.getRarity()) || "SR".equals(card.getRarity()))) {
						
						skillContribution = skillCalculatorSIS(skillParser, userInput, card.getCardNumber());
						
					}
					
					else {
						
						// Card too low rarity to consider SIS, calculate normally
						System.out.println("Low rarity");
						skillContribution = skillCalculator(skillParser);
						
					}
					
				}
			
		}
		
	}
	
	private double skillCalculator(SkillParser skillParser) {
		
		if (skillParser.getActivationResult().contains("score")) {
		
			return skillFrequency * skillParser.getActivationResultNumber();
			
		}
		
		else {
			
			return 0;
			
		}
		
	}
	
	private double skillCalculatorSIS(SkillParser skillParser, UserInput userInput, int cardNumber) {
		
		if (cardNumber == 1) {
			
			// 250% Charm
			if (userInput.getCard1SIS().contains("250%") && skillParser.getActivationResult().contains("score")) {
					
				return skillFrequency * (skillParser.getActivationResultNumber() * 2.5);
					
				
			}
			
			else if (userInput.getCard1SIS().contains("270x") && skillParser.getActivationResult().contains("HP")) {
					
				return skillFrequency * (skillParser.getActivationResultNumber() * 270);

				
			}
			
			else if (userInput.getCard1SIS().contains("25%") && skillParser.getActivationResult().contains("seconds")) {
					
				double boostFrequency = Math.min(1, (skillFrequency * skillParser.getActivationResultNumber()) / userInput.getTime());
					
				return ((scoreContribution * boostFrequency * 1.25) + 
						(scoreContribution * (1 - boostFrequency))) - 
						 scoreContribution;
					
					
			}
				
			else {
					
				return 0;
					
			}
		}
		
		// Else card 2
		else {
			
			// 250% Charm
			if (userInput.getCard2SIS().contains("250%") && skillParser.getActivationResult().contains("score")) {
					
				return skillFrequency * (skillParser.getActivationResultNumber() * 2.5);
					
				
			}
			
			else if (userInput.getCard2SIS().contains("270x") && skillParser.getActivationResult().contains("HP")) {
					
				return skillFrequency * (skillParser.getActivationResultNumber() * 270);

				
			}
			
			else if (userInput.getCard2SIS().contains("25%") && skillParser.getActivationResult().contains("seconds")) {
					
				double boostFrequency = Math.min(1, (skillFrequency * skillParser.getActivationResultNumber()) / userInput.getTime());
					
				return ((scoreContribution * boostFrequency * 1.25) + 
						(scoreContribution * (1 - boostFrequency))) - 
						 scoreContribution;
					
					
			}
				
			else {
					
				return 0;
					
			}
			
		}
		
	}

	private int skillFrequency(UserInput userInput, SkillParser skillParser) {
		
		// Time-based skills
		if (skillParser.getActivationCondition().contains("seconds")) {
		
			return Math.floorDiv(userInput.getTime(), (int)skillParser.getActivationConditionNumber());
			
		}
		
		// Perfect-based skills
		else if (skillParser.getActivationCondition().contains("perfects")) {
			
			return Math.floorDiv(userInput.getNoteCount(), (int)skillParser.getActivationConditionNumber());
			
		}
		
		// Note-based or combo-based skills
		else {
			
			return Math.floorDiv(userInput.getNoteCount(), (int)skillParser.getActivationConditionNumber());
			
		}
	}

	private double scoreContribution(UserInput userInput, int finalBaseStats) {
		noteCount = userInput.getNoteCount();
		
		
		if (noteCount > 800) {
			
			return CalculateOver800(finalBaseStats);
			
		}
		
		else if (noteCount <= 800 && noteCount > 600) {
			
			return Calculate600To800(finalBaseStats);
			
		}
		
		else if (noteCount <= 600 && noteCount > 400) {
			
			return Calculate400To600(finalBaseStats);
			
		}
		
		else if (noteCount <= 400 && noteCount > 200) {
			
			return Calculate200To400(finalBaseStats);
			
		}
		
		else if (noteCount <= 200 && noteCount > 100) {
			
			return Calculate100To200(finalBaseStats);
			
		}
		
		else if (noteCount <= 100 && noteCount > 50) {
			
			return Calculate50To100(finalBaseStats);
			
		}
		
		else {
			
			return Calculate0To50(finalBaseStats);
			
		}
	}
	
	private double CalculateOver800(int finalBaseStats) {
		
		int remainder = noteCount - 800;
		noteCount = noteCount - remainder;
		
		return Calculate600To800(finalBaseStats) + (remainder * Math.floor(finalBaseStats * 0.0125 * perfectHit 
																								   * comboMultiplier[6] 
																								   * noteType 
																								   * attributeMultiplier 
																								   * groupMultiplier));
		
	}
	
	private double Calculate600To800(int finalBaseStats) {
		
		int remainder = noteCount - 600;
		noteCount = noteCount - remainder;
		
		return Calculate400To600(finalBaseStats) + (remainder * Math.floor(finalBaseStats * 0.0125 * perfectHit 
																					 			   * comboMultiplier[5] 
																					 			   * noteType 
																					 			   * attributeMultiplier 
																					 			   * groupMultiplier));
		
	}
	
	private double Calculate400To600(int finalBaseStats) {
		
		int remainder = noteCount - 400;
		noteCount = noteCount - remainder;
		
		return Calculate200To400(finalBaseStats) + (remainder * Math.floor(finalBaseStats * 0.0125 * perfectHit 
																					 			   * comboMultiplier[4] 
																					 			   * noteType 
																					 			   * attributeMultiplier 
																					 			   * groupMultiplier));
		
	}
	
	private double Calculate200To400(int finalBaseStats) {
		
		int remainder = noteCount - 200;
		noteCount = noteCount - remainder;
		
		return Calculate100To200(finalBaseStats) + (remainder * Math.floor(finalBaseStats * 0.0125 * perfectHit 
																					 			   * comboMultiplier[3] 
																					 			   * noteType 
																					 			   * attributeMultiplier 
																					 			   * groupMultiplier));
		
	}
	
	private double Calculate100To200(int finalBaseStats) {
		
		int remainder = noteCount - 100;
		noteCount = noteCount - remainder;
		
		return Calculate50To100(finalBaseStats) + (remainder * Math.floor(finalBaseStats * 0.0125 * perfectHit 
																					     		  * comboMultiplier[2] 
																					     		  * noteType 
																					     		  * attributeMultiplier 
																					     		  * groupMultiplier));
		
	}
	
	private double Calculate50To100(int finalBaseStats) {
		
		int remainder = noteCount - 50;
		noteCount = noteCount - remainder;
		
		return Calculate0To50(finalBaseStats) + (remainder * Math.floor(finalBaseStats * 0.0125 * perfectHit 
																					   * comboMultiplier[1] 
																				       * noteType 
																				       * attributeMultiplier 
																				   	   * groupMultiplier));
		
	}
	
	private double Calculate0To50(int finalBaseStats) {
		
		return noteCount * Math.floor(finalBaseStats * 0.0125 * perfectHit 
															  * comboMultiplier[0] 
											 				  * noteType 
															  * attributeMultiplier 
															  * groupMultiplier);
		
	}

	public FinalInformation getInfo() {
		return info;
	}
	
}
