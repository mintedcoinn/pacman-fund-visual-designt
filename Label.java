import greenfoot.*;

public class Label extends Actor {
    private String text;
    private int size;
    private int value = 0;
    public Label(String txt, int sz) {
        text = txt;
        size = sz;
    }
    
    public void act() {
        setImage(new GreenfootImage(text + value, size, Color.WHITE, null));
    }
    
    public void update_score(int n) {
        value += n;
    }
}
