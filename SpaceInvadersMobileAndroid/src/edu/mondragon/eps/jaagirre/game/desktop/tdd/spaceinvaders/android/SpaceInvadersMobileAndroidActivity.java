package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.android;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Game;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameBoard;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameThread;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.Level;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Alien;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.AlienHV;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.PlayerWithCteSpeed;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Shot;


public class SpaceInvadersMobileAndroidActivity extends Activity {
    /** Called when the activity is first created. */
	/*GUI*/
	Button bRight , bLeft , bUp, bDown , bFire;
	FrameLayout fLayout= null;
	SurfaceView surfaceView;
	GameBoard board; 
	GameEventListenerAndroid listener;
	/*Logica de negocio*/  
	Actor []aliens;
	Actor []aliens2;
	PlayerWithCteSpeed player = null;
	Game game = null;
	GameThread gameThread= null;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        board = new GameBoardAndroid( (SurfaceView)this.findViewById(R.id.surfaceView1) );
    
    	
    
    }
    
 
    
    @Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		initGameElements();
	  	game.openWindow();
		gameThread.start();
	}





	public void initView(){
    
    	bLeft = (Button) this.findViewById(R.id.buttonLeft);
		bRight = (Button) this.findViewById(R.id.buttonRight); 
		bUp = (Button) this.findViewById(R.id.buttonUp); 
		bDown = (Button) this.findViewById(R.id.buttonDown);
		bFire = (Button) this.findViewById(R.id.buttonFire);
		fLayout = (FrameLayout) this.findViewById(R.id.frameLayout1);
    }
    
    public void initGameElements(){
    	int i = 0;
    	int h , w;
    	h =this.findViewById(R.id.surfaceView1).getWidth();
    	w=this.findViewById(R.id.surfaceView1).getHeight();
    	
    	game = new GameAndroid( h,w , board);
    	System.out.println("w & h : " + h +" - " +w + "::" +fLayout.getWidth() + "#"+ fLayout.getHeight());
    	
    	gameThread= new GameThread(game);
		aliens = new Actor[3];
		
		game.initWindow();
		game.addSprite("invasor" , ""+R.drawable.bicho);
		game.addSprite("jugador" , ""+R.drawable.nave);
		game.addSprite("misil" , ""+R.drawable.disparo1);
		game.addSprite("invasorHV" , ""+ R.drawable.the_new_blue_space_ship);
		
		game.getGameLogic().setBoard(board);
		listener = new GameEventListenerAndroid(game);
		bRight.setOnClickListener(listener);
		bLeft.setOnClickListener(listener);
		bUp.setOnClickListener(listener);
		bDown.setOnClickListener(listener); 
		bFire.setOnClickListener(listener);
		
	
		
		
		
	
		for ( i = 0 ; i < 3 ; i++){
			aliens[i] = new Alien( "invasor" ,  10 ,(i* 20)+10  ,  game.getWidth() , game.getHeight() ,
									game.getBoard().getSpriteWidth("invasor") , game.getBoard().getSpriteHeight("invasor") , true ); 
			aliens[i].setVx(2);
			aliens[i].setVy(0);
		}
		Level level = new Level();
        ArrayList<Actor> actors = new ArrayList<Actor>(Arrays.asList(aliens));
        level.setLevel(actors);
        game.getGameLogic().addLevel(level);
        game.getGameLogic().setActorsInBoard();
        
        aliens2 = new Actor[6];
        for ( i = 0 ; i < 3 ; i++){
              
               aliens2[i] = new Alien( "invasor" ,  10 ,(i* 20)+10  ,  game.getWidth() , game.getHeight() , 
            		   			game.getBoard().getSpriteWidth("invasor") , game.getBoard().getSpriteHeight("invasor") , true);
               aliens2[i].setVx(2);
               aliens2[i].setVy(0);
               //game.addActor(aliens[i]);
        }
        for ( i = 3 ; i < 6 ; i++){
              
               aliens2[i] = new AlienHV( "invasorHV" ,  10*i ,(i* 20)+10  ,  game.getWidth() , game.getHeight()  ,
            		   					game.getBoard().getSpriteWidth("invasorHV") , game.getBoard().getSpriteHeight("invasorHV") , true );
               aliens2[i].setVx(2);
               aliens2[i].setVy(0);
               //game.addActor(aliens2[i]);
        }
        
        //Segundo nivel
        Level level2 = new Level();
        ArrayList<Actor> actors2 = new ArrayList<Actor>(Arrays.asList(aliens2));
        level2.setLevel(actors2);
        game.getGameLogic().addLevel(level2);
		
		
		//player = new Player( game.getSprite("jugador") , 200 , 300 , game.getWidth()-50 , game.getHeight()-100 );
        player = new PlayerWithCteSpeed( "jugador" , 200 , 300 , game.getWidth(), game.getHeight(),
        								game.getBoard().getSpriteWidth("jugador") ,
        								game.getBoard().getSpriteHeight("jugador") , true);
		player.setV(2);
        ArrayList<Shot> misiles = new ArrayList<Shot>();
		misiles.add(new Shot( "misil" ,  0 , 0  , game.getWidth() , game.getHeight()  , 
							game.getBoard().getSpriteWidth("misil")  , 
							game.getBoard().getSpriteHeight("misil") , false) );
		misiles.add(new Shot( "misil" ,  0 , 0  , game.getWidth() , game.getHeight() , 
							game.getBoard().getSpriteWidth("misil"), game.getBoard().getSpriteHeight("misil") , false ) );
		misiles.add(new Shot( "misil" ,  0 , 0  , game.getWidth() , game.getHeight()  , 
							game.getBoard().getSpriteWidth("misil") , game.getBoard().getSpriteHeight("misil") , false ) );
		
		player.setMisiles(misiles);
		game.getGameLogic().setPlayer(player);
	}
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		boolean retry = true; 
		game.getGameLogic().setGameOver(true);
		while (retry) {
             try {
                   gameThread.join();
                   retry = false;
             } catch (InterruptedException e) {
             }
      }
	}

	
	
}