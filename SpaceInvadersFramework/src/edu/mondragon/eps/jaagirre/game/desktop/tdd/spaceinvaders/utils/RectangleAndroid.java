package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.utils;

import android.graphics.Rect;



public class RectangleAndroid implements RectangleInterface {

	@Override
	public boolean intersects(Square s1, Square s2) {
		// TODO Auto-generated method stub
		Rect r1 = new Rect( s1.x0 , s1.y0 , s1.x1 , s1.y1);
		Rect r2 = new Rect( s2.x0 ,  s2.y0 , s2.x1 , s2.y1);
				
		return r1.intersect(r2);
	}

}
