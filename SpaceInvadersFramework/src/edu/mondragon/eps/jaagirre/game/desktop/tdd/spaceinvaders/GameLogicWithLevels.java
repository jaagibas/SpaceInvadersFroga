package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.util.ArrayList;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Level;

public class GameLogicWithLevels extends GameLogic {

	int level = 0;
	static final int MAX_LEVEL = 3;
	ArrayList<Level> levels;
	
	private void setLevel(int newLevel){
		if ( newLevel < GameLogicWithLevels.MAX_LEVEL && newLevel > 0){
			this.level = newLevel;
			this.actors = levels.get( newLevel-1).getLevel();
		}
		 
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		if ( this.isLevelWin() ){
			this.level++;
			this.setLevel(level);
			//colocar el player en su poisicion de inicio
		}
	}
	
	
	
	
	
}
