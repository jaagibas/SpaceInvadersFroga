package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;


public class Shot extends Actor {

	public Shot(String image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
		this.setVx(0);
		this.setVy(-2);
		this.setVisible(false);
	}

	public Shot(String image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
		this.setVx(0);
		this.setVy(-2);
		this.setVisible(false);
	}

	public Shot(String image, int x, int y, int xLimit, int yLimit, int width,
			int height, boolean visible) {
		super(image, x, y, xLimit, yLimit, width, height, visible);
		// TODO Auto-generated constructor stub
		this.setVx(0);
		this.setVy(-2);
		this.setVisible(false);
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
