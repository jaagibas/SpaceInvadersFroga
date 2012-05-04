package edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders;

import java.util.ArrayList;

import edu.mondragon.eps.jaagirre.game.desktop.tdd.spaceinvaders.actors.Actor;

/*Clase pque representa los invasores de cada nivel*/
public class Level {
       ArrayList<Actor> level;
      
       public Level(){
             this.level = new ArrayList<Actor>();
       }
      
       public Level(ArrayList<Actor> level){
             this.level = level;
       }
 
       public ArrayList<Actor> getLevel() {
             return level;
       }
 
       public void setLevel(ArrayList<Actor> level) {
             this.level = level;
       }
      
      
 
}
