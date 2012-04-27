package edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests;

import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests.mocks.BoardMock;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Game;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameEventListener;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Alien;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;
public class GameTest {
	BoardMock pantalla;
	Game game;
	Actor []aliens;
	Player player = null;
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		pantalla = new BoardMock();
		game = new Game( 400, 400, pantalla);
		game.getGameLogic().setBoard(pantalla);
		game.initWindow();
		game.addSprite("invasor" , "bicho.gif");
		game.addSprite("jugador" ,"nave.gif");
		game.addSprite("misil" ,"disparo1.gif");
		
		aliens = new Actor[5];
		int i = 0;
		for ( i = 0 ; i < 5 ; i++){
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
		player.setV(2);
		game.getGameLogic().setPlayer(player);
		
		game.openWindow();
		pantalla.paint( pantalla.getGraphics() );
		
		
	}

	@After
	public void tearDown() throws Exception {
		game.closeWindow();
	}

	@Test
	public void testAlienRightMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(2);
			aliens[i].setVy(0);
		}
		game.update();
		pantalla.paint( pantalla.getGraphics());
		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					10+2,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" , 
					(i* 20)+2,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getY() 
			);
		}
		
	}
	@Test
	public void testAlienBouncingMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(2);
			aliens[i].setVy(0);
			aliens[i].setX(349);
		}
		game.update();
		pantalla.paint( pantalla.getGraphics());
		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					349,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" , 
					(i* 20)+2,
					pantalla.getGraphicsMock().getImageBuffer().get(i).getY() 
			);
			Assert.assertEquals("Velocidad Alien "+ i +"en pantalla incorrecta" ,
					-2,
					aliens[i].getVx() 
			);
		}
	}
	@Test
	public void testGameEvents() {
		System.out.println("Windoe closing to Simulated."); 
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
	
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue(); 
		eventQueue.postEvent( new WindowEvent( game.getWindow() ,  WindowEvent.WINDOW_CLOSING ) );
		try{
			Thread.sleep(200);
		}catch(Exception e){
			
		}
		Assert.assertFalse( "Ventana Cerrada",game.getWindow().isVisible() );
	}
	
		
	@Test
	public void testPlayerMovement() {
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue(); 
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED )  );
		try{
			Thread.sleep(300);
		}catch(Exception e){
			
		}
		pantalla.paint( pantalla.getGraphics() );
		Assert.assertEquals("Posicion X Jugador " ,
				200+2,
				pantalla.getGraphicsMock().getImageBuffer().get(5).getX() 
		);
		
		
		
	}
	
	
	
	@Test
	public void testPlayerMovementWithKeypad() {
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		player.setX(349);
		
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue(); 
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED )  );
		try{
			Thread.sleep(200);
		}catch(Exception e){
			
		}
		pantalla.paint( pantalla.getGraphics() );
		Assert.assertEquals("Posicion X Jugador " ,
				349,
				pantalla.getImageBuffer().get(5).getX() 
		);
		Assert.assertEquals("Posicion X Jugador " ,
				349,
				pantalla.getGraphicsMock().getImageBuffer().get(5).getX() 
		);
		
	}
	
	@Test
	public void testPlayerMovementWithTouchScreen() {
		
	}

	@Test
	public void testMovement() {
		
	}
	
	@Test
	public void testCollision() {
		int initialAlienNumber = game.getGameLogic().getActorsNumber();
		player.setX(100);
		player.setY(10);
		aliens[1].setX(100);
		aliens[1].setY(10);
		game.getGameLogic().checkCollisions();
		Assert.assertEquals("Ha borrado Alien del juego" ,
				initialAlienNumber-1,
				game.getGameLogic().getActorsNumber()
		);
		pantalla.paint(pantalla.getGraphics());
		Assert.assertEquals("Ha borrado Alien de la pantalla" ,
				initialAlienNumber-1,
				pantalla.getGraphicsMock().getImageBuffer().size()-1
		);
		Assert.assertEquals("Jugador ha perido una vida" ,
				3-1,
				game.getGameLogic().getPlayer().getShields()
		);
		
	}

	@Test
	public void testCollisionGaming() {
		int initialAlienNumber = game.getGameLogic().getActorsNumber();
		player.setX(100);
		player.setY(10);
		aliens[1].setX(100);
		aliens[1].setY(10);
		game.update();
		pantalla.paint( pantalla.getGraphics());
		Assert.assertEquals("Ha borrado Alien de la pantalla" ,
				initialAlienNumber-1,
				pantalla.getGraphicsMock().getImageBuffer().size()-1
		);
		Assert.assertEquals("Jugador ha perido una vida" ,
				3-1,
				game.getGameLogic().getPlayer().getShields()
		);
	}	

	@Test
	public void testShot() {
		int initialAlienNumber = game.getGameLogic().getActorsNumber();
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		
		player.setX(100);
		player.setY(200);
		aliens[1].setX(102);
		aliens[1].setY(10);
		
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue(); 
		eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
		try{
			Thread.sleep(300);
		}catch(Exception e){
			
		}
		Shot misil = player.getMisiles().get(0);
		Assert.assertTrue("Misil lanzadovisible", misil.isVisible() );
		pantalla.paint( pantalla.getGraphics() );
		Assert.assertEquals("Misil insertado en en el frameBuffer de la pantalla admeas del actor" ,
				initialAlienNumber+2,
				pantalla.getGraphicsMock().getImageBuffer().size()
		);
		
		misil.setY(15);
		game.update();
		pantalla.paint( pantalla.getGraphics() );
		//Ahora hay que chequear el numero de misiles visibles/utilizados y el numero  de invasores
		Assert.assertEquals("Invasor eliminados y misil recargado y no visible" ,
				initialAlienNumber+2-2,
				pantalla.getGraphicsMock().getImageBuffer().size()
		);
		
		
		
	}
	
	@Test
	public void test6Shot2Aliens() {
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		//Eliminar todos los invasores
		int shotIndex = 0;
		int initialAlienNumber = game.getGameLogic().getActorsNumber();
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		
		for ( shotIndex = 0 ; shotIndex < initialAlienNumber  ; shotIndex++){
			player.setX( aliens[shotIndex].getX());
			player.setY( aliens[shotIndex].getY()+2);
			eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
			try{
				Thread.sleep(200);
			}catch(Exception e){
				
			}
		}
		Shot misil ;
		for ( shotIndex = 0 ; shotIndex < 3  ; shotIndex++){
			misil = player.getMisiles().get(shotIndex);
			Assert.assertTrue("Misil "+ shotIndex+ " lanzado/visible", misil.isVisible() );
		}
		game.update();
		pantalla.paint( pantalla.getGraphics() );
		game.update();
		pantalla.paint( pantalla.getGraphics() );
		
		Assert.assertEquals("Numero de inavsores eliminados" ,  initialAlienNumber-3, game.getGameLogic().getActorsNumber());
		
		Assert.assertFalse("Solo tres Invasores eliminados.No ha terminado el juego", game.getGameLogic().isGameWin() );
		 
		game.closeWindow();
		
	
	}
	
	@Test
	public void testGameWin() {
		GameEventListener listener = new GameEventListener(game);
		game.getWindow().addWindowListener(listener);
		game.getWindow().addKeyListener(listener);
		//Eliminar todos los invasores
		int shotIndex = 0;
		int initialAlienNumber = game.getGameLogic().getActorsNumber();
		EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		
	
		for ( shotIndex = 0 ; shotIndex < 3  ; shotIndex++){
			player.setX( game.getGameLogic().getActors().get(shotIndex).getX());
			//player.setY( game.getGameLogic().getActors().get(shotIndex).getY()+6);
			eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
			eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
			try{
				Thread.sleep(100);
			}catch(Exception e){
			}
			pantalla.paint( pantalla.getGraphics() );

		}
		
		Shot misil;
		for ( shotIndex = 0 ; shotIndex < 3  ; shotIndex++){
			misil = player.getMisiles().get(shotIndex);
			Assert.assertTrue("Misil "+ shotIndex+ " lanzado/visible", misil.isVisible() );
		}
		Assert.assertEquals("Numero de inavsores actual" , 5 , game.getGameLogic().getActorsNumber());
	
		
		do{
			game.update();
			pantalla.paint( pantalla.getGraphics() );
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}while( (player.getMisiles().get(2).isVisible()) );
		
	
		
		
		/*
		game.update();
		pantalla.paint( pantalla.getGraphics() );
		game.update();
		game.update();
		pantalla.paint( pantalla.getGraphics() );
		*/
		
		//Par aver si ha recargado bien los misiles
		Assert.assertEquals("Numero de invasores actual" , 2 , game.getGameLogic().getActorsNumber());
		for ( shotIndex = 0 ; shotIndex < 3  ; shotIndex++){
			misil = player.getMisiles().get(shotIndex);
			Assert.assertFalse("Misil   "+ shotIndex+ " recargado", misil.isVisible() );
		}
		//initialAlienNumber = game.getGameLogic().getActorsNumber();
		

		for ( shotIndex = 0 ; shotIndex < game.getGameLogic().getActorsNumber()  ; shotIndex++){
			player.setX( game.getGameLogic().getActors().get(shotIndex).getX() );
			player.setY( game.getGameLogic().getActors().get(shotIndex).getY()+6);
			eventQueue.postEvent( new KeyEvent( game.getWindow() , KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED )  );
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}
		
		do{
			game.update();
			pantalla.paint( pantalla.getGraphics() );
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}while( (player.getMisiles().get(1).isVisible()) );
		
		Assert.assertEquals("Numero de inavsores eliminados" ,  0 , game.getGameLogic().getActorsNumber());
		
		Assert.assertTrue("Invasores eliminados.", game.getGameLogic().isGameWin() );
		 
		game.closeWindow();
		
	
	}
	
	
	@Test
	public void testGameOver() {
		//Agotar las vidas en juego
		//para ello tambien deben de haber desaperecido tres alien en este caso , ya qu elo slaien no disparan por ahora
		int i = 0;
		for ( i = 0 ; i < 3  ; i++){
			player.setX( game.getGameLogic().getActors().get(i).getX());
			player.setY( game.getGameLogic().getActors().get(i).getY());
			game.update();
			pantalla.paint( pantalla.getGraphics() );
		}
		Assert.assertTrue("Vidas agotadas.", game.getGameLogic().isGameOver() );
		 
		game.closeWindow();
		
		
	}
	
	@Test
	public void testConvertirInvisibleUnMisil(){
		Assert.fail("Test importatntisimo proque comteia error al borrar  debido ");
	}
	
	@Test
	public void testScoreUpdate() {
		//Actualizar puntuacion 
	}
	
	@Test
	public void testShieldUpdate() {
		//Agregar un texto como shields
	}
	
	
	@Test
	public void testCompleteGame() {
		//Ahora a pensar en como crear el hilo
	}

}
