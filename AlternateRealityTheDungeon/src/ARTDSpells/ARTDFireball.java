package ARTDSpells;

import java.util.Random;

import AlternateRealityTheDungeon.ARTDSpells;

public class ARTDFireball extends ARTDSpells{

	int requiredint;
	int damagecaused;
	
	public ARTDFireball() {
		
		requiredint = 15;
		
		
		Random rn = new Random();
		damagecaused = rn.nextInt(10) + 1;
	}

}
