package DungeonoftheBrutalKing;



public class Spells {

	protected Singleton myChar = new Singleton();



	//public ArrayList<String> myMonsters = new ArrayList<String>();
	private static Spells single_instance_spells;

	public String charintelligence;
	public String charwisdom;
	public int requiredwis;
	public int requiredint;
	public int damagecaused;
	public String name;
	public int foodconjured;
	public Boolean isCombatSpell;

	public static Spells Singleton()
	{
		// To ensure only one instance is created
		if (single_instance_spells == null) {

			single_instance_spells = new Spells();

		}
		return single_instance_spells;
	}

	//Adding User learned spells into the hashtable




	//Take in Charector and Monster "Pass a null if not in combat"
	public void CastSpell()
	{
	}



}
