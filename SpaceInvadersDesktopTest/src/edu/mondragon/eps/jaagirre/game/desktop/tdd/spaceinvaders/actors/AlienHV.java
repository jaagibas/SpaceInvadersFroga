package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;

import java.awt.image.BufferedImage;

public class AlienHV extends Adversary {

	public AlienHV(BufferedImage image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
	}

	public AlienHV(BufferedImage image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeBehaviour() {
		// TODO Auto-generated method stub
		this.setX( this.getX() +  this.getVx() );
		this.setY( this.getY() +  this.getVy() );
		
		if ( this.getX() +  this.getVx()  >  this.getxLimit()-1 ){
			this.setY(this.getY() + 2 );
			this.setVx( this.getVx() *(-1) );
		}
		
		if ( this.getY() +  this.getVy()  >  this.getyLimit()-1 ){
			this.setY( 2 );
		}
		
		if ( this.getX() +  this.getVx()  <  1 ){
			this.setY(this.getY() + 2 );
			this.setVx( this.getVx() *(-1) );
		}
		
		
	}

}
