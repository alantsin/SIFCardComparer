public class BaseStatsCalculator {

	private int finalBaseStats;
	
	public BaseStatsCalculator(Card card, UserInput userInput) {
		
		int initialStat = card.getBaseStat();
		int bond;
		
		double centerBoost;
		double memberModifier;
		
		
		// If song attribute matches card attribute
		if (userInput.getAttribute().equals(card.getCardAttribute())) {
			bond = card.getBondPoints();
		}
		
		else {
			bond = 0;
		}
		
		centerBoost = centerBoost(card, userInput);
		
		if (!"N".equals(card.getRarity())) {
			memberModifier = memberModifier(card, userInput);
		}
		
		else {
			
			memberModifier = 1;
			
		}
		
		if (userInput.getCenterSkill().contains("12%")) {
			
			finalBaseStats = (int)Math.ceil((Math.ceil((initialStat + bond)) * memberModifier) + (centerBoost * card.getOffStat()));
			
		}
		
		else {
			
			finalBaseStats = (int)Math.ceil((Math.ceil((initialStat + bond) * memberModifier)) * centerBoost);
			
		}
		
	}

	private double centerBoost(Card card, UserInput userInput) {

		if(userInput.getCenterSkill().contains("12%")) {
			
			if (userInput.getCenterSkill().contains("12% Smile") && "Smile".equals(userInput.getAttribute()) ||
				userInput.getCenterSkill().contains("12% Pure") &&  "Pure".equals(userInput.getAttribute())  ||	
				userInput.getCenterSkill().contains("12% Cool") &&  "Cool".equals(userInput.getAttribute())) {
				
				return 0.12;
				
				
			}
			
			else {
				
				return 0;
				
			}
			
		}
		
		else if(userInput.getCenterSkill().contains("9%")) {
			
			if (userInput.getCenterSkill().contains("Smile") && "Smile".equals(card.getBaseStatType()) && "Smile".equals(userInput.getAttribute()) ||
				userInput.getCenterSkill().contains("Pure")  && "Pure".equals(card.getBaseStatType())  && "Pure".equals(userInput.getAttribute())  ||
				userInput.getCenterSkill().contains("Cool")  && "Cool".equals(card.getBaseStatType())  && "Cool".equals(userInput.getAttribute())) {
				
				return 1.09;
		
			}
			
			else {
				
				return 1;
				
			}
			
		}
		
		else {
			
			return 1;
			
		}
	}
	
	private double memberModifier(Card card, UserInput userInput) {
		
		if (userInput.getMemberModifier().contains("3%")) {
			
			if (userInput.getMemberModifier().contains("μ's") && "μ's".equals(card.getMainUnit())) {
				
				return 1.03;
				
			}
			
			else if (userInput.getMemberModifier().contains("Aqours") && "Aqours".equals(card.getMainUnit())) {
				
				return 1.03;
				
			}
			
			else {
				
				return 1;
				
			}
			
		}
		
		else if (userInput.getMemberModifier().contains("6%")) {
			
			if (userInput.getMemberModifier().contains("First") && "First".equals(card.getYear())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("Second") && "Second".equals(card.getYear())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("Third") && "Third".equals(card.getYear())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("Printemps") && "Printemps".equals(card.getSubUnit())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("Lily White") && "Lily White".equals(card.getSubUnit())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("Bibi") && "Bibi".equals(card.getSubUnit())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("CYaRon!") && "CYaRon!".equals(card.getSubUnit())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("AZALEA") && "AZALEA".equals(card.getSubUnit())) {
				
				return 1.06;
				
			}
			
			else if (userInput.getMemberModifier().contains("Guilty Kiss") && "Guilty Kiss".equals(card.getSubUnit())) {
				
				return 1.06;
				
			}
			
			else {
				
				return 1;
				
			}
			
		}
		
		else {
			
			return 1;
			
		}
		
	}
	
	public int getFinalBaseStats() {
		return finalBaseStats;
	}
	
}
