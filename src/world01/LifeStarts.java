package world01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LifeStarts {
	
	public static void performInitialActions(Human character) {
        character.born();
        character.displayInfo();
    }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	Map<String, HumanCharacter> characters = new HashMap<>();
	Random random = new Random(); //Randomオブジェクトを宣言
		
		//キャラクターを登録
	    characters.put("1", new Ari());
	    characters.put("2", new Alex());
	    characters.put("3", new Steve());
	    
	    //インターフェースの配列
	    Human[] characterArray = {new Ari(), new Alex(), new Steve()};
	    System.out.println("All characters in array: ");
	    for (Human h : characterArray) {
	    	h.reserve();
	    }
	    
	    ArrayList<Human> characterList = new ArrayList<>();
	    characterList.add(new Ari());
	    characterList.add(new Alex());
	    characterList.add(new Steve());
	    System.out.println("\nAll characters in Arraylist: ");
	    for (Human h : characterList) {
	    	h.displayInfo();
	    }
	    
	    System.out.println("\nAll characters in HashMap:");
	    for (HumanCharacter h : characters.values()) {
	    	h.ready();
	    }
	    
	    System.out.println("\nChoose number for your character: (1:Ari, 2:Alex, 3:Steve)");
	    String choice = sc.nextLine().trim();
	    
	    HumanCharacter character = characters.get(choice);
	    
	    if (character != null) {
	    	performInitialActions(character);
	    	
	    	//メソッドをArrayListに格納し、繰り返し実行する
	    	ArrayList<Runnable> actions = new ArrayList<>();
	    	actions.add(() -> character.walk());
	    	actions.add(() -> character.jump());
	    	actions.add(() -> character.mining());
	    	actions.add(() -> character.greeting());
	    	
	    	//入力された回数、アクションを実行	        
	    	System.out.println("How many actions to perform?");
	    	int numActions = Integer.parseInt(sc.nextLine());
	    	java.util.Collections.shuffle(actions);
	    	for (int i = 0; i < numActions; i++) {
	    		int randomIndex = random.nextInt(actions.size()); // randomIndex をここで宣言
	    		actions.get(randomIndex).run();
	    	}
	    	
	    } else {
	    	System.out.println("Invalid choice. Exiting.");
	    }
	    
	    sc.close();
	}
}
