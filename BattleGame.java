//Name: Jinyu JIAO    Student ID: 260881046
import java.util.*;


public class BattleGame {
//for debugging purpose, set a fix seed to test. 
	private static int seed =123;
	private static Random randomObeject = new Random();
	private static int fixSeed = randomObeject.nextInt();
	Character player;
	Character monster;

//call the playGame method
	public static void main(String[] args) {		
		String playerFile = "src/player.txt"; 
		String monsterFile = "src/monster.txt"; 
		String spellFile = "src/spells.txt";
		try {
			playGame(playerFile, monsterFile, spellFile);
		} catch (Exception e) {
			System.out.println("The game cannot be played");
		}
	}
	

	public static void playGame(String player, String monster, String spell) {
		Scanner enter = new Scanner(System.in);
		try {
			System.out.println("Wlecome to the game!");
			FileIO.readCharacter(player);
			System.out.println("Name : " + FileIO.readCharacter(player).getName());
			System.out.println("Attack : " + FileIO.readCharacter(player).getAttackValue());
			System.out.println("Health : " + FileIO.readCharacter(player).getMaxHealth());
			System.out.println("Number of Wins : " + FileIO.readCharacter(player).getNumWins());
			System.out.println();
			
			FileIO.readCharacter(monster);
			System.out.println("Name : " + FileIO.readCharacter(monster).getName());
			System.out.println("Attack : " + FileIO.readCharacter(monster).getAttackValue());
			System.out.println("Health : " + FileIO.readCharacter(monster).getMaxHealth());
			System.out.println("Number of Wins : " + FileIO.readCharacter(monster).getNumWins());
			System.out.println();
			
//	Play the game under different conditions
			String attackStrP ="";
			String attackStrM ="";
			double attackDoubleP;
			double attackDoubleM;
			double playerHp = FileIO.readCharacter(player).getMaxHealth();
			double monsterHp = FileIO.readCharacter(monster).getMaxHealth();

			int countRound =0;
			int numOfWinP =0;
			int numOfWinM =0;
			if(FileIO.readSpells(spell).isEmpty()) {
				System.out.println("The game will be played without spells.");
//	Play the game without spells
				while(true) {
					attackStrP = String.format("%1$.2f", FileIO.readCharacter(player).getAttackDamage(fixSeed));
					attackStrM = String.format("%1$.2f", FileIO.readCharacter(monster).getAttackDamage(fixSeed));
					attackDoubleP = Double.parseDouble(attackStrP);
					attackDoubleM = Double.parseDouble(attackStrM);
					playerHp = playerHp - attackDoubleM;
					String playerHpStr = String.format("%1$.2f",playerHp);
					monsterHp = monsterHp- attackDoubleP;
					String monsterHpStr = String.format("%1$.2f",monsterHp);

						System.out.println("Enter a command: ");
						String command = enter.nextLine();
						enter.nextLine();
						if(command.equals("attack")) {
							
							if(countRound%2==0) {
								if(monsterHp<=0) {
									System.out.println(FileIO.readCharacter(player).getName() + " attacks for "+ attackStrP + " damage!");
									System.out.println(FileIO.readCharacter(monster).getName()+" was knocked out!" +"\n" +"\n" +
											"You killed the monster!");
									numOfWinP++;
									System.out.println(FileIO.readCharacter(player).getName() + " has won: "+ numOfWinP + " times.");	
									FileIO.writeCharacter(player, "src/player.txt");
									break;
								}
								System.out.println(FileIO.readCharacter(player).getName() + " attacks for "+ attackStrP + " damage!");
								System.out.println(FileIO.readCharacter(monster).getName() + " current health is "+ monsterHpStr + ".");
								System.out.println();
								countRound++;								
							}if(countRound%2==1) {
								if(playerHp<=0) {
									System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
									System.out.println(FileIO.readCharacter(player).getName()+" was knocked out!" +"\n" +"\n" +
											"Oh no! You lost!");
									numOfWinM++;
									System.out.println(FileIO.readCharacter(monster).getName() + " has won: "+ numOfWinM + " times.");	
									FileIO.writeCharacter(monster, "src/monster.txt");
									break;
								}
								System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
								System.out.println(FileIO.readCharacter(player).getName() + " current health is "+ playerHpStr+ ".");
								System.out.println();
								countRound++;					
							}
						}else if(command.equals("quit")) {
							System.out.println("Goodbye!");
							break;
						}else {
							System.out.println("Message cannot recognize, please enter attack or quit command:");
							}
						}
				}else {
//	play the game with spells
					int i=0;
					System.out.println("Here are the available spells:");
					for(; i<FileIO.readSpells(spell).size();i=i+4 ) {
					System.out.print("Name: ");
					System.out.print(FileIO.readCharacter(player).setSpells(FileIO.readSpells(spell))[i/4].getName() +"\t");
					System.out.print(" Damage: ");
					System.out.print(Double.parseDouble(FileIO.readSpells(spell).get(i+1)));
					System.out.print("-");
					System.out.print(Double.parseDouble(FileIO.readSpells(spell).get(i+2)));
					System.out.print(" Chance: ");
					System.out.print(Double.parseDouble(FileIO.readSpells(spell).get(i+3))*100 +"%");
					System.out.println();
					}
					while(true) {
						attackStrM = String.format("%1$.2f", FileIO.readCharacter(monster).getAttackDamage(fixSeed));
						attackStrP = String.format("%1$.2f", FileIO.readCharacter(player).getAttackDamage(fixSeed));
						attackDoubleM = Double.parseDouble(attackStrM);
						attackDoubleP = Double.parseDouble(attackStrP);
						playerHp = playerHp - attackDoubleM;		
//						monsterHp = monsterHp- attackDoubleP;		
						String monsterHpStr = String.format("%1$.2f",monsterHp);
						String playerHpStr = String.format("%1$.2f",playerHp);
							
						System.out.println("Enter a command: ");
						String command = enter.nextLine();
						enter.nextLine();
						double castDamage = FileIO.readCharacter(player).castSpell(command, fixSeed);
							if(command.equals("attack")) {
//								playerHp = playerHp - attackDoubleM;		
								monsterHp = monsterHp- attackDoubleP;	
								monsterHpStr = String.format("%1$.2f",monsterHp);
//								playerHpStr = String.format("%1$.2f",playerHp);
								if(countRound%2==0) {
									if(monsterHp<=0.0) {
										System.out.println(FileIO.readCharacter(player).getName() + " attacks for "+ attackStrP + " damage!");
										System.out.println(FileIO.readCharacter(monster).getName()+" was knocked out!" +"\n" +"\n" +
													"You killed the monster!");
										numOfWinP++;
										System.out.println(FileIO.readCharacter(player).getName() + " has won: "+ numOfWinP + " times.");	
										FileIO.writeCharacter(player, "src/player.txt");
										break;
										}
									System.out.println(FileIO.readCharacter(player).getName() + " attacks for "+ attackStrP + " damage!");
									System.out.println(FileIO.readCharacter(monster).getName() + " current health is "+ monsterHpStr + ".");
									System.out.println();
									countRound++;	
								}if(countRound%2==1) {
									if(playerHp<=0.0) {
										System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
										System.out.println(FileIO.readCharacter(player).getName()+" was knocked out!" +"\n" + "\n" +
													"Oh no! You lost!");
										numOfWinM++;
										System.out.println(FileIO.readCharacter(monster).getName() + " has won: "+ numOfWinM + " times.");	
										FileIO.writeCharacter(monster, "src/monster.txt");
										break;
										}
									System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
									System.out.println(FileIO.readCharacter(player).getName() + " current health is "+ playerHpStr+ ".");
									System.out.println();
									countRound++;
									}
								}else if(command.equals("quit")) {
									System.out.println("Goodbye!");
									break;
								}else {
//	player enter commands other than "attack" or "quit", assume all the commands as spells
									String castDamageStr = String.format("%1$.2f", castDamage);
//	the command does match one of the Spells, but fail to cast the spell
									if(castDamage==0.0) {
										if(playerHp<=0 && monsterHp>=0) {
											System.out.println(FileIO.readCharacter(player).getName() + " tried to cast "+ command + " but they failed!");
											System.out.println();
											System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
											System.out.println(FileIO.readCharacter(player).getName()+" was knocked out!" +"\n" +"\n" +
													"Oh no! You lost!");
											numOfWinM++;
											System.out.println(FileIO.readCharacter(monster).getName() + " has won: "+ numOfWinM + " times.");	
											FileIO.writeCharacter(monster, "src/monster.txt");
											break;
										}
										System.out.println(FileIO.readCharacter(player).getName() + " tried to cast "+ command + " but they failed!");
										System.out.println();
										System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
										System.out.println(FileIO.readCharacter(player).getName() + " current health is "+ playerHpStr+ ".");
										System.out.println();
										
									}else if(castDamage==-1) {
//	the command does not match the one of the Spells
										if(playerHp<=0 && monsterHp>=0) {
											System.out.println(FileIO.readCharacter(player).getName() + " tried to cast "+ command + " but they don't know the spell!");
											System.out.println();
											System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
											System.out.println(FileIO.readCharacter(player).getName()+" was knocked out!" +"\n" +"\n" +
													"Oh no! You lost!");
											numOfWinM++;
											System.out.println(FileIO.readCharacter(monster).getName() + " has won: "+ numOfWinM + " times.");	
											FileIO.writeCharacter(monster, "src/monster.txt");
											break;
										}
										System.out.println(FileIO.readCharacter(player).getName() + " tried to cast "+ command + " but they don't know the spell!");
										System.out.println();
										System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
										System.out.println(FileIO.readCharacter(player).getName() + " current health is "+ playerHpStr+ ".");
										System.out.println();

									}else {
//									the commands match one of the Spells and the spell was successfully casted out. 
										monsterHp = monsterHp - castDamage;
										monsterHpStr = String.format("%1$.2f",monsterHp);
										if(playerHp<=0 && monsterHp<=0) {
											System.out.println(FileIO.readCharacter(player).getName() + " casts "+ command + " dealing "+ castDamageStr +" damage!");
											System.out.println(FileIO.readCharacter(monster).getName()+" was knocked out!" +"\n" +"\n" +
													"You killed the monster!");
											numOfWinP++;
											System.out.println(FileIO.readCharacter(player).getName() + " has won: "+ numOfWinP + " times.");	
											FileIO.writeCharacter(player, "src/player.txt");
											break;
										}if(playerHp<=0 && monsterHp>=0) {
											System.out.println(FileIO.readCharacter(player).getName() + " casts "+ command + " dealing "+ castDamageStr +" damage!");
											System.out.println(FileIO.readCharacter(monster).getName() + " current health is "+ monsterHpStr + ".");
											System.out.println();
											System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
											System.out.println(FileIO.readCharacter(player).getName()+" was knocked out!" +"\n" +"\n" +
													"Oh no! You lost!");
											numOfWinM++;
											System.out.println(FileIO.readCharacter(monster).getName() + " has won: "+ numOfWinM + " times.");	
											FileIO.writeCharacter(monster, "src/monster.txt");
											break;
										}
										System.out.println(FileIO.readCharacter(player).getName() + " casts "+ command + " dealing "+ castDamageStr +" damage!");
										System.out.println(FileIO.readCharacter(monster).getName() + " current health is "+ monsterHpStr + ".");
										System.out.println();
										System.out.println(FileIO.readCharacter(monster).getName() + " attacks for "+ attackStrM + " damage!");
										System.out.println(FileIO.readCharacter(player).getName() + " current health is "+ playerHpStr+ ".");
										System.out.println();
										}
									}
								}
						}
				}catch(Exception e){
					System.out.println(e);
					System.out.println("!!!ERROR MESSAGE!!!"+ "\n"+"The game cannot be played");
				}
		enter.close();
	}
}
