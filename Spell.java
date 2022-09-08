//Name: Jinyu JIAO    Student ID: 260881046
import java.util.*;

public class Spell {
	private String name;
	private double min;
	private double max;
	private double chance;

//	define the Spell class properly
	public Spell(String name, double min, double max, double chance) {
		if(min <0.0 || min>max || (chance <0.0 && chance >1.0)) {
			throw new IllegalArgumentException("Something is wrong!");
		}else {
			this.name = name;
			this.min = min;
			this.max = max;
			this.chance = chance;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getMagicDamage(int seed) {
		Random generator = new Random(seed);
		double r = generator.nextDouble();
		double r2= generator.nextDouble();
		double r3= generator.nextDouble();
		
		if(r3 > this.chance) {
//			System.out.println("The spell fails!");
			return 0.0;
		}else {
			double spellDamage2=(new Random().nextDouble() * (max - min)) + min;
			return spellDamage2;
		}
	}
	
	public String toString() {
		return this.name + this.min + this.max + this.chance;
	}
}
