//Name: Jinyu JIAO    Student ID: 260881046
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
	private String name;
	private double attack;
	private double healthMax;
	private double health;
	private int numberOfWins;
	
	
	private ArrayList<String> spells = new ArrayList<>();
//create Spells list to store the spells from the text file 
	static Spell[] spellList = new Spell[4];
	
	
	public Character(String name, double attack, double healthMax, int numberOfWins) {
		this.name =name;
		this.attack =attack;
		this.healthMax = healthMax;
		this.numberOfWins = numberOfWins;
	}
	public String getName() {
		return this.name;
	}
	public double getAttackValue() {
		return this.attack;
	}
	public double getMaxHealth() {
		return this.healthMax;
	}
	public double getCurrHealth() {
		return this.health;
	}
	public int getNumWins() {
		return this.numberOfWins;
	}
	
	public String toString() {
		return this.name +": Health: " +this.health;
	}
	
	public double getAttackDamage(int seed) {
		Random generator =new Random(seed);
		double r = generator.nextDouble();
		double r2 = generator.nextDouble();
		double r3 = generator.nextDouble();
//		double value = (0.7+(new Random().nextDouble()* ( 1 - 0.7 )));
		double value = ThreadLocalRandom.current().nextDouble(0.7, 1.0);
		return this.attack =this.attack * value;
	}
	
	public double takeDamage(double damage) {
		return this.health=this.health - this.attack;
	}
	public void increaseWins(int numberOfWins) {		
	}
	
//	
	public ArrayList<String> setSpellsVersion(ArrayList<String> copyList) {
		String name ="";
		double min =0.0;
		double max=0.0;
		double chance =0.0;
		int i=0;
		int j=0;
//split the text according to the tab, and store them into the ArrayList as strings
		String[] seperateSpell = new String[copyList.size()];
		seperateSpell=copyList.get(i).split("\t");
		
			for(; i<copyList.size();i++) {
				seperateSpell=copyList.get(i).split("\t");
					spells.add(seperateSpell[0]);
					spells.add(seperateSpell[1]);
					spells.add(seperateSpell[2]);
					spells.add(seperateSpell[3]);

					for(; j<spells.size() ;) {
						name = spells.get(0+j);
						min = Double.parseDouble(spells.get(1+j));
						max = Double.parseDouble(spells.get(2+j));
						chance = Double.parseDouble(spells.get(3+j));
						j=j+4;
//						System.out.println(j);
					}
					spellList[i]= new Spell(name, min, max, chance);
			}
			return this.spells;
	}
	
//store the strings separately based on their attributes	
	public Spell[] setSpells(ArrayList<String> copyList) {
		String name ="";
		double min =0.0;
		double max=0.0;
		double chance =0.0;
		int j=0;	
			for(; j<copyList.size();) {
				name = copyList.get(0+j);
				min = Double.parseDouble(copyList.get(1+j));
				max = Double.parseDouble(copyList.get(2+j));
				chance = Double.parseDouble(copyList.get(3+j));
				j=j+4;

				spellList[j/4-1]= new Spell(name, min, max, chance);
				}
		return spellList;
	}
	
	
	
	public void displaySpells(ArrayList<String> copyList) {
		System.out.println(copyList);
//		System.out.println(spellList);
	}

	
	public double castSpell(String spellName, int number) {
		int i = 0;
		for(;i<4;i++) {
			if(spellName.equalsIgnoreCase(spellList[i].getName())) {
				return spellList[i].getMagicDamage(number);
			}
		}
		System.out.println();
		return -1;
	}
}
