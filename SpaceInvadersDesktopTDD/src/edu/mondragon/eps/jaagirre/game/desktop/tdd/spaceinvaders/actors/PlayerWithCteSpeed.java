package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors;
import java.awt.image.BufferedImage;



public class PlayerWithCteSpeed extends Player {
	int dir;
    
    public PlayerWithCteSpeed(BufferedImage image, int x, int y, int xLimit,
                 int yLimit) {
          super(image, x, y, xLimit, yLimit);
          // TODO Auto-generated constructor stub
    }
   
   
    @Override
    public void executeBehaviour() {
          super.executeBehaviour();
          //TODO
          switch (dir){
                 case 0:
                        moveRight();
                        break;
                 case 1:
                        moveLeft();
                        break;
                 case 2:
                        moveUp();
                        break;
                 case 3:
                        moveDown();
                        break;
                 default:
          }
         
    }


    public int getDir() {
          return dir;
    }


    public void setDir(int dir) {
          this.dir = dir;
    }
   
    public void moveRight(){
          super.moveRight();
          //Todo
          this.setDir(0);
    }
    public void moveLeft(){
          super.moveLeft();
          //Todo
          this.setDir(1);
    }
    public void moveUp(){
          super.moveUp();
          //Todo
          this.setDir(2);
    }
    public void moveDown(){
          super.moveDown();
          //Todo
          this.setDir(3);
    }
}
