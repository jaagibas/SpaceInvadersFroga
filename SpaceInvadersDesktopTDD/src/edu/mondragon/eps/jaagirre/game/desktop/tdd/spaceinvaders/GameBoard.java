package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

public class GameBoard extends Canvas {
	
	ArrayList<GameText> textBuffer;
	ArrayList<Actor> imageBuffer;
	//backgroundBuffer
	private HashMap<String,BufferedImage> sprites;
	
	public GameBoard() {
		super();
		// TODO Auto-generated constructor stub
		textBuffer = new ArrayList<GameText>();
		imageBuffer = new ArrayList<Actor>();
		this.sprites = new HashMap<String,BufferedImage>();
	}
	
	public ArrayList<GameText> getTextBuffer() {
		return textBuffer;
	}

	public void setImageBuffer(ArrayList<Actor> imageBuffer) {
		this.imageBuffer = imageBuffer;
	}

	public ArrayList<Actor> getImageBuffer() {
		return imageBuffer;
	}

	public void addText(GameText str){
		textBuffer.add( str );
	}
	
	public void addActor(Actor image){
		imageBuffer.add( image );
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
		this.setBackground(Color.BLACK);
		//g.setColor(Color.black);
	    //g.fillRect(0,0,getWidth(),getHeight());
		g.clearRect(0, 0, getWidth(),getHeight());
	}
	

	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		clearBoard(g); //no es necesario
	    paintText(g);
	    paintImage(g);
	}
	
	
	public BufferedImage getSprite(String id){
		 BufferedImage img = (BufferedImage)sprites.get(id);
	     return img;
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

}



