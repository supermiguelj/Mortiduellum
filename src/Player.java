import java.util.concurrent.TimeUnit;

public class Player {
	private String playerName;
	private int hp = 100;
	private int strength = 1;
	private int defense = 1;
	Weapon weapon;
	Shield shield;
	
	//Constructor
	public Player(String name) {
		playerName = name;
	}
	
	//getters
	public int getHP() {
		return hp;
	}
	public String getName() {
		return playerName;
	}
	public int getStrength() {
		return strength;
	} 
	public int getDefense() {
		return defense;
	}
	
	
	//methods
	public int attack(Player p2, int rng, int MAXROLL) {
		int damage = 0;
		
		//Combat Formula begins here
		
		//Is defaulted to no modifier
		double attackSpeedMod = 1.0;
		//Grants attack speed buff if attacker has no shield
		if (shield.getBaseDefense() == Metals.NONE)
			attackSpeedMod = 1.33;		
		//Calculates attacker's weapon speed by including speed modifier
		double calculatedWeaponSpeed = weapon.getBaseSpeed() * attackSpeedMod;
		
		//calculates attacker's actual strength
		int trueStrength = (strength + ((int) Math.rint((weapon.getBaseDamage() * calculatedWeaponSpeed))));
		//Calculates defender's true defense
		int p2TrueDefense = (defense + ((int) (p2.shield.getBaseDefense() * p2.shield.getBaseWeight() * 0.67)));
		
		
		
		//RNG percentage modifier would alter the rate of change depending on what the max roll of the die is
		double rngMod = 1.0 / MAXROLL;
		//RNG contribution to attacker's base attack
		int finalAttack = 0;
		
		//Calculates probability of attack miss
		if (rng == 0)
			trueStrength = 0;
		//Calculates probability of critical hit
		else if (rng == MAXROLL) {
			p2TrueDefense = 0;
			finalAttack = (int) (trueStrength * 1.5);
			
		}
			//final attack strength is the same as base attack strength when RNG is in the middle
		else if (rng == (MAXROLL / 2))
			finalAttack = trueStrength;
			//High Roll
		else if (rng > (MAXROLL / 2) && rng < MAXROLL)
			finalAttack = (int) Math.rint((trueStrength * (1 + (rngMod * (rng - (MAXROLL / 2))))));
			//Low Roll
		else if (rng < (MAXROLL / 2) && rng > 0)
			finalAttack = (int) Math.rint((trueStrength * (1 - (rngMod * ((MAXROLL / 2) - rng)))));
			//Debug Condition
		else {
			printWithDelays("You should not be seeing this message! rng is somehow messed up!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
			System.exit(-1);
		}
		
		
		//Final calculation
		damage =  finalAttack - p2TrueDefense;
		//Fixes negative damage healing defender
		if (damage < 0)
			damage = 0;
		p2.hp -= damage;
		
		//Combat Formula ends here
		
		//After attack commentary
		if (p2.getHP() < 0)
			p2.hp = 0;
		 printWithDelays(playerName + " attacks " + p2.getName() + "!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
		if (rng > 0) {
			//Commentary relies on RNG
			printWithDelays("The attack lands...\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
			if (damage == 0)
				printWithDelays("But it bounces off.\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
			else {
				if (rng == MAXROLL)
					printWithDelays("IT'S A PERFECT STRIKE!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
				else if (rng >= Math.ceil(MAXROLL * .67) && (rng <= MAXROLL - 1))
			 		printWithDelays("It's a devastating strike!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
				else if ((rng >= Math.floor(MAXROLL * .33)) && (rng < Math.ceil(MAXROLL * .67)))
					printWithDelays("It's a successful strike.\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
				else if ((rng > 0) && (rng < Math.floor(MAXROLL * .33)))
					printWithDelays("It barely makes a dent.\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
				//Debug condition
				else {
					printWithDelays("You should not be seeing this message! RNG = " + rng + "\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
					System.exit(-1);
				}
				printWithDelays(playerName + " deals " + damage + " hitpoints against " + p2.getName() + "!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
				printWithDelays(p2.getName() + " has "+ p2.getHP() + " hitpoints left!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
			}
			 	
		} else
			 printWithDelays("The attack missed!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
		return damage;
	}
	
	public void fighterIntro() {
		 if (weapon.getWeaponName().contains("Fists") || weapon.getWeaponName().contains("Bare"))
			 printWithDelays(playerName + " enters the arena with his " + weapon.getWeaponName() + ".\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
		 else if (weapon.getWeaponName().contains("Greatsword") || shield.getShieldName().contains("Hands"))
			 printWithDelays(playerName + " enters the arena with a(n) " + weapon.getWeaponName() + ".\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);	
		 else
			 printWithDelays(playerName + " enters the arena with a(n) " + weapon.getWeaponName() + " and a(n) " + shield.getShieldName() + ".\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
	}
	
	public void getStatus() {
		 printWithDelays(playerName + " has " + hp + " hitpoints left.\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
	}
	
	public void victory(Player p2, int roundCounter) {
		 printWithDelays(p2.getName() + " was defeated in " + roundCounter + " round(s)!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
		 printWithDelays("The winner is... " + playerName + "!\n", TimeUnit.MILLISECONDS, Main.TEXTSPEED);
	}
	
	public static void printWithDelays(String data, TimeUnit unit, long delay) {
	    for (char ch : data.toCharArray()) {
	        System.out.print(ch);
	        try {
				unit.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
