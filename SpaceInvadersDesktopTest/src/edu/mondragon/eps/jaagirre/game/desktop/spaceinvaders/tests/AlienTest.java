package edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests.mocks.BoardMock;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Game;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Alien;

public class AlienTest {

	Game game;
	BoardMock pantalla;
	Actor []aliens;
	
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
		
		
		aliens = new Actor[5];
		int i = 0;
		for ( i = 0 ; i < 5 ; i++){
			aliens[i] = new Alien( game.getSprite("invasor") ,  10 ,i* 10  , game.getWidth()-50 , game.getHeight()-100 ); 
			game.addActor(aliens[i]);
		}
	}

	@After
	public void tearDown() throws Exception {
		game.closeWindow();
	}

	@Test
	public void testAlienLeftMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(2);
			aliens[i].setVy(0);
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					10+2,
					aliens[i].getX() 
			);
		}
		
	}
	@Test
	public void testAlienRightMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(-2);
			aliens[i].setVy(0);
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					10-2,
					aliens[i].getX() 
			);
		}
		
	}
	
	@Test
	public void testAlienUpMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(0);
			aliens[i].setVy(-2);
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			if ( i == 0){
				Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
						1,
						aliens[i].getY() 
						);
			}else{
				Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
						(i*10)-2,
						aliens[i].getY() 
						);
			}
		}
		
	}
	
	@Test
	public void testAlienDownMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(0);
			aliens[i].setVy(2);
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		
		
		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					(i*10)+2,
					aliens[i].getY() 
			);
		}
		
	}
	
	@Test
	public void testMovementRightDownLimits() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(i);
			aliens[i].setVy(i);
			aliens[i].setX(349);
			aliens[i].setY(299);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					349,
					aliens[i].getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					299,
					aliens[i].getY() 
			);
		}
		
		
	}
	
	@Test
	public void testMovementLefttDownLimits() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(-i);
			aliens[i].setVy(i);
			aliens[i].setX(1);
			aliens[i].setY(299);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					1,
					aliens[i].getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					299,
					aliens[i].getY() 
			);
		}
		
		
	}
	
	
	@Test
	public void testMovementLeftUpLimits() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(-i);
			aliens[i].setVy(-i);
			aliens[i].setX(1);
			aliens[i].setY(1);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					1,
					aliens[i].getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					1,
					aliens[i].getY() 
			);
		}
		
		
	}

	
	@Test
	public void testMovementRightUpLimits() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(i);
			aliens[i].setVy(-i);
			aliens[i].setX(349);
			aliens[i].setY(1);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					349,
					aliens[i].getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					1,
					aliens[i].getY() 
			);
		}
		
		
	}
	
	@Test
	public void testRightBouncingtMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(i);
			aliens[i].setVy(0);
			aliens[i].setX(349);
			aliens[i].setY(100);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					349,
					aliens[i].getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					i*(-1),
					aliens[i].getVx() 
			);
		}
	}
	
	@Test
	public void testLeftBouncingtMovement() {
		int  i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(-i);
			aliens[i].setVy(0);
			aliens[i].setX(1);
			aliens[i].setY(100);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion X Alien "+ i +"en pantalla incorrecta" ,
					1,
					aliens[i].getX() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					i,
					aliens[i].getVx() 
			);
		}
	}
	
	@Test
	public void testUpBouncingtMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(0);
			aliens[i].setVy(-i);
			aliens[i].setX(100);
			aliens[i].setY(1);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					1,
					aliens[i].getY() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					i,
					aliens[i].getVy() 
			);
		}
	}
	
	@Test
	public void testDownBouncingtMovement() {
		int i = 0;
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].setVx(0);
			aliens[i].setVy(i);
			aliens[i].setX(100);
			aliens[i].setY(299);
		}
		
		for ( i = 0 ; i < 5 ; i++){
			aliens[i].executeBehaviour();
		}
		

		for ( i = 0 ; i < 5 ; i++){
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					299,
					aliens[i].getY() 
			);
			Assert.assertEquals("Posicion Y Alien "+ i +"en pantalla incorrecta" ,
					i*-1,
					aliens[i].getVy() 
			);
		}
	}
	
	


	
}
