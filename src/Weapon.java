
public class Weapon {
	//Properties
	private String weaponName;
	//Is determined by metal
	private int baseDamage;
	//Is determined by type of weapon
	private double baseSpeed;
	
	//Constructor
	public Weapon (String newName, int newMetal, double weaponType) {
		weaponName = newName;
		baseDamage = newMetal;
		baseSpeed = weaponType;
	}
	
	//getter
	public String getWeaponName() {
		return weaponName;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public double getBaseSpeed() {
		return baseSpeed;
	}
	
	//if x == 0, metals; if x == 1, weaponTypes
	static String[][] weaponList = {{"Bare ", "Bronze ", "Iron ", "Steel ", "Blacksteel "}, {"Fists", "Dagger", "Shortsword", "Longsword", "Greatsword"}};
}