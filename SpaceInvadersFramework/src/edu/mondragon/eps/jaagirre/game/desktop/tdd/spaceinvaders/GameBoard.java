package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;



import java.util.ArrayList;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

public abstract class  GameBoard {
	
	protected ArrayList<GameText> textBuffer;
	protected ArrayList<Actor> imageBuffer;
	
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
	
	public void addLevelText(GameText str){
		removeLevelText();
		textBuffer.add( str );
	}
	
	
	public void removeLevelText(){
		int i;
		GameText text = null;
		for (i = 0 ; i < textBuffer.size() ; i++){
			
			if ( textBuffer.get(i).getText().contains("maila") ){
				text = textBuffer.get(i);
			}
		}
		textBuffer.remove(text);
	}
	
	public void addActor(Actor image){
		imageBuffer.add( image );
	}
	
	public abstract void paintBoard();
	public abstract void createSprite(String nombre, String key);
	public abstract boolean existsSprite(String key);
	public abstract int getSpriteWidth(String key);
	public abstract int getSpriteHeight(String key);
	public abstract void initBoard(int x, int y, int width , int height );

}



