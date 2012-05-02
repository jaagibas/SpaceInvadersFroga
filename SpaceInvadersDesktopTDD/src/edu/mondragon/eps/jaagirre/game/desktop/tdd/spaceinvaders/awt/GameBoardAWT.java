package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.awt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameBoard;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.GameText;
import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

public class GameBoardAWT  extends  GameBoard{

	private HashMap<String,BufferedImage> sprites;
	Canvas canvas;
	
	public GameBoardAWT() {
		super();
		// TODO Auto-generated constructor stub
		textBuffer = new ArrayList<GameText>();
		imageBuffer = new ArrayList<Actor>();
		this.sprites = new HashMap<String,BufferedImage>();
		this.canvas = new Canvas();
	}
	

	
	public void paintText(Graphics g) {
		// TODO Auto-generated method stub
		int i = 0;
		GameText bufferedText;
		g.setColor(Color.RED);
		for (i = 0 ; i < textBuffer.size() ; i++){
			bufferedText = (GameText)textBuffer.get(i);
			g.drawString(  bufferedText.getText() ,  bufferedText.getX() , bufferedText.getY()  );
		}
	}
	
	public void paintImage(Graphics g) {
		// TODO Auto-generated method stub
		int i = 0;
		Actor actor;
		g.setColor(Color.RED);
		for (i = 0 ; i < imageBuffer.size() ; i++){
			actor = (Actor)imageBuffer.get(i);
			if (actor.isVisible()){
				g.drawImage(  getSprite( actor.getId() ),    actor.getX() , actor.getY(), null  );
			}
			
		}
	}
	
	public void clearBoard(Graphics g){
		canvas.setBackground(Color.BLACK);
		//g.setColor(Color.black);
	    //g.fillRect(0,0,getWidth(),getHeight());
		g.clearRect(0, 0, canvas.getWidth(),canvas.getHeight());
	}
	
	public void paintBoard(){
		paint( canvas.getGraphics());
	}
	
	
	
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		canvas.paint(g);
		clearBoard(g); //no es necesario
	    paintText(g);
	    paintImage(g);
	}
	
	
	public BufferedImage getSprite(String id){
		 BufferedImage img = (BufferedImage)sprites.get(id);
	     return img;
	}
	
	public int getSpriteWidth(String key){
		 BufferedImage img = (BufferedImage)sprites.get(key);
	     return img.getWidth();
	}
	public int getSpriteHeight(String key){
		BufferedImage img = (BufferedImage)sprites.get(key);
	    return img.getHeight();
	}
	
	 public boolean existsSprite(String nombre) {
		 return false;
	 }
	
	 public void createSprite( String path , String nombre  ){
		 BufferedImage img = (BufferedImage)sprites.get(nombre);
		 if (img == null) {
	          img = loadImage("res/"+path);
	          if (img != null){
	        	  sprites.put(nombre ,img);
	          }
	        }
	 }
	 
	 private BufferedImage loadImage(String nombre) {
	        URL url=null;
	        try {
	          url = getClass().getClassLoader().getResource(nombre);
	          return ImageIO.read(url);
	        } catch (Exception e) {
	          System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
	          System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
	          return null;
	        }
	 }

	 
		public void initBoard(int x, int y, int width , int height ){
			canvas.setBounds(0,0,width, height);
		}
		
		public void setCanvas(Canvas canvas){
			this.canvas = canvas;
		}
		
		public Canvas getCanvas(){
			return this.canvas;
		}
}
