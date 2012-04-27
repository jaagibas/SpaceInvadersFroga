package edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests.mocks.BoardMock;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Game;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;

public class ShotTest {

	Game game;
	BoardMock pantalla;
	Actor misil;
	
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
		
		misil = new Shot( game.getSprite("misil") ,  200 ,250  , game.getWidth()-50 , game.getHeight()-100 ); 
		
		
	}

	@After
	public void tearDown() throws Exception {
		game.closeWindow();
	}

	@Test
	public void testMovement() {
		misil.setVisible(true);
		misil.executeBehaviour();
		Assert.assertEquals("Movimiento Tiro" ,
				200,
				misil.getX() 
		);
		Assert.assertEquals("Movimiento Derecha Y" ,
				250-2,
				misil.getY() 
		);
			
	}
	
	@Test
	public void testMovementLimits() {
		misil.setVisible(true);
		misil.setY(1);
		Assert.assertTrue("Previo" ,
				misil.isVisible() 
		);
		misil.executeBehaviour();
		Assert.assertEquals("Movimiento Tiro" ,
				200,
				misil.getX() 
		);
		Assert.assertEquals("Movimiento Derecha Y" ,
				1,
				misil.getY() 
		);
		Assert.assertFalse("Desaparacion misil" ,
				misil.isVisible() 
		);
	}

}
