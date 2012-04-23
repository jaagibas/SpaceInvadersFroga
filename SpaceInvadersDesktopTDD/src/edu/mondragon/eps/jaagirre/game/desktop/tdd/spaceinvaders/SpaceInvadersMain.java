package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.util.ArrayList;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Alien;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;





public class SpaceInvadersMain {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		Game game = null;
		GameBoard pantalla = null;
		GameThread gameThread= null;
		GameEventListener listener;
		Actor []aliens;
		Player player = null;
	
		pantalla = new GameBoard();
		game = new Game( 400, 400, pantalla);
		gameThread= new GameThread(game);
		listener = new GameEventListener(game);
		
		game.getGameLogic().setBoard(pantalla);
		game.initWindow();
		game.addSprite("invasor" , "bicho.gif");
		game.addSprite("jugador" ,"nave.gif");
		game.addSprite("misil" ,"disparo1.gif");
		
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		
		aliens = new Actor[3];
		int i = 0;
		for ( i = 0 ; i < 3 ; i++){
			aliens[i] = new Alien( game.getSprite("invasor") ,  10 ,(i* 20)+10  ,  game.getWidth()-50 , game.getHeight()-100  ); 
			aliens[i].setVx(2);
			aliens[i].setVy(0);
			game.addActor(aliens[i]);
		}
		
		player = new Player( game.getSprite("jugador") , 200 , 300 , game.getWidth()-50 , game.getHeight()-100 );
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		game.getGameLogic().setPlayer(player);
		
		game.openWindow();
		
		
		//Comenzamos el hilo del juego
		gameThread.start();
		
		

	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameBoard pantalla = new GameBoard();
		pantalla.setBackground(Color.black);
		//BoardMock pantalla = new BoardMock();
		Game game = new Game(400,400, pantalla);
		GameText testBufferedText = new GameText("kaixo" , 50 , 50);
		GameText testBuffering = new GameText("Buffering" , 70 , 70);
		pantalla.addText( testBufferedText );
		game.initWindow();
				
		game.openWindow();
		testBufferedText.setX(80);
		testBufferedText.setX(90);
		pantalla.addText( testBuffering );
		pantalla.paint( pantalla.getGraphics());
		
	}*/

}
