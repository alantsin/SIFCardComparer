
public class DifferenceInformation {
	
	private String finalBaseStatsDifference;
	
	private String scoreContributionDifference;
	
	private String skillContributionDifference;
	
	private String finalScoreDifference;
	
	public DifferenceInformation(FinalInformation info1, FinalInformation info2) {
		
		setDifference(1, Integer.parseInt(info1.getFinalBaseStats()), 	 Integer.parseInt(info2.getFinalBaseStats()));
		setDifference(2, Integer.parseInt(info1.getScoreContribution()), Integer.parseInt(info2.getScoreContribution()));
		setDifference(3, Integer.parseInt(info1.getSkillContribution()), Integer.parseInt(info2.getSkillContribution()));
		setDifference(4, Integer.parseInt(info1.getFinalScore()), 		 Integer.parseInt(info2.getFinalScore()));
		
	}

	private void setDifference(Integer value, Integer num1, Integer num2) {
		
		int difference;
		
		// If card 1 is stronger
		if (num1 >= num2) {
			
			difference = num1 - num2;
			
			switch(value) {
			
			case 1:
				finalBaseStatsDifference = "+" + Integer.toString(difference);
				break;
				
			case 2:
				scoreContributionDifference = "+" + Integer.toString(difference);
				break;
			
			case 3:
				skillContributionDifference = "+" + Integer.toString(difference);
				break;
			
			case 4:
				finalScoreDifference = "+" + Integer.toString(difference);
				break;
				
			default:
				break;
				
			}
			
		}
		
		// Else card 2 is stronger
		else {
			
			difference = num2 - num1;
			
			switch(value) {
			
			case 1:
				finalBaseStatsDifference = "-" + Integer.toString(difference);
				break;
				
			case 2:
				scoreContributionDifference = "-" + Integer.toString(difference);
				break;
			
			case 3:
				skillContributionDifference = "-" + Integer.toString(difference);
				break;
			
			case 4:
				finalScoreDifference = "-" + Integer.toString(difference);
				break;
				
			default:
				break;
				
			}
			
		}
		
	}

	public String getFinalBaseStatsDifference() {
		return finalBaseStatsDifference;
	}

	public String getScoreContributionDifference() {
		return scoreContributionDifference;
	}

	public String getSkillContributionDifference() {
		return skillContributionDifference;
	}

	public String getFinalScoreDifference() {
		return finalScoreDifference;
	}

}
