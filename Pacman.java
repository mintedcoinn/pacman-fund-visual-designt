import greenfoot.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class Pacman extends Actor {

    public Pacman() {
        GreenfootImage img = new GreenfootImage("pacmeen.png");
        img.scale(60, 40);
        setImage(img);
    }
    
    private static int[][] map = MyWorld.wrld;
    private final static int CELL_SIZE = MyWorld.worldPieceSize;
    private final static int CELL_HALF = MyWorld.worldHalfPieceSize;
    
    private int speed = 2;
    
    public static int matrixX = 24;
    public static int matrixY = 13;
    private int prevMX = 24;
    private int prevMY = 13;
    private int _allowed_dir = 10;
    private int where_from_came = 2;
    private int prev_rotat = 0;
    private int dir;
    
    private int rotat = 270;
    private boolean canChangeDirection = false;
    public static boolean JoinNewCell = false;
    private boolean can = false;
    
    public void act()
    {
        matrixNavigation(getX(),getY());
        circlenavigation();
        changeMatrixLocatioLog();
        inCenterOfCell();  
        
        ChangerLocation(_allowed_dir);
        eatCoin();
    }
    
        
    void ChangerLocation( int allowed_dirs){ 
        int newX = getX();
        int newY = getY();
        //allowed_dirs -= where_from_came;      
        
        
        //if there is a fork ghost will choose direction with his actual pattern
         //chaotic pattern. ghost will go to random direction
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
            default:
                speed =0;
                return;
            }
            
        if (Greenfoot.isKeyDown("right") ) {
            dir = 0;
        } else if (Greenfoot.isKeyDown("down") ) {
            dir = 90;
        } else if (Greenfoot.isKeyDown("left") ) {
            dir = 180;
        } else if (Greenfoot.isKeyDown("up") ) {
            dir = 270; 
        }
        
        if (random_allowed_dir.contains(dir) ){
            can = true; 
            if(canChangeDirection == true){
            rotat = dir;   
            }
        } else{
            can=false;
        }// нажатая клавиша не принадл массиву- останови
        // остановка не от else от принадл к массив а от
        //сделай проверку на следующую по направлению клетку если 16 то дойди до центра и останови 
        //сделай цикл x y
        //for (int y = 0; y < map.length; y++) {
            //for (int x = 0; x < map[y].length; x++) {
        //if(getX() == x*CELL_SIZE+CELL_HALF){
        if(getAllowedDirsAt(newX, newY, rotat)==16 ){
            if(can==false){
                
                //if (canChangeDirection ){
                    speed =0;
                    if (Greenfoot.isKeyDown("right") ) {
                        dir = 0;
                    } else if (Greenfoot.isKeyDown("down") ) {
                        dir = 90;
                    } else if (Greenfoot.isKeyDown("left") ) {
                        dir = 180;
                    } else if (Greenfoot.isKeyDown("up") ) {
                        dir = 270; 
                    }
                    if (random_allowed_dir.contains(dir) ){
                        can = true;
                        speed=4;
                        rotat=90;
                    } 
                //} 
                
            } else{
                speed=2;
            }         
        }//}
    //}}
        
        switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        setLocation(newX, newY);
        
    }
    
    public static int getAllowedDirsAt(int pixelX, int pixelY, int dir) {
        int tileSize = 20;
        int gridX = pixelX / tileSize;
        int gridY = pixelY / tileSize;
    
        // Проверка на границы карты
        if (gridY >= 0 && gridY < map.length && gridX >= 0 && gridX < map[0].length) {
            if(dir==0){
                return map[gridY][gridX+1];
            }
            if(dir==90){
                return map[gridY+1][gridX];
            }
            if(dir==180){
                return map[gridY][gridX-1];
            }
            if(dir==270){
                return map[gridY-1][gridX];
            }         
        }
    
        return 16; // Возвращаем тупик по умолчанию
    }
    
    private void changeMatrixLocatioLog(){//check when ghost join on new cell of matrix
        if( matrixX != prevMX || matrixY != prevMY){
            if (matrixX < prevMX) where_from_came = 1;
            if (matrixX > prevMX) where_from_came = 4;
            if (matrixY < prevMY) where_from_came = 2;
            if (matrixY > prevMY) where_from_came = 8;
            prevMX = matrixX;
            prevMY = matrixY;
            JoinNewCell = true;
        }
    }
    
    private void inCenterOfCell(){ //if ghost in center of cell it can change direction 
        if (JoinNewCell){
            if (getX() == matrixX*CELL_SIZE+CELL_HALF && getY() == matrixY*CELL_SIZE+CELL_HALF) 
            canChangeDirection = true;
            else canChangeDirection = false;
        }
    }
    private void matrixNavigation(int current_x, int current_y) {
        matrixX = current_x / CELL_SIZE;
        matrixY = current_y / CELL_SIZE;
        _allowed_dir = map[matrixY][matrixX];
    }
        private void circlenavigation(){
        if (matrixX==0) {
            int dy = getY();
            for(int i = 18; i !=0; i-=2)setLocation(i,dy);
            for(int i = 980; i != 950; i-=2)setLocation(i,dy);
            matrixNavigation(getX(),getY());
            where_from_came = 1;
            prevMX = matrixX+1;
            prevMY = matrixY;
            JoinNewCell = true;
        }
        if (matrixX== map[0].length-1) {
            int dy = getY();
            for(int i = 960; i !=978; i+=2)setLocation(i,dy);
            for(int i = 0; i != 30; i+=2)setLocation(i,dy);
            matrixNavigation(getX(),getY());
            where_from_came = 4;
            prevMX = matrixX-1
            ;
            prevMY = matrixY;
            JoinNewCell = true;
        }
    }    
    
    private void eatCoin() {
    Coin coin = (Coin) getOneIntersectingObject(Coin.class);
    if (coin != null) {
        getWorld().removeObject(coin);
        ((MyWorld)getWorld()).addScore(10); // вызываем метод addScore у MyWorld
    }

    Cherry cherry = (Cherry) getOneIntersectingObject(Cherry.class);
    if (cherry != null) {
        getWorld().removeObject(cherry);
        ((MyWorld)getWorld()).addScore(50);
    }
}

    
}
