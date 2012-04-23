package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

public class GameThread  extends Thread{
	private boolean running;
	Game game;
	public GameThread(Game game){
		this.game = game;
		running = false;
	}
		//gameLogic.update();
		//Canvas tiene que tener una refencia al gameLogic y nada mas de manera que lo sbuffer sean del otro
	@Override
	public void run() {
		running = true;
		GameLogic gameLogic = this.game.getGameLogic();
		while( !gameLogic.isGameOver() && !gameLogic.isGameWin() ){
			game.update();
			game.getBoard().paint( game.getBoard().getGraphics());
			try{
             	sleep(100);
			}catch(Exception e){}
		}
		running = false;
		 
	}
	public boolean isRunning() {
		return running;
	}	
	
}
