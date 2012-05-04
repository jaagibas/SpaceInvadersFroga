package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.utils;

import java.awt.Rectangle;

public class RectangleAWT implements RectangleInterface {

	@Override
	public boolean intersects(Square s1 , Square s2 ) {
		// TODO Auto-generated method stub
		Rectangle r1 = new Rectangle(s1.getX0() , s1.getY0() , s1.getWidth() , s1.getHeight());
		Rectangle r2 = new Rectangle( s2.getX0() , s2.getY0() , s2.getWidth() , s2.getHeight() );
				
		return r1.intersects(r2);
	}

}
