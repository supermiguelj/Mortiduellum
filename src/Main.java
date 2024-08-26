import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main {
	public static final int TEXTSPEED = 50;
	public static final int BORDERTEXTSPEED = 25;
	private static final int MAXROLL = 20;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name1, name2;
		 printWithDelays("=-=-=-=-=-=-=-=-=-=-=-= Mortiduellum Alpha v1.0 =-=-=-=-=-=-=-=-=-=-=-=\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
		 printWithDelays("\nEnter the first fighter's name: ", TimeUnit.MILLISECONDS, TEXTSPEED);
		name1 = sc.nextLine();
		Player player1 = new Player(name1);
		printWithDelays("Enter the second fighter's name: ", TimeUnit.MILLISECONDS, TEXTSPEED);
		name2 = sc.nextLine();
		System.out.println();
		Player player2 = new Player(name2);
		Random ran = new Random();
		//Roll for player 1 weapon
		int metalRNG = ran.nextInt(Metals.CHOICES);
		int typeRNG = ran.nextInt(Types.CHOICES);
		if (metalRNG == 0)
			typeRNG = 0;
		if (typeRNG == 0)
			metalRNG = 0;
		Weapon firstWeapon = new Weapon(Weapon.weaponList[0][metalRNG].concat(Weapon.weaponList[1][typeRNG]), Metals.metals[metalRNG], Types.weaponTypes[typeRNG]);
		//Weapon firstWeapon = new Weapon(Weapon.weaponList[0][metalRNG].concat(Weapon.weaponList[1][typeRNG]), Metals.BRONZE, Types.GREATSWORD);
		//Roll for player 1 shield
		Shield firstShield;
		//Checks if player 1 is already holding 2-handed weapon or is empty-handed
		if (typeRNG != Types.FISTS || typeRNG != Types.GREATSWORD) {
			metalRNG = ran.nextInt(Metals.CHOICES);
			typeRNG = ran.nextInt(Types.CHOICES);
			if (metalRNG == 0)
				typeRNG = 0;
			if (typeRNG == 0)
				metalRNG = 0;
			firstShield = new Shield(Shield.shieldList[0][metalRNG].concat(Shield.shieldList[1][typeRNG]), Metals.metals[metalRNG], Types.shieldTypes[typeRNG]);
		//Forces off-hand to be empty
		} else {
			firstShield = new Shield("Bare Hands", Metals.NONE, Types.NONE);
		}
		//Shield firstShield = new Shield(Shield.shieldList[0][metalRNG].concat(Shield.shieldList[1][typeRNG]), Metals.metals[metalRNG], Types.NONE);
		//Roll for player 2 weapon
		metalRNG = ran.nextInt(Metals.CHOICES);
		typeRNG = ran.nextInt(Types.CHOICES);
		if (metalRNG == 0)
			typeRNG = 0;
		if (typeRNG == 0)
			metalRNG = 0;
		Weapon secondWeapon = new Weapon(Weapon.weaponList[0][metalRNG].concat(Weapon.weaponList[1][typeRNG]), Metals.metals[metalRNG], Types.weaponTypes[typeRNG]);
		//Roll for player 2 shield
		Shield secondShield;
				//Checks if player 2 is already holding 2-handed weapon or is empty-handed
				if (typeRNG != Types.FISTS || typeRNG != Types.GREATSWORD) {
					metalRNG = ran.nextInt(Metals.CHOICES);
					typeRNG = ran.nextInt(Types.CHOICES);
					if (metalRNG == 0)
						typeRNG = 0;
					if (typeRNG == 0)
						metalRNG = 0;
					secondShield = new Shield(Shield.shieldList[0][metalRNG].concat(Shield.shieldList[1][typeRNG]), Metals.metals[metalRNG], Types.shieldTypes[typeRNG]);
				//Forces off-hand to be empty
				} else {
					secondShield = new Shield("Bare Hands", Metals.NONE, Types.NONE);
				}
		
		player1.weapon = firstWeapon;
		player1.shield = firstShield;
		player2.weapon = secondWeapon;
		player2.shield = secondShield;
		
		//Luck-based element in combat
		int rng;
		int coinFlip;
		int roundCounter = 1;
		
		 printWithDelays("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
		 printWithDelays("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
		//Introductions
		 System.out.println();
		player1.fighterIntro();
		player2.fighterIntro();
		
		 printWithDelays("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
		//Combat
		while (player1.getHP() > 0 && player2.getHP() > 0) {
		printWithDelays("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + "  Round " + roundCounter + "  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
			coinFlip = ran.nextInt(2);
			player1.getStatus();
			player2.getStatus();
			System.out.println();
			//FIGHT
			if (coinFlip == 0) {
				rng = ran.nextInt(MAXROLL + 1);
				player1.attack(player2, rng, MAXROLL);
				System.out.println();
				rng = ran.nextInt(MAXROLL + 1);
				if (player2.getHP() > 0) {
					player2.attack(player1, rng, MAXROLL);
				}
			} else {
				rng = ran.nextInt(MAXROLL + 1);
				player2.attack(player1, rng, MAXROLL);
				System.out.println();
				rng = ran.nextInt(MAXROLL + 1);
				if (player1.getHP() > 0) {
					player1.attack(player2, rng, MAXROLL);
				}
			}
			
			roundCounter++;
			System.out.printf("\n");
		}
		 printWithDelays("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
		 printWithDelays("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n", TimeUnit.MILLISECONDS, BORDERTEXTSPEED);
		 
		//Victory Scene
		if (player1.getHP() > 0 && player2.getHP() <= 0)
			player1.victory(player2, roundCounter);
		else if (player2.getHP() > 0 && player1.getHP() <= 0)
			player2.victory(player1, roundCounter);
		else {
			printWithDelays("Both fighters perished!\n", TimeUnit.MILLISECONDS, TEXTSPEED);
			printWithDelays("It is a Tie!\n\n", TimeUnit.MILLISECONDS, TEXTSPEED);
		}
		
			
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
