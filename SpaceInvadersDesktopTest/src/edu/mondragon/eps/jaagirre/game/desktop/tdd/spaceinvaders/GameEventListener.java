package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class GameEventListener extends WindowAdapter implements KeyListener {

	Game game;
		
	public GameEventListener(Game game){
		this.game = game;
	}
		
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
			case KeyEvent.VK_DOWN:
				game.getGameLogic().getPlayer().moveDown();
				break;
			case KeyEvent.VK_UP:
				game.getGameLogic().getPlayer().moveUp();
				break;
			case KeyEvent.VK_RIGHT:
				game.getGameLogic().getPlayer().moveRight();
				break;
			case KeyEvent.VK_LEFT:
				game.getGameLogic().getPlayer().moveLeft();
				break;
			case KeyEvent.VK_SPACE:
				game.getGameLogic().getPlayer().fire();
				break;
			default:
				break;
	}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void windowClosing(WindowEvent e) {
        System.out.println("Windoe closoing Simulated!!!!!!!!"); 
		game.closeWindow();
    }

}
