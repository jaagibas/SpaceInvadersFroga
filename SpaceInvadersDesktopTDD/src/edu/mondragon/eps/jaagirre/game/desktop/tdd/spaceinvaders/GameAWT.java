package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameAWT extends Game {

	private JFrame window;
	private JPanel panel;
	private Canvas canvas;
	
	public GameAWT(int height, int width, GameBoard board) {
		super(height, width, board);
		// TODO Auto-generated constructor stub
		canvas = new Canvas();
		((GameBoardAWT)board ).setCanvas(this.canvas);
	}
	
	public JFrame getWindow(){
		return this.window;
	}
	
	public void initWindow(){
		board.initBoard(0,0,width, height); 
		window = new JFrame("SpaceInvaders");
		window.setBounds( 100,100,width, height );
		panel = (JPanel)window.getContentPane();
		panel.setPreferredSize(new Dimension( this.width, this.height));
	    panel.setLayout(null);
	    //Otra linea problematica
	    panel.add( canvas );
	}
	
	public void openWindow(){
		 window.setVisible(true);
	}
	
	


	public void closeWindow(){
		this.window.dispose();
	}
	


}
