package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;

import java.awt.image.BufferedImage;

abstract  public class Adversary extends Actor {

	public Adversary(BufferedImage image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
		setVx(0);
		setVy ( 0);
	}
	
	public Adversary(BufferedImage image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
	}


}
