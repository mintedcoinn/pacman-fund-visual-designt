import greenfoot.*;

public class Wall extends Actor {
    GreenfootImage img = new GreenfootImage("background.jpg");
    static int CELL_SIZE = MyWorld.worldPieceSize;
    public Wall(int n) {
        
        switch (n){
        case -1: img = new GreenfootImage("block.png");break;
        case -2: img = new GreenfootImage("up_round_block.png"); break;
        case -3: img = new GreenfootImage("down_round_block.png");break;
        case -4: img = new GreenfootImage("left_round_block.png");break;
        case -5: img = new GreenfootImage("right_round_block.png");break;
        case -6: img = new GreenfootImage("left_block.png");break;
        case -7:img = new GreenfootImage("right_block.png");break;
        case -8: img = new GreenfootImage("down_right_block.png");break;
        case -9: img = new GreenfootImage("down_left_block.png");break;
        default:break;}
        if (img != null){
        img.scale(CELL_SIZE, CELL_SIZE);
        setImage(img);
        }
    }    
}