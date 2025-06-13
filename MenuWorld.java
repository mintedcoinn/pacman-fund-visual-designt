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
        
        Actor imageActor = new Actor() {};
        GreenfootImage Image = new GreenfootImage("instructions.png");
        Image.scale(330, 280);  
        imageActor.setImage(Image);
        addObject(imageActor, 820, 150);
        
    }

    public void startGame() {
        Greenfoot.setWorld(new MyWorld());
    }
}