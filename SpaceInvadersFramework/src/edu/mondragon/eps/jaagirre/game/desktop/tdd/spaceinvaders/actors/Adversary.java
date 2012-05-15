package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;


abstract  public class Adversary extends Actor {

	public Adversary(String image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
		setVx(0);
		setVy ( 0);
	}
	
	public Adversary(String image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
	}

	public Adversary(String image, int x, int y, int xLimit, int yLimit,
			int width, int height, boolean visible) {
		super(image, x, y, xLimit, yLimit, width, height, visible);
		// TODO Auto-generated constructor stub
	}


}
