package com.cubic;

class Dog {
	String name = "Rommy";
	String color="white";
	int tail = 1;

	void walk() {
		System.out.println("Dog is walking on road!");
	}

	void bark() {
		System.out.println("Dog  barks at night!");
	}
	
	void info() {
		System.out.println("name = "+name);
		System.out.println("color = "+color);
		System.out.println("tail = "+tail);
	}
}

public class DogMain {

	public static void main(String[] args) {
		Dog  pk =new Dog();
		pk.bark();
		pk.walk();
		pk.info();
		System.out.println("#############################################");
		Dog  tk =new Dog();
		System.out.println(tk.name);
		tk.bark();
		tk.walk();
		tk.info();
		
	}
}
