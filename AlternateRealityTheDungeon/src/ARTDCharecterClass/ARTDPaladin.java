package ARTDCharecterClass;

import AlternateRealityTheDungeon.ARTDCharecter;
import AlternateRealityTheDungeon.ARTDClass;

public class ARTDPaladin extends ARTDClass 
{
	int sta; //Stamina
	int chr; //Charisma
	int str; //Strength
	int inti; //Intelligence
	int wis; //Wisdom
	int agi; //Agility

	ARTDCharecter myChar = new ARTDCharecter();
	
	public static String charClass = "Paladin";
	
	public static String PaladinClassDescription;
	public ARTDPaladin()
	{
		int Herolevel = myChar.CharInfo.indexOf(myChar.CharInfo.get(2));
		
		charClass = "Paladin";
		
		PaladinClassDescription = "Compared with other classes the " + ARTDPaladin.charClass + " class has one of the most/n restrictive codes"
				+ " of conduct and paladin characters are expected to/n demonstrate and embody goodness. \n\n"
				+ "Wisdom (WIS) followed by Strength is most important stats for a " + ARTDPaladin.charClass;
		
		//A multiplier for using strength weapons in combat.
		sta = 3;
		chr = 1;
		str = 1;
		inti = 1;
		wis = 3;
		agi = 1;
	}
	@Override
	public double Heal() {
		// TODO Auto-generated method stub
		return 0;
	}
}
