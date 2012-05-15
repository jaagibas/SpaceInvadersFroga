package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;



import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Player;

//Simulación de cambios sobre rama dev
//Simulacion2º de cambios sobre rama dev (visualizando TopScore)
//Simulacion3º de cambios sobre rama dev (visualizando CteSpeed)
//Simulacion4º y nota:: Cambiar mensaje Zorionak Niveles y Final
//Simulacion5º rama dev sigue su curso
//Simulacion6º Simulacion1Feature (visualizando Simulacion1Feature)
public abstract class Game  {
	
	protected int width;
	protected int height;
	protected GameBoard board;
	protected GameLogic gameLogic;
	boolean running;

	public Game(int height, int width, GameBoard board){
		this.height = height;
		this.width = width;
		this.gameLogic = new GameLogic();
		//Este casting es clave
		this.board = board; //Aqui tiene que ser 
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
		
	

	public void update(){
		gameLogic.update();
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

	public GameBoard getBoard() {
		return board;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	/*Funcion que realiza la actualización de actores, verifica el juego y pinta la pantalla*/
    public void step(){
          gameLogic.executeActualLevel();
          if ( gameLogic.isLevelWin() ){        //isGameWin significa ganar nivel. Nota cambiar nobre futura release
              //significa que ha pasado la pantalla
              gameLogic.nextLevel();
              //establecemos los nuevos actores de la nueva pantalla
              gameLogic.setActorsInBoard();
              if (!gameLogic.isGameWon()){
            	  gameLogic.setGameWin(false);
              }
              
          }
    }
	
	public boolean isGameRunning() {
		boolean ret = true;
		GameLogic gameLogic = getGameLogic();
		ret = ( !gameLogic.isGameOver() && !gameLogic.isGameWon()  ); //&& !gameLogic.isGameWin() );
		return ret;		
	}

	
	public abstract void closeWindow();
	public abstract void initWindow();
	public abstract void openWindow();
}	
