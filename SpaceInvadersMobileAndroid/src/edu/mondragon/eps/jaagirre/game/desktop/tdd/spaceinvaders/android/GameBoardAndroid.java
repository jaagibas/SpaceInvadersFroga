package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameBoard;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

public class GameBoardAndroid extends GameBoard {

	SurfaceView canvas;
	SurfaceHolder holder;
	
	
	
	
	
	@Override
	public void paintBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSprite(String nombre, String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsSprite(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSpriteWidth(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpriteHeight(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initBoard(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	private void init(){
		initHolder();
	}

	private void initHolder(){
		holder = canvas.getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        	
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
                 /* Canvas c = holder.lockCanvas(null);
                  onDraw(c);
                  holder.unlockCanvasAndPost(c);*/
        	
        	    
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format,
                 int width, int height) {
        }
    });
	}

	private Bitmap createBitmap( int id){
    	return BitmapFactory.decodeResource(getResources(), id) ;
    }

	
	public void paintImage(Canvas g) {
		// TODO Auto-generated method stub
		int i = 0;
		Actor actor;
		Paint p = new Paint();
      	  p.setColor(Color.GREEN);
		for (i = 0 ; i < imageBuffer.size() ; i++){
			actor = (Actor)imageBuffer.get(i);
			if (actor.isVisible()){
				g.drawBitmap( actor.getImage() ,  actor.getX() , actor.getY(), null  );
			}
			
		}
	}
	
	public void clearBoard(Canvas g){
		 g.drawColor(Color.BLACK);
	}
	

	
	

	public void onDraw(Canvas g) {
		// TODO Auto-generated method stub
		super.onDraw(g);
		clearBoard(g); //no es necesario
	    paintText(g);
	    paintImage(g);
	}
}
