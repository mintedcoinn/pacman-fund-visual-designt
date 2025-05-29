import greenfoot.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Red_ghost extends Actor
{
    public Red_ghost()
    {
        setImage("RedGhost (1).png");
    }
    private static int PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
    private final int[][] map = MyWorld.wrld;
    private int fearStatusTimer = 0;
    private int speed = 20;
    private final static int CELL_SIZE = MyWorld.worldPieceSize;
    private final static int CELL_HALF = MyWorld.worldHalfPieceSize;
    private int matrixX = 10;
    private int matrixY = 9;
    
    public void act()
    {
        if (MyWorld.POWER_PILL_COUNT < PREVIOUS_COUNT_OF_PILLS){
            fearStatusTimer += 50* PREVIOUS_COUNT_OF_PILLS;
            PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
        }
        
        if (fearStatusTimer >0 ){
            fearStatusTimer -= 1;
            FearMod();
        }
    }
    
    private void ScatterMod()
    {
        
    }
    private void ChaseMod()
    {
        
    }
    private void FearMod()
    {
        
    }
    
    void ChangerLocation(int direction_pattern, int allowed_dirs){ //direction_pattern : -1 - chaotic, 0 - straight, 1 - hunt
        int newX = getX();
        int newY = getY();
        int rotat = getRotation();
        
        if (direction_pattern == -1){
            List<Integer> random_allowed_dir = new ArrayList<>();
            switch (allowed_dirs){
            case 15:random_allowed_dir = Arrays.asList(0, 90, 180, 270);break;
            case 14:random_allowed_dir = Arrays.asList(90, 180, 270);break;
            case 13:random_allowed_dir = Arrays.asList(0, 180, 270);break;
            case 12:random_allowed_dir = Arrays.asList(180, 270);break;
            case 11:random_allowed_dir = Arrays.asList(0, 90, 270);break;
            case 10:random_allowed_dir = Arrays.asList(90, 270);break;
            case 9:random_allowed_dir = Arrays.asList(0, 270);break;
            case 8:random_allowed_dir = Arrays.asList(270);break;
            case 7:random_allowed_dir = Arrays.asList(0, 90, 180);break;
            case 6:random_allowed_dir = Arrays.asList(90, 180);break;
            case 5:random_allowed_dir = Arrays.asList(0, 180);break;
            case 4:random_allowed_dir = Arrays.asList( 180);break;
            case 3:random_allowed_dir = Arrays.asList(0, 90);break;
            case 2:random_allowed_dir = Arrays.asList(90);break;
            case 1:random_allowed_dir = Arrays.asList(0);break;
            }
            
        int randIndex =  Greenfoot.getRandomNumber(random_allowed_dir.size());
        rotat = random_allowed_dir.get(randIndex);
        setRotation(rotat);
        }
        
        if (direction_pattern == 0){
            List<Integer> _allowed_direction = new ArrayList<>();
            switch (allowed_dirs){
            case 15:_allowed_direction = Arrays.asList(0, 90, 180, 270);break;
            case 14:_allowed_direction = Arrays.asList(90, 180, 270);break;
            case 13:_allowed_direction = Arrays.asList(0, 180, 270);break;
            case 12:_allowed_direction = Arrays.asList(180, 270);break;
            case 11:_allowed_direction = Arrays.asList(0, 90, 270);break;
            case 10:_allowed_direction = Arrays.asList(90, 270);break;
            case 9:_allowed_direction = Arrays.asList(0, 270);break;
            case 8:_allowed_direction = Arrays.asList(270);break;
            case 7:_allowed_direction = Arrays.asList(0, 90, 180);break;
            case 6:_allowed_direction = Arrays.asList(90, 180);break;
            case 5:_allowed_direction = Arrays.asList(0, 180);break;
            case 4:_allowed_direction = Arrays.asList( 180);break;
            case 3:_allowed_direction = Arrays.asList(0, 90);break;
            case 2:_allowed_direction = Arrays.asList(90);break;
            case 1:_allowed_direction = Arrays.asList(0);break;
            }
            
        }
        
        switch (rotat) {
            case 0:   newY -= speed; break;
            case 90:  newX += speed; break;
            case 180: newY += speed; break;
            case 270: newX -= speed; break;
        }
        
        setLocation(newX, newY);
        
    }
    
    void GhostCaptured(){}
    
    private void matrixNavigation(int current_x, int current_y) {
        matrixX = current_x / CELL_SIZE;
        matrixY = current_y / CELL_SIZE;
    }
}
