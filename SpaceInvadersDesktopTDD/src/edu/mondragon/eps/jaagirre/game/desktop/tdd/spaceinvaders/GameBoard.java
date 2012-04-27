package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

public class GameBoard extends Canvas {
	
	ArrayList<GameText> textBuffer;
	ArrayList<Actor> imageBuffer;
	//backgroundBuffer
	private HashMap<String,BufferedImage> sprites;
	
	public GameBoard() {
		super();
		// TODO Auto-generated constructor stub
		textBuffer = new ArrayList<GameText>();
		imageBuffer = new ArrayList<Actor>();
	}
	
	public ArrayList<GameText> getTextBuffer() {
		return textBuffer;
	}

	public void setImageBuffer(ArrayList<Actor> imageBuffer) {
		this.imageBuffer = imageBuffer;
	}

	public ArrayList<Actor> getImageBuffer() {
		return imageBuffer;
	}

	public void addText(GameText str){
		textBuffer.add( str );
	}
	
	public void addActor(Actor image){
		imageBuffer.add( image );
	}
	
	public void paintText(Graphics g) {
		// TODO Auto-generated method stub
		int i = 0;
		GameText bufferedText;
		g.setColor(Color.RED);
		for (i = 0 ; i < textBuffer.size() ; i++){
			bufferedText = (GameText)textBuffer.get(i);
			g.drawString(  bufferedText.getText() ,  bufferedText.getX() , bufferedText.getY()  );
		}
	}
	
	public void paintImage(Graphics g) {
		// TODO Auto-generated method stub
		int i = 0;
		Actor actor;
		g.setColor(Color.RED);
		for (i = 0 ; i < imageBuffer.size() ; i++){
			actor = (Actor)imageBuffer.get(i);
			if (actor.isVisible()){
				g.drawImage( actor.getImage() ,  actor.getX() , actor.getY(), null  );
			}
			
		}
	}
	
	public void clearBoard(Graphics g){
		this.setBackground(Color.BLACK);
		//g.setColor(Color.black);
	    //g.fillRect(0,0,getWidth(),getHeight());
		g.clearRect(0, 0, getWidth(),getHeight());
	}
	

	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		clearBoard(g); //no es necesario
	    paintText(g);
	    paintImage(g);
	}
	
	
	public BufferedImage getSprite(String id){
		return null;
	}

}



