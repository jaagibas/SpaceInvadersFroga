package edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests.mocks.BoardMock;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Game;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;

public class PlayerTest {

	Game game;
	BoardMock pantalla;
	Player player;
	
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
		
		player = new Player( game.getSprite("jugador") ,  200 ,250  , game.getWidth()-50 , game.getHeight()-100 ); 
		player.setV(2);
		game.setPlayer(player);
		
	}

	@After
	public void tearDown() throws Exception {
		game.closeWindow();
	}

	@Test
	public void testMovement() {
		player.moveRight();
		Assert.assertEquals("Movimiento Derecha X" ,
				200+2,
				player.getX() 
		);
		Assert.assertEquals("Movimiento Derecha Y" ,
				250,
				player.getY() 
		);
		player.moveUp();
		Assert.assertEquals("Movimiento arriba Y" ,
				250-2,
				player.getY() 
		);
		Assert.assertEquals("Movimiento arriba X" ,
				202,
				player.getX() 
		);
		player.moveLeft();
		Assert.assertEquals("Movimiento Izquierda" ,
				200,
				player.getX() 
		);
		Assert.assertEquals("Movimiento izquierda" ,
				250-2,
				player.getY() 
		);
		player.moveDown();
		Assert.assertEquals("Movimiento Abajo y",
				250,
				player.getY() 
		);
		Assert.assertEquals("Movimiento Izquierda x" ,
				200,
				player.getX() 
		);
		
	}
	
	@Test
	public void testMovementLimits() {
		player.setX(349);
		player.moveRight();
		Assert.assertEquals("Movimiento Derecha X" ,
				349,
				player.getX() 
		);
		Assert.assertEquals("Movimiento Derecha Y" ,
				250,
				player.getY() 
		);
		player.setY(1);
		player.moveUp();
		Assert.assertEquals("Movimiento arriba Y" ,
				1,
				player.getY() 
		);
		Assert.assertEquals("Movimiento arriba X" ,
				349,
				player.getX() 
		);
		player.setX(1);
		player.moveLeft();
		Assert.assertEquals("Movimiento Izquierda" ,
				1,
				player.getX() 
		);
		Assert.assertEquals("Movimiento izquierda" ,
				1,
				player.getY() 
		);
		player.setY(299);
		player.moveDown();
		Assert.assertEquals("Movimiento Abajo y",
				299,
				player.getY() 
		);
		Assert.assertEquals("Movimiento Izquierda x" ,
				1,
				player.getX() 
		);
		
	}
	
	@Test
	public void testOneShot() {
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		
		player.fire();
		Assert.assertEquals("Primer disparo",
				player.getY(),
				misiles.get(0).getY() 
		);
		player.fire();
		Assert.assertEquals("Segundo disparo",
				player.getY(),
				misiles.get(1).getY() 
		);
		player.fire();
		Assert.assertEquals("Tercer  disparo",
				player.getY(),
				misiles.get(2).getY() 
		);
	
		
	}
	
	@Test
	public void testShotLimit() {
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		
		player.fire();
		player.fire();
		player.fire();
		player.fire();
		Assert.assertEquals("Cuarto disparo consecutivo con cargador de tres",
				3,
				player.getMisilNumber()
		);
		
	}
	
	@Test
	public void testShotBehaviour() {
		int i = 0;
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		
		player.fire();
		player.fire();
		player.fire();
		player.fire();
		
		player.executeBehaviour();
		for ( i = 0 ; i < 3 ; i ++) {
			Assert.assertEquals("Movimiento Tiro" ,
					player.getX(),
					misiles.get(i).getX() 
			);
			Assert.assertEquals("Movimiento Derecha Y" ,
					player.getY()-2,
					misiles.get(i).getY() 
			);
			
		}
		
		
	}
	
	@Test
	public void testShotReLoadFirst() {
		int i = 0;
		int loopNumber = 0;
		int xIniciales[];
		
		xIniciales = new int[3];
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		
		player.fire();
		player.fire();
		player.fire();
		player.fire();
		Assert.assertTrue("Misil 1  iniciado trayectoria",
				misiles.get(0).isVisible()
		);
		Assert.assertEquals("Cuarto disparo consecutivo con cargador de tres",
				3,
				player.getVisibleMisilsNumber()
		);
		
		for ( i = 0 ; i < 3 ; i++) xIniciales[i] = player.getX();
		
		misiles.get(0).setY(1);
		
		for ( loopNumber = 0;  loopNumber < 2 ; loopNumber++){
			player.executeBehaviour();
			Assert.assertEquals("Cuarto disparo consecutivo con cargador de tres bucle" +loopNumber ,
					2,
					player.getVisibleMisilsNumber()
			);
			for ( i = 0 ; i < 3 ; i ++) {
				Assert.assertEquals("Movimiento X Tiro misil " + i + " bucle " + loopNumber,
						xIniciales[i],
						misiles.get(i).getX() 
				);
				if ( i != 0){
					Assert.assertTrue("Misil "+ i +" en movimiento",
							misiles.get(i).isVisible()
					);
					Assert.assertEquals("Movimiento Y Tiro misil " + i + " bucle " + loopNumber,
							player.getY()-((loopNumber+1)*2),
							misiles.get(i).getY() 
					);
					
				}else{
					Assert.assertEquals("Movimiento Y Tiro misil " + i + " bucle " + loopNumber,
							1,
							misiles.get(i).getY()
					);
					Assert.assertFalse("Misil 1  finalizado trayectoria",
							misiles.get(i).isVisible()
					);
				}
				 
			}
		}
	}
	
	@Test
	public void testShotReLoadSecond() {
		int i = 0;
		int loopNumber = 0;
		int xIniciales[];
		
		xIniciales = new int[3];
		ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		misiles.add(new Shot( game.getSprite("misil") ,  0 , 0  , game.getWidth()-50 , game.getHeight()-100 ) );
		
		player.setMisiles(misiles);
		
		player.fire();
		player.fire();
		player.fire();
		player.fire();
		Assert.assertTrue("Misil 1  iniciado trayectoria",
				misiles.get(0).isVisible()
		);
		Assert.assertEquals("Cuarto disparo consecutivo con cargador de tres",
				3,
				player.getMisilNumber()
		);
		
		for ( i = 0 ; i < 3 ; i++) xIniciales[i] = player.getX();
		
		misiles.get(1).setY(1);
		
		for ( loopNumber = 0;  loopNumber < 2 ; loopNumber++){
			player.executeBehaviour();
			Assert.assertEquals("Cuarto disparo consecutivo con cargador de tres bucle" +loopNumber ,
					2,
					player.getVisibleMisilsNumber()
			);
			for ( i = 0 ; i < 3 ; i ++) {
				Assert.assertEquals("Movimiento X Tiro misil " + i + " bucle " + loopNumber,
						xIniciales[i],
						misiles.get(i).getX() 
				);
				if ( i != 1){
					Assert.assertTrue("Misil "+ i +" en movimiento",
							misiles.get(i).isVisible()
					);
					Assert.assertEquals("Movimiento Y Tiro misil " + i + " bucle " + loopNumber,
							player.getY()-((loopNumber+1)*2),
							misiles.get(i).getY() 
					);
					
				}else{
					Assert.assertEquals("Movimiento Y Tiro misil " + i + " bucle " + loopNumber,
							1,
							misiles.get(i).getY()
					);
					Assert.assertFalse("Misil 1  finalizado trayectoria",
							misiles.get(i).isVisible()
					);
				}
				 
			}
		}
		player.fire();
		player.executeBehaviour();
		Assert.assertEquals("Movimiento Tiro" ,
				player.getX(),
				misiles.get(1).getX() 
		);
		Assert.assertEquals("Movimiento Derecha Y" ,
				player.getY()-2,
				misiles.get(1).getY() 
		);
		
	}

}
