package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;

import java.awt.image.BufferedImage;

public class Alien extends Adversary {

	
	public Alien(String image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
	}

	public Alien(String image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
	}

	public Alien(String image, int x, int y, int xLimit, int yLimit, int width,
			int height, boolean visible) {
		super(image, x, y, xLimit, yLimit, width, height, visible);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeBehaviour() {
		// TODO Auto-generated method stub
		//debemos de conocer la anchura y altura de la pantalla
		//Bouncing alien
		this.setX( this.getX() +  this.getVx() );
		this.setY( this.getY() +  this.getVy() );
		
		if ( this.getX() +  this.getVx()  >  this.getxLimit()-1 ){
			this.setVx( this.getVx() *(-1) );
		}
		
		if ( this.getY() +  this.getVy()  >  this.getyLimit()-1 ){
			this.setVy( this.getVy() *(-1) );
		}
		
		if ( this.getX() +  this.getVx()  <  1 ){
			this.setVx( this.getVx() *(-1) );
		}
		
		if ( this.getY() +  this.getVy()  < 1 ){
			this.setVy( this.getVy() *(-1) );
		}
	}

}
