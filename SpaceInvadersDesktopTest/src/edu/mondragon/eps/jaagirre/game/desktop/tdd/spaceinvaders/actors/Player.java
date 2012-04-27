package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Actor{

	ArrayList<Shot> misiles;
	int misilNumber;
	int shields;
	
	public Player(BufferedImage image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
		misiles = new ArrayList<Shot>();
		misilNumber = 0;
		shields=3;
	}

	
	
	public ArrayList<Shot> getMisiles() {
		return misiles;
	}



	public void setMisiles(ArrayList<Shot> misiles) {
		this.misiles = misiles;
	}



	public Player(BufferedImage image, int x, int y) {
		super(image, x, y);
		// TODO Auto-generated constructor stub
	}

	public void setMisilNumber(int misilNumber) {
		this.misilNumber = misilNumber;
	}



	@Override
	public void executeBehaviour() {
		// TODO Auto-generated method stub
		int i = 0;
		
		for ( i = 0 ; i < misiles.size() ; i++){
			if ( (misiles.get(i).isVisible()) ){
				  misiles.get(i).executeBehaviour();
				  if (!( misiles.get(i).isVisible())  ){
					  this.misilNumber--;
				  }
				  
				 
			}
			
			
		}
	}
	
	public void moveRight(){
		this.setX( this.getX() +  2);
		
	}
	
	public void moveLeft(){
		this.setX( this.getX() -  2);
	}
	
	public void moveUp(){
		this.setY( this.getY() -  2);
	}

	public void moveDown(){
		this.setY( this.getY() +  2);
	}
	
	public void fire(){
		Shot misil = null;
		int i = 0;
		boolean aurkitua = false;
		do{
				misil = misiles.get(i);
				if ( !misil.isVisible() ){
					aurkitua = true;
				}
				i++;
		}while(!aurkitua &&  i < misiles.size() );
		if (aurkitua){
			misil.setVisible(true);
			misil.setX(this.getX());
			misil.setY(this.getY());
			
			this.misilNumber++;
		}	
		
	}


	public int getVisibleMisilsNumber(){
		int i = 0,ret = 0;
		for ( i = 0 ; i < getMisilNumber() ; i++){
			if ( misiles.get(i).isVisible() ){
				ret++;
			}
		}
		return ret;
	}
	
	public int getMisilNumber() {
		return misiles.size();
	}



	public int getShields() {
		return shields;
	}



	public void setShields(int shields) {
		this.shields = shields;
	}
	
	

}
