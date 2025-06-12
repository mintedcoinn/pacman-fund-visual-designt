import greenfoot.*;

public class Pill extends Actor{
    
    public Pill() {
        GreenfootImage img = new GreenfootImage(15, 15);
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, 15, 15);
        setImage(img);
    }
}