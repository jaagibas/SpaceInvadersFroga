package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;


public class GameImage {
	
	String id;
	private int x , y;
	private int xLimit , yLimit;
	private int width;
	private int height;
	
	private boolean visible;
	
	public GameImage(String id ,  int  x ,int  y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.xLimit = 600;
		this.yLimit = 400;
		this.visible = true;
		//this.width = image.getWidth();
		//this.height = image.getHeight();
	}
	

	public GameImage(String image, int x, int y, int xLimit, int yLimit,
			int width, int height, boolean visible) {
		super();
		this.id = image;
		this.x = x;
		this.y = y;
		this.xLimit = xLimit;
		this.yLimit = yLimit;
		this.width =  width;
		this.height = height;
		this.visible = visible;
	}


	public GameImage(String image, int x, int y, int xLimit, int yLimit) {
		super();
		this.id = image;
		this.x = x;
		this.y = y;
		this.xLimit = xLimit;
		this.yLimit = yLimit;
		this.visible = true;
		//this.width = image.getWidth();
		//this.height = image.getHeight();
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


	public void setHeight(int height) {
		this.height = height;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		if ( (x > 0) &&  (x < this.xLimit)){
			this.x = x;
		}else{
			if ( x  >  this.getxLimit()-1 ){
				this.x = this.getxLimit()-1;
			}else{
				this.x = 1;
			}
		}
		
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if ( y > 0 && y < this.yLimit)
			this.y = y;
		else{
			if ( y  >  this.getyLimit()-1 ){
				this.y = this.getyLimit()-1;
			}else{
				this.y = 1;
			}
		}
			
	}

	

	public int getxLimit() {
		return xLimit;
	}

	public void setxLimit(int xLimit) {
		this.xLimit = xLimit;
	}

	public int getyLimit() {
		return yLimit;
	}

	public void setyLimit(int yLimit) {
		this.yLimit = yLimit;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	
	
	
	
}
