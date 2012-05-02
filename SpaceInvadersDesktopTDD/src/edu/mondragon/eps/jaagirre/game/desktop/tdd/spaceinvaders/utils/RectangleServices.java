package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.utils;

public class RectangleServices {

	RectangleInterface ri;
	
	public RectangleServices(){
		ri = new RectangleAWT();
	}
	
	public boolean intersects(Square s1 , Square s2){
		return ri.intersects(s1, s2);
	}
	
}
