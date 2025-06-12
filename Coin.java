import greenfoot.*;

public class Coin extends Actor {
    public Coin() {
        GreenfootImage img = new GreenfootImage(6, 6);
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, 6, 6);
        setImage(img);
    }
}