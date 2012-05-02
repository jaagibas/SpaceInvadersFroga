package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;

//Simulación de cambios sobre rama dev
//Simulacion2º de cambios sobre rama dev (visualizando TopScore)
//Simulacion3º de cambios sobre rama dev (visualizando CteSpeed)
//Simulacion4º y nota:: Cambiar mensaje Zorionak Niveles y Final
//Simulacion5º rama dev sigue su curso
//Simulacion6º Simulacion1Feature (visualizando Simulacion1Feature)
public class Game  {
	
	private int width;
	private int height;
	
	private JFrame window;
	private JPanel panel;
	private GameBoard board;
	private GameLogic gameLogic;
	
	
	boolean running;
	
	
	
	public Game(int height, int width, GameBoard board){
		this.height = height;
		this.width = width;
		this.gameLogic = new GameLogic();
		this.board = board;
		
	
	}
	
	public void startGameThread(){
		
	} 
	
	public void addSprite(String key , String  nombre){
		 if (! board.existsSprite(nombre) ){
			 board.createSprite( nombre , key  );
			 
		 }
	}
	
	public void addActor(Actor actor){
		gameLogic.addActor(actor);
	}
	
	public void setPlayer(Player player){
		gameLogic.setPlayer(player);
	}
		
	public void initWindow(){
		
		
		board.setBounds(0,0,width, height);
	    
		window = new JFrame("SpaceInvaders");
		window.setBounds( 100,100,width, height );
		panel = (JPanel)window.getContentPane();
		panel.setPreferredSize(new Dimension( this.width, this.height));
	    panel.setLayout(null);
	    panel.add(board);
		
	    
	   
	}

	public void update(){
		gameLogic.update();
	}
	
	public void openWindow(){
		 window.setVisible(true);
	}
	
	


	public void closeWindow(){
		this.window.dispose();
	}


	
	
	public int getWidth() {
		return width;
	}


	
	
	


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	


	public JFrame getWindow() {
		return window;
	}


	


	public JPanel getPanel() {
		return panel;
	}


	


	public GameBoard getBoard() {
		return board;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	/*Funcion que realiza la actualización de actores, verifica el juego y pinta la pantalla*/
    public void step(){
          gameLogic.executeActualLevel();
          if ( gameLogic.isGameWin() ){        //isGameWin significa ganar nivel. Nota cambiar nobre futura release
              //significa que ha pasado la pantalla
              gameLogic.nextLevel();
              //establecemos los nuevos actores de la nueva pantalla
              gameLogic.setActorsInBoard();
              gameLogic.setGameWin(false);
          }
    }
	
	public boolean isGameRunning() {
		boolean ret = true;
		GameLogic gameLogic = getGameLogic();
		ret = ( !gameLogic.isGameOver() && !gameLogic.isGameWon()  ); //&& !gameLogic.isGameWin() );
		return ret;		
	}

}	
