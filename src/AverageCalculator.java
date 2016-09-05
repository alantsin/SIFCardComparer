public class AverageCalculator {

private int noteCount;
	
	private double perfectPercentage;
	
	private double perfectHit = 1.00;
	
	private double greatHit = 0.88;
	
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
	
	public AverageCalculator(Card card, UserInput userInput, int finalBaseStats) {
		
		perfectPercentage = userInput.getPerfectPercent();
		
		if (userInput.getAttribute().equals(card.getCardAttribute())) {		
			attributeMultiplier = 1.10;		
		}
		
		if (!"N".equals(card.getRarity())) {
		
			if (userInput.getSong().equals(card.getMainUnit())) {	
				groupMultiplier = 1.10;			
			}
		
		}
		
		scoreContribution = scoreContribution(userInput, finalBaseStats);
		
		skillContribution(card, userInput);
		
		finalScore = (int)(scoreContribution + skillContribution);
		
		info = new FinalInformation();
		
		info.setFinalBaseStats(Integer.toString(finalBaseStats));
		info.setScoreContribution(Integer.toString((int)scoreContribution));
		info.setSkillContribution(Integer.toString((int)skillContribution));
		info.setFinalScore(Integer.toString(finalScore));
		
	}
	
	private double starNoteCalculator(Card card, UserInput userInput, int cardNumber) {
		
		// If card 1
		if (cardNumber == 1) {
			
			// Check for SIS
			if (userInput.getCard1SIS().contains("250%")) {
				return Math.round(userInput.getPerfectPercent() * userInput.getStarNotes()) * 120 * 2.5;
			}
			
			else {	
				return Math.round(userInput.getPerfectPercent() * userInput.getStarNotes()) * 120;
			}
			
		}
		
		// Else card 2
		else {
			
			// Check for SIS
			if (userInput.getCard2SIS().contains("250%")) {
				return Math.round(userInput.getPerfectPercent() * userInput.getStarNotes()) * 120 * 2.5;
			}
			
			else {	
				return Math.round(userInput.getPerfectPercent() * userInput.getStarNotes()) * 120;
			}			
			
		}
		
	}

	private void skillContribution(Card card, UserInput userInput) {
		
		// If Star-note Umi
		if (Integer.parseInt(userInput.getCard1ID()) == 206) {
			
			skillContribution = starNoteCalculator(card, userInput, 1);
			return;
			
		}
		
		else if (Integer.parseInt(userInput.getCard2ID()) == 206) {
			
			skillContribution = starNoteCalculator(card, userInput, 2);
			return;
			
		}
		
		if (!"N".equals(card.getRarity())) {
			
			SkillParser skillParser = new SkillParser(card.getSkillDetails());
			
			skillFrequency = skillFrequency(userInput, skillParser);
			
			// If card 1
			if (card.getCardNumber() == 1) {				
				skillCalculatorCard1(card, userInput, skillParser);
			}
			
			// Else card 2
			else {				
				skillCalculatorCard2(card, userInput, skillParser);				
			}
			
		}
		
		// Else N card with no skill
		else {			
			skillContribution = 0;			
		}
		
	}

	private void skillCalculatorCard2(Card card, UserInput userInput, SkillParser skillParser) {
		// Check if SIS selected
		if (!"None".equals(userInput.getCard2SIS())) {
			
			// Check promo status and rarity
			if (!card.getPromo()) {
				
				if ("UR".equals(card.getRarity()) 								   || 
					"SR".equals(card.getRarity()) && userInput.getCard2Idolized()  ||
					"SSR".equals(card.getRarity()) && userInput.getCard2Idolized())   {
					
					skillContribution = skillCalculatorSIS(skillParser, userInput, card.getCardNumber());
					
				}
			
				else {
					// Card too low rarity to consider SIS, calculate normally
					skillContribution = skillCalculator(skillParser);
				}
				
			}
			
			else {
				// Card too low rarity to consider SIS, calculate normally
				skillContribution = skillCalculator(skillParser);
			}
			
		}
		
		// Else no SIS, calculate normally
		else {
			skillContribution = skillCalculator(skillParser);
		}
		
	}

	private void skillCalculatorCard1(Card card, UserInput userInput, SkillParser skillParser) {
		// Check if SIS selected
		if (!userInput.getCard1SIS().contains("None")) {
			
			// Check promo status and rarity
			if (!card.getPromo()) {
				
				if ("UR".equals(card.getRarity()) 								    || 
					"SR".equals(card.getRarity()) && userInput.getCard1Idolized()   ||
					"SSR".equals(card.getRarity()) && userInput.getCard1Idolized())   {
					
					skillContribution = skillCalculatorSIS(skillParser, userInput, card.getCardNumber());
					
				}
			
				else {
					// Card too low rarity to consider SIS, calculate normally
					System.out.println("Low rarity");
					skillContribution = skillCalculator(skillParser);
				}
				
			}
			
			else {
				// Card too low rarity to consider SIS, calculate normally
				System.out.println("Low rarity");
				skillContribution = skillCalculator(skillParser);
			}
			
		}
		
		// Else no SIS, calculate normally
		else {
			System.out.println("No SIS");
			skillContribution = skillCalculator(skillParser);
		}
	}
	
	private double skillCalculator(SkillParser skillParser) {
		
		if (skillParser.getActivationResult().contains("score")) {
			return Math.round(skillParser.getProbability() * skillFrequency) * skillParser.getActivationResultNumber();
		}
		
		else {
			return 0;
		}
		
	}
	
	private double skillCalculatorSIS(SkillParser skillParser, UserInput userInput, int cardNumber) {
		
		if (cardNumber == 1) {
			
			// 250% Charm
			if (userInput.getCard1SIS().contains("250%") && skillParser.getActivationResult().contains("score")) {
				return Math.round(skillParser.getProbability() * skillFrequency) * (skillParser.getActivationResultNumber() * 2.5);
			}
			
			else if (userInput.getCard1SIS().contains("270x") && skillParser.getActivationResult().contains("HP")) {
				return Math.round(skillParser.getProbability() * skillFrequency) * (skillParser.getActivationResultNumber() * 270);
			}
			
			else if (userInput.getCard1SIS().contains("25%") && skillParser.getActivationResult().contains("seconds")) {
					
				double boostFrequency = Math.min(1, skillParser.getProbability() * 
													(skillFrequency * skillParser.getActivationResultNumber()) / userInput.getTime());
					
				return ((scoreContribution * boostFrequency * 1.25) + 
						(scoreContribution * (1 - boostFrequency))) - 
						 scoreContribution;
					
					
			}
			
			// Else no SIS applicable, calculate normally
			else {	
				return skillCalculator(skillParser);	
			}
		}
		
		// Else card 2
		else {
			
			// 250% Charm
			if (userInput.getCard2SIS().contains("250%") && skillParser.getActivationResult().contains("score")) {
				return Math.round(skillParser.getProbability() * skillFrequency) * (skillParser.getActivationResultNumber() * 2.5);
			}
			
			else if (userInput.getCard2SIS().contains("270x") && skillParser.getActivationResult().contains("HP")) {	
				return Math.round(skillParser.getProbability() * skillFrequency) * (skillParser.getActivationResultNumber() * 270);
			}
			
			else if (userInput.getCard2SIS().contains("25%") && skillParser.getActivationResult().contains("seconds")) {
					
				double boostFrequency = Math.min(1, skillParser.getProbability() * 
						(skillFrequency * skillParser.getActivationResultNumber()) / userInput.getTime());

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
		
		// Score-based skills
		if (skillParser.getActivationCondition().contains("scored")) {
			
			return Math.floorDiv(userInput.getFinalScore(), (int)skillParser.getActivationConditionNumber());
			
		}
		
		// Time-based skills
		if (skillParser.getActivationCondition().contains("seconds")) {
			return Math.floorDiv(userInput.getTime(), (int)skillParser.getActivationConditionNumber());
		}
		
		// Perfect-based skills
		else if (skillParser.getActivationCondition().contains("perfects")) {
			return Math.floorDiv((int)Math.round(userInput.getNoteCount() * perfectPercentage), (int)skillParser.getActivationConditionNumber());
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
		
		return Calculate600To800(finalBaseStats) +
			   (Math.round(remainder * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[6])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[6])));				  
		
	}
	
	private double Calculate600To800(int finalBaseStats) {
		
		int remainder = noteCount - 600;
		noteCount = noteCount - remainder;
		
		return Calculate400To600(finalBaseStats) +
			   (Math.round(remainder * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[5])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[5])));	
		
	}
	
	private double Calculate400To600(int finalBaseStats) {
		
		int remainder = noteCount - 400;
		noteCount = noteCount - remainder;
		
		return Calculate200To400(finalBaseStats) + 
			   (Math.round(remainder * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[4])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[4])));	
		
	}
	
	private double Calculate200To400(int finalBaseStats) {
		
		int remainder = noteCount - 200;
		noteCount = noteCount - remainder;
		
		return Calculate100To200(finalBaseStats) +
			   (Math.round(remainder * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[3])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[3])));			
		
	}
	
	private double Calculate100To200(int finalBaseStats) {
		
		int remainder = noteCount - 100;
		noteCount = noteCount - remainder;
		
		return Calculate50To100(finalBaseStats) +
			   (Math.round(remainder * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[2])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[2])));
		
	}
	
	private double Calculate50To100(int finalBaseStats) {
		
		int remainder = noteCount - 50;
		noteCount = noteCount - remainder;
		
		return Calculate0To50(finalBaseStats) + 
			   (Math.round(remainder * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[1])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[1])));
		
	}
	
	private double Calculate0To50(int finalBaseStats) {
		
		return (Math.round(noteCount * perfectPercentage)      * noteHitCalculator(finalBaseStats, perfectHit, comboMultiplier[0])) +
			   (Math.round(noteCount * (1 - perfectPercentage) * noteHitCalculator(finalBaseStats, greatHit,   comboMultiplier[0])));
		
	}
	
	private double noteHitCalculator(int finalBaseStats, double accuracy, double comboMultiplier) {
		
		return Math.floor(finalBaseStats * 0.0125 * accuracy 
				   						 		  * comboMultiplier 
				   						 		  * noteType 
				   						 		  * attributeMultiplier 
				   						 		  * groupMultiplier);
		
	}

	public FinalInformation getInfo() {
		return info;
	}
	
}
