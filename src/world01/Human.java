package world01;

public interface Human {
	void reserve();
	void ready();
	void born();
	void displayInfo();
	default void walk() {};
	default void jump() {};
	default void mining() {};
	default void greeting() {};
}
