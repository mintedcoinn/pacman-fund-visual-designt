import greenfoot.*;

public class PauseMenu extends World {
    private MyWorld myWorld;

    public PauseMenu(MyWorld myWorld) {
        super(600, 400, 1);
        GreenfootImage img = new GreenfootImage("pause.jpeg");
        img.scale(600, 290);
        setBackground(img);
        this.myWorld = myWorld;
        prepare();
        
    }
    
    private void prepare() {
        
        MenuButton mainMenuButton = new MenuButton("На главный экран");
        addObject(mainMenuButton, getWidth()/2, getHeight()/2 + 40);
        
        MenuButton resumeButton = new MenuButton("Продолжить игру");
        addObject(resumeButton, getWidth()/2, getHeight()/2 - 40);
    }
    
    public void resumeGame() {
        Greenfoot.setWorld(myWorld);
        myWorld.resumeGame();
    }
    
    public void returnToMainMenu() {
        Greenfoot.setWorld(new MenuWorld());
    }
}