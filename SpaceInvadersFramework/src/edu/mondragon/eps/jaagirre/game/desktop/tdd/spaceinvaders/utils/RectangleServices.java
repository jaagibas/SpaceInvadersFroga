package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*Hay que crear y agregar clase RectangleAndroid*/
public class RectangleServices {

	RectangleInterface ri;
	public static final int PLATFORM_ANDROID=0;
	public static final int PLATFORM_SWING=1;
	
	public RectangleServices(){
		
		if ( System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik") ){
			ri = new RectangleAndroid();
		}else{
			ri = new RectangleAWT();
		}
			
		
	}
	
	public boolean intersects(Square s1 , Square s2){
		return ri.intersects(s1, s2);
	}
	
}
