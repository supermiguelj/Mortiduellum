
public class Shield {
	//Properties
	private String shieldName;
	//Is determined by Metal
	private int baseDefense;
	//Is determined by ShieldType
	private double baseWeight;
	
	//Constructor
	public Shield(String name, int metal, double shieldType) {
		shieldName = name;
		baseDefense = metal;
		baseWeight = shieldType;
	}
	
	//getter
	public String getShieldName() {
		return shieldName;
	}
	public int getBaseDefense() {
		return baseDefense;
	}
	public double getBaseWeight() {
		return baseWeight;
	}
	
	//if x == 0, metals; if x == 1, shieldTypes
	static String[][] shieldList = {{"Bare ", "Bronze ", "Iron ", "Steel ", "Blacksteel "}, {"Hands", "Roundshield", "Squareshield", "Kiteshield", "Towershield"}};
}
