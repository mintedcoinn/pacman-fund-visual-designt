import greenfoot.*;

public class Wall extends Actor {
    public Wall(int n) {
        GreenfootImage img = new GreenfootImage("block.png");
        if (n == 2) img = new GreenfootImage("up_round_block.png");
        if (n == 3) img = new GreenfootImage("down_round_block.png");
        if (n == 4) img = new GreenfootImage("left_round_block.png");
        if (n == 5) img = new GreenfootImage("right_round_block.png");
        if (n == 6) img = new GreenfootImage("left_block.png");
        if (n == 7) img = new GreenfootImage("right_block.png");
        if (n == 8) img = new GreenfootImage("down_right_block.png");
        if (n == 9) img = new GreenfootImage("down_left_block.png");
        img.scale(20, 20);
        setImage(img);
    }    
}