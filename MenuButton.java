import greenfoot.*;

public class MenuButton extends Actor {
    private GreenfootImage normal;
    private GreenfootImage hover;
    private String text;
    
    public MenuButton(String text) {
        this.text = text;
        createImages();
        setImage(normal);
    }
    
    private void createImages() {
        int width = 300;
        int height = 50;
        Color bgNormal = new Color(0, 0, 0);
        Color bgHover = new Color(0, 0, 0);
        Color textColor = Color.WHITE;
        int fontSize = 28;
        
        normal = new GreenfootImage(width, height);
        normal.setColor(bgNormal);
        normal.fillRect(0, 0, width, height);
        normal.setColor(textColor);
        normal.setFont(new Font("Arial", true, false, fontSize));
        normal.drawString(text, ((width - 20) - normal.getFont().getSize() * text.length()/2)/2, height*3/4);
        
        hover = new GreenfootImage(width, height);
        hover.setColor(bgHover);
        hover.fillRect(0, 0, width, height);
        hover.setColor(Color.YELLOW);
        hover.setFont(new Font("Arial", true, false, fontSize));
        hover.drawString(text, ((width - 20) - hover.getFont().getSize() * text.length()/2)/2, height*3/4);
        
        normal.setColor(Color.WHITE);
        normal.drawRect(0, 0, width-1, height-1);
        hover.setColor(Color.YELLOW);
        hover.drawRect(0, 0, width-1, height-1);
    }
    
    public void act() {
        if (Greenfoot.mouseMoved(this)) {
            setImage(hover);
        } else if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            setImage(normal);
        }
        if (Greenfoot.mouseClicked(this) && this.text == "Продолжить игру") {
            ((PauseMenu)getWorld()).resumeGame();
        }
        if (Greenfoot.mouseClicked(this) && this.text == "На главный экран") {
            ((PauseMenu)getWorld()).returnToMainMenu();
        }
        if (Greenfoot.mouseClicked(this) && this.text == "Начать игру") {
            ((MenuWorld)getWorld()).startGame();
        }
    }
}