package world01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LifeStarts {

	//var name2 = "life"; //プロパティ変数の時はvarは使用できない
	public static void performInitialActions(Human character) {
        character.born();
        character.displayInfo();
    }
//	class ActionRunnable implements Runnable {
//
//		@Override
//		public void run() {
//		    System.out.println("Runnableが実行されました1");
//		    System.out.println("Runnableが実行されました2");
//		}
//	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	Map<String, HumanCharacter> characters = new HashMap<>();
	Random random = new Random();
	FishList fishList = new FishList(); // MySQLデータベース管理
	
//	var r = new Random(); //Randomオブジェクトを宣言　F3がここにジャンプ var...変数の型として、ほぼ同じを指定する場合省略するためにvarを使用する
//	System.out.println("ｒの型は" + r);
//	
//	var text = "Hello";//型推論
//	System.out.println("textの型は" + text);
//	
//	var math = 1L;
//	System.out.println("mathの型は" + math);
	
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
	    
	    // インターフェースのArrayList
	    ArrayList<Human> characterList = new ArrayList<>();
	    characterList.add(new Ari());
	    characterList.add(new Alex());
	    characterList.add(new Steve());
	    System.out.println("\nAll characters in Arraylist: ");
	    for (Human h : characterList) {
	    	h.displayInfo();
	    }
	    
	    // HashMapのループ
	    System.out.println("\nAll characters in HashMap:");
	    for (HumanCharacter h : characters.values()) {
	    	h.ready();
	    }
	    
	    // キャラクター選択
	    System.out.println("\nChoose number for your character: (1:Ari, 2:Alex, 3:Steve)");
	    String choice = sc.nextLine().trim();
	    
	    HumanCharacter character = characters.get(choice);
	    
	    if (character != null) {
	    	performInitialActions(character);
	    	
	    	// 釣りメニュー
            while (true) {
                System.out.println("\nWhat would you like to do?");
                System.out.println("1: Go fishing");
                System.out.println("2: Check fish list");
                System.out.println("3: Exit");
                String action = sc.nextLine().trim();
                
                if (action.equals("1")) {
                    System.out.println(character.name + " casts a fishing rod into the water..."); // 釣りはLifeStartsで処理
                    Fish caughtFish = catchFish(random);
                    if (caughtFish != null) {
                        System.out.println(character.name + " caught a " + caughtFish.getFishNAME() + "!");
                        fishList.addFish(caughtFish);
                    } else {
                        System.out.println("Nothing was caught this time.");
                    }
                } else if (action.equals("2")) {
                    java.util.List<Fish> allFish = fishList.getAllFish();
                    if (allFish.isEmpty()) {
                        System.out.println("No fish caught yet!");
                    } else {
                        System.out.println("Fish List:");
                        for (int i = 0; i < allFish.size(); i++) {
                            System.out.println((i + 1) + ": " + allFish.get(i));
                        }
                    }
                } else if (action.equals("3")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
        
        sc.close();
    }

    private static Fish catchFish(Random random) {
        int[][] fishData = {
            {1, random.nextInt(10000)}, // Cod
            {2, random.nextInt(10000)}, // Salmon
            {3, random.nextInt(10000)}, // Pufferfish
            {4, random.nextInt(10000)}  // Tropical Fish
        };
        String[] fishNames = {"Cod", "Salmon", "Pufferfish", "Tropical Fish"};
        
        int chance = random.nextInt(100);
        if (chance < 70) {
            int index = random.nextInt(fishData.length);
            int fishID = fishData[index][0];
            int fishNO = fishData[index][1]; // 仮値、DBで上書き
            String fishNAME = fishNames[index];
            return new Fish(fishID, fishNAME, fishNO);
        } else {
            return null;
        }
    }
}