package Model.Logic;

public class ScoreAdvisor {
	
	
	private long time;
	private int score;
	
	public void start() {
		
		time = System.currentTimeMillis();
		score = 0;
	}
	

	public int getScoreEnd(int score) {
		
		long diff = (System.currentTimeMillis() - time)/40000;
		
		System.out.println((int)diff);
		
		
		int newScore = (score-(int)diff);
		
		if(newScore < 0) newScore = 0;
		
		return newScore;
		
	}
	

}
