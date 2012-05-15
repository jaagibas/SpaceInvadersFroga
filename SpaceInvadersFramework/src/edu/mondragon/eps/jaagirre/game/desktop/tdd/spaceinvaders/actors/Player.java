package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;

import java.util.ArrayList;

public class Player extends Actor{

	ArrayList<Shot> misiles;
	int misilNumber;
	int shields;
	int v;
	
	public Player(String image, int x, int y, int xLimit, int yLimit) {
		super(image, x, y, xLimit, yLimit);
		// TODO Auto-generated constructor stub
		misiles = new ArrayList<Shot>();
		misilNumber = 0;
		shields=3;
	}

	
	
	public Player(String image, int x, int y, int xLimit, int yLimit,
			int width, int height, boolean visible) {
		super(image, x, y, xLimit, yLimit, width, height, visible);
		// TODO Auto-generated constructor stub
		misiles = new ArrayList<Shot>();
		misilNumber = 0;
		shields=3;
	}


	
	public int getV() {
		return v;
	}



	public void setV(int v) {
		this.v = v;
	}

	public ArrayList<Shot> getMisiles() {
		return misiles;
	}



	public void setMisiles(ArrayList<Shot> misiles) {
		this.misiles = misiles;
	}



	public Player(String image, int x, int y) {
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
		this.setX( this.getX() +  v);
		
	}
	
	public void moveLeft(){
		this.setX( this.getX() -  v);
	}
	
	public void moveUp(){
		this.setY( this.getY() -  v);
	}

	public void moveDown(){
		this.setY( this.getY() +  v);
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
