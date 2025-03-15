package world01;

public abstract class HumanCharacter implements Human {
    protected String name;
    protected String hairColor;
    protected String skinColor;
    protected String clothing;
    
    public HumanCharacter(String name, String hairColor, String skinColor, String clothing) {
    	this.name = name;
    	this.hairColor = hairColor;
    	this.skinColor = skinColor;
    	this.clothing = clothing;
    }
    
    @Override
    public void reserve() {
    	System.out.println(name + "is about to be born into this world!");
    }
    
    @Override
    public void ready() {
    	System.out.println(name +"is about to go out into the world");
    }
    
	@Override
    public void born() {
		System.out.println(name + "has been born into the wirld!");
    }
	
	@Override
	public void displayInfo() {
		System.out.println("name: " + name);
		System.out.println("Hair Color: " + hairColor);
		System.out.println("Skin Color: " + skinColor);
		System.out.println("clothing: " + clothing);
	}
	
	//HumanCharacterで動作を定義
	@Override
    public void walk() {
		System.out.println(name + " walks confidently in" + clothing + ".");
	}
	
	@Override
	public void jump() {
		System.out.println(name + " jumps high with" + hairColor + "hair flowing!");
    }
	
    @Override
    public void mining() {
    	System.out.println(name + " mines resouces with" + skinColor + "hands.");
    }
    
	@Override
    public void greeting() {   
		System.out.println(name + " greetings you warmly");
	}
}
    
    