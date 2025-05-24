import greenfoot.*;

public class Wall extends Actor {
    public Wall() {
        GreenfootImage img = new GreenfootImage("block.png");
        img.scale(40, 40);
        setImage(img);
    }    
}