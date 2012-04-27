package edu.mondragon.eps.jaagirre.game.desktop.spaceinvaders.tests.mocks;

import java.awt.Color;
import java.awt.Graphics;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameBoard;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameText;

/*La herencia podria ser lo contrario*/
/*Lo ideal seria solo reescribir el g.drawString , porque 
 sino dependeriamos tambien de coo hemos implementado el mock 
*/

public class BoardMock extends GameBoard {

	GraphicsMock graphicsMock;
	
	
	public BoardMock() {
		super();
		// TODO Auto-generated constructor stub
		graphicsMock = new GraphicsMock();
	}
		
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//Ahora llamaos a la misma funcion pero con el Mock
		g = graphicsMock;
		super.paint(g);
	}

	public GraphicsMock getGraphicsMock() {
		return graphicsMock;
	}
	
	public GameText getPaintedText(int x , int y){
			GameText ret= null;
			GameText iterator= null;
			int i = 0;
			boolean aurkitua = false;
			
			while( !(aurkitua) && i <  graphicsMock.getTextBuffer().size() ){
				iterator =(GameText) graphicsMock.getTextBuffer().get(i);
				if ( x == iterator.getX() && y ==iterator.getY()){
					ret = iterator;
					aurkitua = true;
				} 
				i++;
			}
			
			return ret;
	}
	
	public void clearBoard(Graphics g){
		super.clearBoard(g);
	
	}
	
	



	
	
	
}
