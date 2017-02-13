package back_end.SugarScape;

import java.util.Random;

public class AgentCellInfo {
	private boolean isMale;
	private int myAge;
	private int maxAge;
	private int mySugar;
	private int mySugarMetabolism;
	private int myVisionRange;
	
	
	private final int MAX_AGE_LOWER_BOUND=60;
	private final int MAX_AGE_UPPER_BOUND=100;
	
	
	public AgentCellInfo(int vision, int sugar, int sugarMetabolism, int gender, int age) {
		isMale= gender==1? true: false;
		myAge=age;
		maxAge=new Random().nextInt(MAX_AGE_UPPER_BOUND-MAX_AGE_LOWER_BOUND+1)
				+MAX_AGE_LOWER_BOUND;
		
		mySugar=sugar;
		mySugarMetabolism=sugarMetabolism;
		myVisionRange=vision;
	}

}
