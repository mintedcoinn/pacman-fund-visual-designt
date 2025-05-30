import greenfoot.*;

public class Label extends Actor {
    public Label(String text, int size) {
        GreenfootImage img = new GreenfootImage(text, size, Color.WHITE, null);
        setImage(img);
    }
}