package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;


public class AlienVertical extends Adversary {

	public AlienVertical(String image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
		this.setVx(0);
		this.setVy(2);
	}

	public AlienVertical(String image, int x, int y, int xLimit,
			int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
		this.setVx(0);
		this.setVy(2);
		
	}

	public AlienVertical(String image, int x, int y, int xLimit, int yLimit,
			int width, int height, boolean visible) {
		super(image, x, y, xLimit, yLimit, width, height, visible);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeBehaviour() {
		// TODO Auto-generated method stub
		this.setX( this.getX() +  this.getVx() );
		this.setY( this.getY() +  this.getVy() );
		
	
		if ( this.getY() +  this.getVy()  >  this.getyLimit()-1 ){
			this.setY(2);
		}
		
	}
	

}
