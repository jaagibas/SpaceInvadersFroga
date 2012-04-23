package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;

import java.awt.image.BufferedImage;

public class Shot extends Actor {

	public Shot(BufferedImage image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
		this.setVx(0);
		this.setVy(-2);
		this.setVisible(false);
	}

	public Shot(BufferedImage image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeBehaviour() {
		// TODO Auto-generated method stub
		if (  this.isVisible() ){
			this.setX( this.getX() +  this.getVx() );
			this.setY( this.getY() +  this.getVy() );
		
			if ( 
					( this.getX() + this.getVx() >= this.getxLimit() || this.getX() + this.getVx() <= 0  )  
					|| 
					( this.getY() +  this.getVy()  >= this.getyLimit() || this.getY() +  this.getVy() <= 0  )
				){
					this.setVisible(false);
			}
		}
	}

}
