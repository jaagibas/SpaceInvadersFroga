package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.android;



import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameBoard;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameText;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

public class GameBoardAndroid extends GameBoard {

	SurfaceView surface;
	SurfaceHolder holder;
	private HashMap<String,Bitmap> sprites;
	
	
	
	public GameBoardAndroid(SurfaceView surfaceView){
		textBuffer = new ArrayList<GameText>();
		imageBuffer = new ArrayList<Actor>();
		surface =  surfaceView;
		holder = surface.getHolder();
		sprites = new HashMap<String,Bitmap>();
		
	}
	
	
	@Override
	public void paintBoard() {
		// TODO Auto-generated method stub
		Canvas c = null;
		c = holder.lockCanvas();
		//Debido a un problema de sicnronizacion y velocidad
		if (c!= null){
			try {
	            synchronized (holder) {
	                onDraw(c);
	        	} 
			}finally {
	            if (c != null) {
	           	 holder.unlockCanvasAndPost(c);
	            }
	        }
		}
		
	}

	@Override
	public void createSprite(String nombre, String key) {
		// TODO Auto-generated method stub
		 Bitmap img = (Bitmap)sprites.get(key);
	        if (img == null) {
	          img = loadImage( Integer.parseInt(nombre) );
	          if (img != null){
	        	  sprites.put(key,img);
	          }
	        }
	}

	@Override
	public boolean existsSprite(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSpriteWidth(String key) {
		// TODO Auto-generated method stub
		 Bitmap img = (Bitmap)sprites.get(key);
	     return img.getWidth();
	}

	@Override
	public int getSpriteHeight(String key) {
		// TODO Auto-generated method stub
		 Bitmap img = (Bitmap)sprites.get(key);
	     return img.getHeight();
	}

	@Override
	public void initBoard(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	private void init(){
		initHolder();
	}

	private void initHolder(){
		holder = surface.getHolder();
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

	
	private void paintImage(Canvas g) {
		// TODO Auto-generated method stub
		int i = 0;
		Actor actor;
		Paint p = new Paint();
      	  p.setColor(Color.GREEN);
		for (i = 0 ; i < imageBuffer.size() ; i++){
			actor = (Actor)imageBuffer.get(i);
			if (actor.isVisible()){
				g.drawBitmap(  getSprite( actor.getId() ) ,  actor.getX() , actor.getY(), null  );
			}
			
		}
	}
	
	private void clearBoard(Canvas g){
		 g.drawColor(Color.BLACK);
	}
	

	

	private void onDraw(Canvas g) {
		// TODO Auto-generated method stub
		
		
			surface.draw(g);
			clearBoard(g); //no es necesario
		    paintText(g);
		    paintImage(g);
		
		
	}
	
	private Bitmap getSprite(String id){
		 Bitmap img = (Bitmap) sprites.get(id);
	     return img;
	}
	
	private void paintText(Canvas g){
		int i = 0;
		GameText bufferedText;
		Paint p = new Paint();
      	p.setColor(Color.GREEN);
		for (i = 0 ; i < textBuffer.size() ; i++){
			bufferedText = (GameText)textBuffer.get(i);
			g.drawText(  bufferedText.getText() ,  bufferedText.getX() , bufferedText.getY() , p  );
		}
	}
	
	private Bitmap loadImage(int id) {
	       Bitmap bitmap = null;
	        bitmap  = BitmapFactory.decodeResource( surface.getResources(), id) ;
	        return bitmap;
	}
	
}
