package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.util.ArrayList;
import java.util.Arrays;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Alien;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.AlienHV;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.PlayerWithCteSpeed;
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
		Actor []aliens2;
		//Player player = null;
		PlayerWithCteSpeed player = null;
		
		pantalla = new GameBoard();
		game = new Game( 400, 400, pantalla);
		gameThread= new GameThread(game);
		listener = new GameEventListener(game);
		
		game.getGameLogic().setBoard(pantalla);
		game.initWindow();
		game.addSprite("invasor" , "bicho.gif");
		game.addSprite("jugador" ,"nave.gif");
		game.addSprite("misil" ,"disparo1.gif");
		game.addSprite("invasorHV" ,"the_new_blue_space_ship.gif");
		
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		
		aliens = new Actor[3];
		int i = 0;
		for ( i = 0 ; i < 3 ; i++){
			aliens[i] = new Alien( "invasor" ,  10 ,(i* 20)+10  ,  game.getWidth()-50 , game.getHeight()-100 ,
									game.getBoard().getSprite("invasor").getWidth() , game.getBoard().getSprite("invasor").getHeight() , true ); 
			aliens[i].setVx(2);
			aliens[i].setVy(0);
			//game.addActor(aliens[i]);
		}
		
		Level level = new Level();
        ArrayList<Actor> actors = new ArrayList<Actor>(Arrays.asList(aliens));
        level.setLevel(actors);
        game.getGameLogic().addLevel(level);
        game.getGameLogic().setActorsInBoard();
        
        aliens2 = new Actor[6];
        for ( i = 0 ; i < 3 ; i++){
              
               aliens2[i] = new Alien( "invasor" ,  10 ,(i* 20)+10  ,  game.getWidth()-50 , game.getHeight()-100 , 
            		   			game.getBoard().getSprite("invasor").getWidth() , game.getBoard().getSprite("invasor").getHeight() , true);
               aliens2[i].setVx(2);
               aliens2[i].setVy(0);
               //game.addActor(aliens[i]);
        }
        for ( i = 3 ; i < 6 ; i++){
              
               aliens2[i] = new AlienHV( "invasorHV" ,  10*i ,(i* 20)+10  ,  game.getWidth()-50 , game.getHeight()-100  ,
            		   					game.getBoard().getSprite("invasorHV").getWidth() , game.getBoard().getSprite("invasorHV").getHeight() , true );
               aliens2[i].setVx(2);
               aliens2[i].setVy(0);
               //game.addActor(aliens2[i]);
        }
        
        //Segundo nivel
        Level level2 = new Level();
        ArrayList<Actor> actors2 = new ArrayList<Actor>(Arrays.asList(aliens2));
        level2.setLevel(actors2);
        game.getGameLogic().addLevel(level2);
		
		
		//player = new Player( game.getSprite("jugador") , 200 , 300 , game.getWidth()-50 , game.getHeight()-100 );
        player = new PlayerWithCteSpeed( "jugador" , 200 , 300 , game.getWidth()-50 , game.getHeight()-100,
        								game.getBoard().getSprite("jugador").getWidth() , game.getBoard().getSprite("jugador").getHeight() , true);
		player.setV(2);
        ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( "misil" ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100  , 
							game.getBoard().getSprite("misil").getWidth()  , 
							game.getBoard().getSprite("misil").getHeight() , false) );
		misiles.add(new Shot( "misil" ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 , 
							game.getBoard().getSprite("misil").getWidth() , game.getBoard().getSprite("misil").getHeight() , false ) );
		misiles.add(new Shot( "misil" ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100  , 
							game.getBoard().getSprite("misil").getWidth() , game.getBoard().getSprite("misil").getHeight() , false ) );
		
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
