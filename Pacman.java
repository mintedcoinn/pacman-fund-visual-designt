import greenfoot.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class Pacman extends Actor {

    public Pacman() {
        animationFrames[0] = new GreenfootImage("pacmeen.png");
        animationFrames[1] = new GreenfootImage("pacmeen1.png");
        animationFrames[2] = new GreenfootImage("pacmeen2.png");
        animationFrames[3] = new GreenfootImage("pacmeen3.png");

        for (GreenfootImage frame : animationFrames) {
            frame.scale(50, 50);
        }
        
        setImage(animationFrames[0]);
        
    }
    
    private static int[][] map = MyWorld.wrld;
    private final static int CELL_SIZE = MyWorld.worldPieceSize;
    private final static int CELL_HALF = MyWorld.worldHalfPieceSize;
    
    private int speed = 2;
    
    public static int matrixX = 24;
    public static int matrixY = 29;
    private int prevMX = 24;
    private int prevMY = 29;
    private int _allowed_dir = 10;
    private int where_from_came = 2;
    private int prev_rotat = 0;
    private int dir;
    
    public static int rotat = 270;
    private boolean canChangeDirection = false;
    public static boolean JoinNewCell = false;
    private boolean can = false;
    
    private int animationStep = 0;
    private int animationDelay = 5; 
    private int animationCounter = 0;
    
    private GreenfootImage[] animationFrames = new GreenfootImage[4]; // 4 кадра анимации
    
    public void act() {
        matrixNavigation(getX(), getY());
        circlenavigation();
        changeMatrixLocatioLog();
        inCenterOfCell();  
        ChangerLocation(_allowed_dir);
        eatCoin();
        
        handleAnimation(); 
        
        if (pillEffect) {
            pillEffectCounter--;
            if (pillEffectCounter <= 0) {
                pillEffect = false;
                MyWorld.currentPillEffect = false;
            }
        }
    }
    
    private boolean isRotating = false;
    private int targetRotation = 0;
    private int rotationDirection = 1; // 1 - по часовой, -1 - против часовой
    private boolean rotation;
    
    private void wherePacmanLook() {
        if (rotat != prev_rotat) {
            int diff = (rotat - prev_rotat + 360) % 360;
                                    
            if (diff>180){
                rotationDirection = -1;
                rotation=true;
            } else{
                rotationDirection = 1;
                rotation=false;
            }
            targetRotation = rotat;
            startRotationAnimation();
            prev_rotat = rotat;
        }
    }

    private void startRotationAnimation() {
        animationCounter = 0;
        isRotating = true;
        
    
        if (rotationDirection == 1) {
            animationStep = 0;
        } else {
            animationStep = animationFrames.length - 1;
        }
    }

    private void handleAnimation() {
        if (!isRotating) return;
    
        animationCounter++;
        if (animationCounter >= animationDelay) {
            animationCounter = 0;
            animationStep += rotationDirection;
    
            if (animationStep >= animationFrames.length || animationStep < 0) {
                endRotationAnimation();
                return;
            }
    
            GreenfootImage img = new GreenfootImage(animationFrames[animationStep]);
            if (rotation) img.rotate(targetRotation);
            if (!rotation) img.rotate(targetRotation-90);
            setImage(img);
        }
    }

    private void endRotationAnimation() {
        isRotating = false;
        GreenfootImage img = new GreenfootImage(animationFrames[0]);
        img.rotate(targetRotation);
        setImage(img);
    }    
    
    private int[] dirs = {1, 2, 4, 8};
    void ChangerLocation( int allowed_dirs){ 
        int newX = getX();
        int newY = getY();
        //allowed_dirs -= where_from_came;      

        List<Integer> _allowed_direction = new ArrayList<>();
        for (int i = 0; i<4; i++){
                if ((allowed_dirs & dirs[i]) != 0)_allowed_direction.add(i*90);
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
        
        if (_allowed_direction.contains(dir) ){
            can = true; 
            if(canChangeDirection == true){
            rotat = dir;   
            }
        } else{
            can=false;
        }
        
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
                    if (_allowed_direction.contains(dir) ){
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
        wherePacmanLook();
        setLocation(newX, newY);
        
    }
    
    public static int getAllowedDirsAt(int pixelX, int pixelY, int dir) {
        int tileSize = 20;
        int gridX = pixelX / tileSize;
        int gridY = pixelY / tileSize;

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
           
        return 16; 
    }
    
    private void changeMatrixLocatioLog(){
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
    
    private void inCenterOfCell(){
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
    
    private int pillEffectCounter = 0;
    private boolean pillEffect = false;
    private void eatCoin() {
        Coin coin = (Coin) getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            ((MyWorld)getWorld()).addScore(10); 
        }
    
        Cherry cherry = (Cherry) getOneIntersectingObject(Cherry.class);
        if (cherry != null) {
            getWorld().removeObject(cherry);
            ((MyWorld)getWorld()).addScore(50);
        }
        
        Pill pill = (Pill) getOneIntersectingObject(Pill.class);
        if (pill != null) {
            getWorld().removeObject(pill);
            MyWorld.POWER_PILL_COUNT -= MyWorld.POWER_PILL_COUNT;
            pillEffect=true;
            MyWorld.currentPillEffect = this.pillEffect;
            pillEffectCounter = 600;
        }
    }
    
}
