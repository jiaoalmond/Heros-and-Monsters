import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class FileIO {
//FOR DEBUGGING NO USE. 	
	public static void main(String[] args) {
		String fileName = "src/monster.txt";
//		readCharacter(fileName);	
//		String fileName = "src/spells.txt";
//		readSpells(fileName);		
		writeCharacter("src/monster.txt","src/monster.txt");
//		System.out.println(readCharacter(fileName).getName());

	}

	public static Character readCharacter(String fileName) {
		String name="";
		double attack=0.0;
		double healthMax=0.0;
		double health=0.0;
		int numberOfWins=0;
		
		ArrayList<String> testList = new ArrayList<>();	
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String readLine = bufferedReader.readLine();
			int count =0;
			while(readLine !=null) {
				testList.add(readLine);
				readLine = bufferedReader.readLine();
				count++;
			}bufferedReader.close();
			fileReader.close();	
			for(int i=0;i<count;i++) {
				if(i%4 == 0) {
					name = testList.get(i);
				}else if(i%4 == 1) {
					attack = Double.parseDouble(testList.get(i));
				}else if(i%4 == 2) {
					healthMax =Double.parseDouble(testList.get(i));
				}else if(i%4 == 3) {
					numberOfWins =Integer.parseInt(testList.get(i));
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println(e);
			return null;
		}catch(IOException o) {
			System.out.println(o);
			return null;
		}		
		Character testPlayer = new Character(name, attack, healthMax, numberOfWins);
		return testPlayer;
	}
	
	public static ArrayList<String> readSpells(String fileName) {
		String name ="";
		double min =0.0;
		double max=0.0;
		double chance =0.0;
		ArrayList<String> testList = new ArrayList<>();	
		ArrayList<String> spells = new ArrayList<>();
		String[] seperateSpell = new String[4];
		int i=0;
		int j=0;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String readLine = bufferedReader.readLine();
			while(readLine !=null) {
				testList.add(readLine);
				readLine = bufferedReader.readLine();
				}	
			bufferedReader.close();
			fileReader.close();	
//			
			for(; i<testList.size();i++) {
				seperateSpell=testList.get(i).split("\t");
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
					}
			}
		}catch(FileNotFoundException e) {
			System.out.println(e+"null");
		}catch(IOException o) {
			System.out.println(o+"null");
		}
		return spells;
	}

//	write the results into the text files
	public static void writeCharacter(String name, String fileExportName) {
		try {
	        BufferedReader file = new BufferedReader(new FileReader(name));
	        StringBuffer inputBuffer = new StringBuffer();

	        if (file.readLine()!= null) { 
	            inputBuffer.append(readCharacter(name).getName()+"\n");
	            inputBuffer.append(String.valueOf(readCharacter(name).getAttackValue())+"\n");
	            inputBuffer.append(String.valueOf(readCharacter(name).getMaxHealth())+"\n");
	            int winNumber =readCharacter(name).getNumWins()+1;
	            inputBuffer.append(String.valueOf(winNumber));
	            }
	        file.close();
	        FileOutputStream fileOut = new FileOutputStream(fileExportName);
	        fileOut.write(inputBuffer.toString().getBytes());
	            fileOut.close();
		        
//			FileWriter fileExport = new FileWriter(fileExportName);
//			BufferedWriter bufferedWriter = new BufferedWriter(fileExport);
//			
//			bufferedWriter.write(readCharacter(name).getName()+"\n");
//			bufferedWriter.write(String.valueOf(readCharacter(name).getAttackValue())+"\n");
//			bufferedWriter.write(String.valueOf(readCharacter(name).getMaxHealth())+"\n");
//			int winNumber =readCharacter(name).getNumWins()+1;
//			bufferedWriter.write(String.valueOf(winNumber));
//			
//			bufferedWriter.close();
	        }catch(IOException e) {
	        	System.out.println(e);
	        	System.out.println("Fail to write to the file: "+ fileExportName);
	        	}
		System.out.println("Successfully wrote to the file: "+ fileExportName);
        
	}
	
}
