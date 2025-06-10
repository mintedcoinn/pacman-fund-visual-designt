import greenfoot.*;

public class MenuWorld extends World {
    public MenuWorld() {
        super(980, 660, 1);
        MusicManager.play("Doom_2.mp3");
        prepare();
    }

    private void prepare() {
        GreenfootImage img = new GreenfootImage("menu_background.jpg");
        img.scale(980, 760);
        setBackground(img);
        
        MenuButton mainMenuButton = new MenuButton("Начать игру");
        addObject(mainMenuButton, 490, 280);
        
    }

    public void startGame() {
        Greenfoot.setWorld(new MyWorld());
    }
}