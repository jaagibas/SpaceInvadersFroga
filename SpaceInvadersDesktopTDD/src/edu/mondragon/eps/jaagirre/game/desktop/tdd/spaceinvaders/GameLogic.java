package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.awt.Rectangle;
import java.util.ArrayList;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;

public class GameLogic {
	protected ArrayList<Actor>  actors;
	private Player player;
	private GameBoard board;
	private boolean gameWin;
	private boolean gameOver;
	
	private ArrayList<Level> levels;
	
	public GameLogic() {
		super();
		this.actors = new ArrayList<Actor>();
		this.gameWin = false;
		this.gameOver = false;
	}
	
	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public void addActor(Actor actor){
		actors.add(actor);
		board.getImageBuffer().add(actor);
	}
	
	public void setPlayer(Player player){
		this.player = player;
		board.getImageBuffer().add(player);
		board.getImageBuffer().addAll(player.getMisiles());
	}
	
	public void removeActor(Actor actor){
		int i = 0;
		actors.remove(actor);
		board.getImageBuffer().remove(actor);
		
	}
	
	public void removePlayer(Player player){
		player = null;
		board.getImageBuffer().remove(player);
	}
	
	public void update(){
		int i = 0;
		for ( i = 0 ; i < actors.size() ; i++){
			actors.get(i).executeBehaviour();
		}
		player.executeBehaviour();
		checkCollisions();
		if ( checkGame() ){
			this.finishGame();
		}
		
		
	}
	
	public boolean checkGame(){
		
		boolean ret = false;
		String text = null;
		if ( actors.size() == 0){
			this.gameWin= true;
			//agregamos texto final
			text = new String("Zorionak!!!!!");
			board.textBuffer.add( new GameText(text, 200,200) );
			ret = true;
		}else if (player.getShields() == 0){
			this.gameOver = true;
			text = new String("Game Over.");
			board.textBuffer.add( new GameText(text, 200,200) );
			//agregamos texto final
			ret = true;
		}
		return ret;
	
	}
	
	public void checkCollisions(){
		Actor alien = null;
		int i  = 0;
		boolean aurkitua = false;
		int size =  actors.size();
		int misilIndex = 0;
		int alienNumber = 0;
		//Between Aliens and Misil
		misilIndex = 0;
		do{
			Shot misil = player.getMisiles().get(misilIndex);
			alienNumber = this.getActorsNumber();
			i = 0;
			aurkitua = false;
			while (!aurkitua &&  i < alienNumber){
				alien = actors.get(i);
				Rectangle rAlien = new Rectangle(alien.getX() , alien.getY() , alien.getWidth() , alien.getHeight());
				Rectangle rMisil = new Rectangle( misil.getX() , misil.getY() , misil.getWidth() , misil.getHeight() );
				if ( 
					    rAlien.intersects( rMisil) 
						&&
						( misil.isVisible() )
					){
						aurkitua = true;
						this.removeActor(alien);
						misil.setVisible(false);
				}
				i++;
			}
			misilIndex++;
		}while(misilIndex < player.getMisilNumber()  );
		
		//Between Player and aliens
		aurkitua = false;
		i = 0;
		size = this.getActorsNumber();
		while( i < size && !aurkitua ){
			alien = actors.get(i);
			Rectangle rAlien = new Rectangle(alien.getX() , alien.getY() , alien.getWidth() , alien.getHeight());
			Rectangle rPlayer = new Rectangle( player.getX() , player.getY() , player.getWidth() , player.getHeight() );
			if ( 
					rAlien.intersects(rPlayer)
				){
				aurkitua = true;
			}
			i++;			
		}
		if (aurkitua){
			this.removeActor(alien);
			player.setShields(player.getShields()-1);
			player.setX(200);
			player.setY(300);
		}
	}
	
	public void finishGame(){
		int i = 0;
		for (i = 0 ; i < actors.size(); i++){
			Actor actor = actors.get(i);
			this.removeActor(actor);
		}
		this.removePlayer(this.player);
		
	}
	
	public Player getPlayer() {
		return player;
	}

	public int getActorsNumber(){
		return actors.size();
	}

	public boolean isGameWin() {
		return gameWin;
	}

	public void setGameWin(boolean gameWin) {
		this.gameWin = gameWin;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}
	
	

}
