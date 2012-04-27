package edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests;




import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests.mocks.BoardMock;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Game;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameBoard;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameEventListener;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameText;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameThread;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Alien;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;

public class SpaceInvadersWindowTest {

	
	
	@Test
	public void testActorImageCreation() {
		BoardMock pantalla = new BoardMock();
		Game game = new Game( 400, 400, pantalla);
		game.getGameLogic().setBoard(pantalla);
		
		game.initWindow();
		game.addSprite("invasor" , "bicho.gif");
		game.addSprite("jugador" ,"nave.gif");

		Assert.assertEquals("Creacion de sprites",
				2,
				game.getSpritesNumber()
		);
		if ( game.getSpritesNumber() != 2 ){
			Assert.fail("Error en la creacion de Imagenes.");
		}
		
		Actor []aliens;
		aliens = new Actor[5];
		int i = 0;
		for ( i = 0 ; i < 5 ; i++){
			aliens[i] = new Alien( game.getSprite("invasor") ,  10 ,i* 10  ); 
			game.addActor(aliens[i]);
		}
		
		Assert.assertEquals("Creacion de invasores con sus imagenes",
									aliens.length,
									game.getGameLogic().getActorsNumber()
							);
		
		
		game.openWindow();
		pantalla.paint( pantalla.getGraphics() );
		

		Assert.assertEquals("Pintando imagenes en pantalla", 
									5,
									pantalla.getGraphicsMock().getImageBuffer().size()
							);
		for (i = 0 ; i < 5 ; i ++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					10,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" , 
					i*10,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getY() 
			);
			
		}
		
		
		game.closeWindow();
		
		
	}
	
	
	@Test
	public void testDoubleBuffering() {
		//quemeros testeas la funcion clearBoard
		//Lo haremos llamando  explcitamente a la funcion
		BoardMock pantalla = new BoardMock();
		Game game = new Game( 400, 400, pantalla);
			
		GameText testBufferedText = new GameText("kaixo" , 50 , 50);
		pantalla.addText(  testBufferedText);
		game.initWindow();
			
		game.openWindow();
		try{
				Thread.sleep(100);
		}catch(Exception e){
				
		}
			
		GameText testBuffering = new GameText("Buffering" , 70 , 70);
		pantalla.addText(  testBuffering);
		pantalla.paint( pantalla.getGraphics() );
		
		Assert.assertEquals("Double buffering",  ( (GameBoard)game.getBoard() ).getTextBuffer().size() , ( (BoardMock)game.getBoard() ).getGraphicsMock().getTextBuffer().size()  );
		Assert.assertEquals("Double buffering 2",  2 , ( (BoardMock)game.getBoard() ).getGraphicsMock().getTextBuffer().size()  );
			
		game.closeWindow();
				
	}
	

	@Test
	public void testBoardCreationAutomatic() {
		/*1-Creamos la clase que contiene la ventana del juego y el canvas*/
		BoardMock pantalla = new BoardMock();
		Game game = new Game( 400, 400, pantalla);
		/*2- Validamos si se abre la ventana , la posicion ,
		 y si tiene un Canvas dentro con un texto.
		 */
		GameText testBufferedText = new GameText("kaixo" , 50 , 50);
		pantalla.addText(  testBufferedText);
		game.initWindow();
		/*Ahota tenemos que chequear si esto hay ido bien o no*/
		/*Primero existe ventana y es la correcta, existe panal , y existe canvas*/
		Assert.assertNotNull( "Creacion ventana", game.getWindow() );
		Assert.assertNotNull( "Obtencion panel", game.getPanel() );
		Assert.assertNotNull( "Creacion canvas", game.getBoard() );
		Assert.assertEquals( "Anchura Ventana", 400 ,game.getWindow().getWidth());
		Assert.assertEquals( "Altura Ventana", 400 ,game.getWindow().getHeight());
		Assert.assertEquals( "Anchura Canvas/Panel", 400 ,game.getBoard().getWidth());
		Assert.assertEquals( "Altura Canvas/Panel", 400 ,game.getBoard().getHeight());
		/*Panel contiene canvas*/
		
		/*Tamano corrceto panel , y canvas*/
		
		/*Ubicacion de canvas correcto*/
		
		/*3*/
		/*Pare ser por el tema de hilos entre el main y el hilo AWT
		 De momento le metemos un Sleep*/
		game.openWindow();
		try{
			Thread.sleep(100);
		}catch(Exception e){
			
		}
		/*Hau ez dakit zergatik egin behar dan*/
		
		/*Frame visible, panel visible, canvas visible
		 */
		Assert.assertTrue( "Ventana visible",game.getPanel().isVisible() );
		Assert.assertTrue( "Canvas Visible" ,game.getBoard().isShowing());
		//Assert.assertTrue("Ha escrito algo", game.getTextBuffer().size() > 0);
		//seria perfecto hacer un getXY()XY
		Assert.assertEquals( "Pintar texto Kaixo", testBufferedText.getText() , ( (BoardMock)game.getBoard() ).getPaintedText(50, 50).getText() );
		/*4-Comprobamos si se cierra*/
		game.closeWindow();

		Assert.assertFalse( "Ventana Cerrada",game.getWindow().isVisible() );
		
		
	}
	
	
	
	@Test
	public void testCompleteBoardCreation() {
		BoardMock pantalla = new BoardMock();
		Game game = new Game( 400, 400, pantalla);
		game.getGameLogic().setBoard(pantalla);
		
		game.initWindow();
		game.addSprite("invasor" , "bicho.gif");
		game.addSprite("jugador" ,"nave.gif");
		game.addSprite("misil" ,"disparo1.gif");

		Assert.assertEquals("Creacion de sprites",
				3,
				game.getSpritesNumber()
		);
		if ( game.getSpritesNumber() != 3 ){
			Assert.fail("Error en la creacion de Imagenes.");
		}
		
		Actor []aliens;
		aliens = new Actor[5];
		int i = 0;
		for ( i = 0 ; i < 5 ; i++){
			aliens[i] = new Alien( game.getSprite("invasor") ,  10 ,i* 10  ); 
			game.addActor(aliens[i]);
		}
		Player player = null;
		player = new Player( game.getSprite("jugador") , 200 , 300);
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		game.getGameLogic().setPlayer(player);
		
		Assert.assertNotNull("Creacion  de jugador con sus imagenes en la logiva del juego",
								game.getGameLogic().getPlayer()
							);
		
		game.openWindow();
		pantalla.paint( pantalla.getGraphics() );
		
		try{
			Thread.sleep(100);
		}catch(Exception e){
			
		}
		
		Assert.assertEquals("Pintando  imagenes  de inavsores ,jugador en pantalla", 
									6,
									pantalla.getGraphicsMock().getImageBuffer().size()
							);
		
		for (i = 0 ; i < 5 ; i ++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					10,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" , 
					i*10,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getY() 
			);
		}
		
		Assert.assertEquals("Posicion X Jugador en pantalla incorrecta" ,
				200,
				pantalla.getGraphicsMock().getImageBuffer().get(i).getX() 
		);
		Assert.assertEquals("Posicion X Jugador en pantalla incorrecta" , 
				300,
				pantalla.getGraphicsMock().getImageBuffer().get(i).getY() 
		);
		
		
		
		game.closeWindow();
					
	}
	
	@Test
	public void testGameThreadCreation() {
		BoardMock pantalla;
		Game game;
		Actor []aliens;
		Player player = null;
	
		pantalla = new BoardMock();
		game = new Game( 400, 400, pantalla);
		game.getGameLogic().setBoard(pantalla);
		game.initWindow();
		game.addSprite("invasor" , "bicho.gif");
		game.addSprite("jugador" ,"nave.gif");
		game.addSprite("misil" ,"disparo1.gif");
		
		aliens = new Actor[1];
		int i = 0;
		for ( i = 0 ; i < 1 ; i++){
			aliens[i] = new Alien( game.getSprite("invasor") ,  10 ,(i* 20)+2  ,  game.getWidth()-50 , game.getHeight()-100  ); 
			aliens[i].setVx(0);
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
		
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		//Comenzamos el hilo del juego
		GameThread gameThread = new GameThread(game);
		gameThread.start();
		
		
		//Eliminar todos los invasores
		int shotIndex = 0;
		int initialAlienNumber = game.getGameLogic().getActorsNumber();
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		
	
		
		player.setX( game.getGameLogic().getActors().get(shotIndex).getX());
		player.setY( game.getGameLogic().getActors().get(shotIndex).getY()+100);
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
		try{
			Thread.sleep(50);
		}catch(Exception e){
			
		}
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
		try{
			Thread.sleep(100);
		}catch(Exception e){
			
		}
		do{
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}while( !(player.getMisiles().get(0).isVisible()) );
		do{
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}while( player.getMisiles().get(0).isVisible() );
		
		Assert.assertTrue("Invasores eliminados.", game.getGameLogic().isGameWin() );
		try{
			Thread.sleep(100);
		}catch(Exception e){
			
		}
		Assert.assertFalse("Hilo de juego finalizado", gameThread.isRunning());
		
	}
	
	
	
	@Test
	public void testWellcomeScreen() {
		
	}

	
	
	
	
	
	
	
	
	

}
