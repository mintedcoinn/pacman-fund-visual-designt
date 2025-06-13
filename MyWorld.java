import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class MyWorld extends World {
    public static int[][] wrld ={
    {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    {-1,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,-1,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,-1},
    {-1,16, 3, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 6,16,-1,16, 3, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 6,16,-1},
    {-1,16,10,16,16,16,16,16,10,16,16,16,16,16,16,16,16,16,10,16,16,16,10,16,-1,16,10,16,16,16,10,16,16,16,16,16,16,16,16,16,10,16,16,16,16,16,10,16,-1},
    {-1,16,10,16,-6,-1,-7,16,10,16,-6,-1,-1,-1,-1,-1,-7,16,10,16,-2,16,10,16,-1,16,10,16,-2,16,10,16,-6,-1,-1,-1,-1,-1,-7,16,10,16,-6,-1,-7,16,10,16,-1},
    {-1,16,10,16,-9,-1,-8,16,10,16,-9,-1,-1,-1,-1,-1,-8,16,10,16,-1,16,10,16,-1,16,10,16,-1,16,10,16,-9,-1,-1,-1,-1,-1,-8,16,10,16,-9,-1,-8,16,10,16,-1},
    {-1,16,10,16,16,16,16,16,10,16,16,16,16,16,16,16,16,16,10,16,-1,16,10,16,-1,16,10,16,-1,16,10,16,16,16,16,16,16,16,16,16,10,16,16,16,16,16,10,16,-1},
    {-1,16,11, 5, 5, 5, 5, 5,15, 5, 5, 5, 7, 5, 5, 5, 5, 5,12,16,-1,16,10,16,-1,16,10,16,-1,16, 9, 5, 5, 5, 5, 5, 7, 5, 5, 5,15, 5, 5, 5, 5, 5,14,16,-1},
    {-1,16,10,16,16,16,16,16,10,16,16,16,10,16,16,16,16,16,16,16,-1,16,10,16,-1,16,10,16,-1,16,16,16,16,16,16,16,10,16,16,16,10,16,16,16,16,16,10,16,-1},
    {-1,16,10,16,-4,-1,-5,16,10,16,-2,16,10,16,-4,-1,-1,-1,-1,-1,-1,16,10,16,-3,16,10,16,-1,-1,-1,-1,-1,-1,-5,16,10,16,-2,16,10,16,-4,-1,-5,16,10,16,-1},
    {-1,16,10,16,16,16,16,16,10,16,-1,16,10,16,16,16,16,16,16,16,16,16,10,16,16,16,10,16,16,16,16,16,16,16,16,16,10,16,-1,16,10,16,16,16,16,16,10,16,-1},
    {-1,16, 9, 5, 5, 5, 7, 5,12,16,-1,16, 9, 5, 5, 5, 7, 5, 5, 5, 5, 5,13, 5, 7, 5,13, 5, 5, 5, 5, 5, 7, 5, 5, 5,12,16,-1,16, 9, 5, 7, 5, 5, 5,12,16,-1},
    {-1,16,16,16,16,16,10,16,16,16,-1,16,16,16,16,16,10,16,16,16,16,16,16,16,10,16,16,16,16,16,16,16,10,16,16,16,16,16,-1,16,16,16,10,16,16,16,16,16,-1},
    {-9,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-1,-1,-5,16,10,16,-6,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-7,16,10,16,-4,-1,-1,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-8},
    {16,16,16,16,16,16,10,16,16,16,16,16,16,16,16,16,10,16,-1,16,16,16,16,16,10,16,16,16,16,16,-1,16,10,16,16,16,16,16,16,16,16,16,10,16,16,16,16,16,16},
    { 1, 5, 5, 5, 5, 5,15, 5, 5, 5, 5, 5, 5, 5, 5, 5,14,16,-1,16, 3, 2, 7, 7,10, 7, 7, 7, 6,16,-1,16,11, 5, 5, 5, 5, 5, 5, 5, 5, 5,15, 5, 5, 5, 5, 5, 4},
    {16,16,16,16,16,16,10,16,16,16,16,16,16,16,16,16,10,16,-1,16,11,11,11,15,10,15,15,15,14,16,-1,16,10,16,16,16,16,16,16,16,16,16,10,16,16,16,16,16,16},
    {-6,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-1,-1,-7,16,10,16,-1,16,11,11,11,15,10,15,15,15,14,16,-1,16,10,16,-6,-1,-1,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-7},
    {-1,16,16,16,16,16,10,16,16,16,16,16,16,16,-1,16,10,16,-1,16, 9, 9, 9, 9,10,13,13,13,12,16,-1,16,10,16,-1,16,16,16,16,16,16,16,10,16,16,16,16,16,-1},
    {-1,16, 3, 5, 5, 5,15, 5, 5, 5, 5, 5, 6,16,-1,16,10,16,-1,16,16,16,16,16,16,16,16,16,16,16,-1,16,10,16,-1,16, 3, 5, 5, 5, 5, 5,15, 5, 5, 5, 6,16,-1},
    {-1,16,10,16,16,16,10,16,16,16,16,16,10,16,-3,16,10,16,-9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-8,16,10,16,-3,16,10,16,16,16,16,16,10,16,16,16,10,16,-1},
    {-1,16,10,16,-2,16,10,16,-6,-1,-7,16,10,16,16,16,10,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,10,16,16,16,10,16,-6,-1,-7,16,10,16,-2,16,10,16,-1},
    {-1,16,10,16,-1,16,10,16,-1,-1,-1,16, 9, 5, 5, 5,15, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5,15, 5, 5, 5,12,16,-1,-1,-1,16,10,16,-1,16,10,16,-1},
    {-1,16,10,16,-1,16,10,16,-1,-1,-1,16,16,16,16,16,10,16,16,16,10,16,16,16,16,16,16,16,10,16,16,16,10,16,16,16,16,16,-1,-1,-1,16,10,16,-1,16,10,16,-1},
    {-1,16,10,16,-1,16,10,16,-9,-1,-1,-1,-1,-1,-5,16,10,16,-2,16,10,16,-4,-1,-1,-1,-5,16,10,16,-2,16,10,16,-4,-1,-1,-1,-1,-1,-8,16,10,16,-1,16,10,16,-1},
    {-1,16,10,16,-1,16,10,16,16,16,16,16,16,16,16,16,10,16,-1,16,10,16,16,16,16,16,16,16,10,16,-1,16,10,16,16,16,16,16,16,16,16,16,10,16,-1,16,10,16,-1},
    {-1,16,10,16,-1,16, 9, 5, 5, 5, 7, 5, 5, 5, 5, 5,12,16,-1,16, 9, 5, 5, 5, 7, 5, 5, 5,12,16,-1,16, 9, 5, 5, 5, 5, 5, 7, 5, 5, 5,12,16,-1,16,10,16,-1},
    {-1,16,10,16,-1,16,16,16,16,16,10,16,16,16,16,16,16,16,-1,16,16,16,16,16,10,16,16,16,16,16,-1,16,16,16,16,16,16,16,10,16,16,16,16,16,-1,16,10,16,-1},
    {-1,16,10,16,-1,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-5,16,10,16,-4,-1,-1,-1,-1,16,10,16,-1},
    {-1,16,10,16,16,16,16,16,16,16,10,16,16,16,16,16,16,16,16,16,16,16,16,16,10,16,16,16,16,16,16,16,16,16,16,16,16,16,10,16,16,16,16,16,16,16,10,16,-1},
    {-1,16, 9, 5, 5, 5, 5, 5, 5, 5,13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,13, 5, 5, 5, 5, 5, 5, 5,12,16,-1},
    {-1,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,-1},
    {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
    };
    public final static int worldPieceSize = 20;
    public final static int worldHalfPieceSize = worldPieceSize /2;
    public static int POWER_PILL_COUNT = 5;
    public static boolean currentPillEffect;
    private boolean gamePaused = false;
    public static int CHASE_TIMER = 0;
    private int CHASE_TIMER_CD = 450;
    
    public static boolean redInWorld = false;
    private int redCoolDown = 0;
    public static boolean yellowInWorld = false;
    private int yellowCoolDown = 0;
    public static boolean blueInWorld = false;
    private int blueCoolDown = 0;
    public static boolean pinkInWorld = false;
    private int pinkCoolDown = 0;

    public static int scoreValue = 0;
    private Label scoreLabel;
    private int cherrySpawnTimer = 0;
    private int cherrySpawnCooldown = Greenfoot.getRandomNumber(1000) + 1000;

    Actor imageActor = new Actor() {};

    public MyWorld() {
        super(980, 700, 1);
        setBackground("background.jpg");
        
        buildLab();
        spawnPill();

        scoreLabel = new Label("Score: ", 25, scoreValue);
        addObject(scoreLabel, 70, 680);
        Portal a = new Portal();
        addObject(a, 35, 310);

        GreenfootImage Image = new GreenfootImage("door.png");
        Image.scale(60, 10);  
        imageActor.setImage(Image);
        addObject(imageActor, 490, 270);

        spawnRed();
        spawnBlue();
        spawnYellow();
        spawnPink();
        spawnPacman();
    }
    
   

    public void act() {
        if (Greenfoot.isKeyDown("escape")) {
            pauseGame();
        }
        if (!redInWorld) {
            redCoolDown += 100;
        }
        if (!redInWorld && redCoolDown > 0) {
            spawnRed();
        }
        if (CHASE_TIMER_CD == 0){
            CHASE_TIMER = 450;
        }
        if (CHASE_TIMER >0){
            CHASE_TIMER -=1;
            CHASE_TIMER_CD +=2;
        }
        if (CHASE_TIMER == 0){
            CHASE_TIMER_CD -= 1;
        }
        
        spawnCherryTimer();
        checkWinCondition();
    }

    private void checkWinCondition() {
        if (getObjects(Coin.class).isEmpty()) {
            showText("YOU WIN!", getWidth() / 2, getHeight() / 2);
            Greenfoot.stop();
        }
    }

    public void pauseGame() {
        if (!gamePaused) {
            gamePaused = true;
            Greenfoot.setWorld(new PauseMenu(this));
        }
    }

    public void resumeGame() {
        gamePaused = false;
    }
    
    private void spawnPill(){
        addObject(new Pill(), 2 * worldPieceSize + worldHalfPieceSize, 2 * worldPieceSize + worldHalfPieceSize);
        addObject(new Pill(), 46 * worldPieceSize + worldHalfPieceSize, 2 * worldPieceSize + worldHalfPieceSize);
        addObject(new Pill(), 2 * worldPieceSize + worldHalfPieceSize, 30 * worldPieceSize + worldHalfPieceSize);
        addObject(new Pill(), 46 * worldPieceSize + worldHalfPieceSize, 30 * worldPieceSize + worldHalfPieceSize);
        addObject(new Pill(), 24 * worldPieceSize + worldHalfPieceSize, 11 * worldPieceSize + worldHalfPieceSize);

    }
    private void buildLab() {
        
        for (int y = 0; y < wrld.length; y++) {
            for (int x = 0; x < wrld[y].length; x++) {
                if (wrld[y][x] != 0) {
                    addObject(new Wall(wrld[y][x]), x * worldPieceSize + worldHalfPieceSize, 
                    y * worldPieceSize + worldHalfPieceSize);
                }
                
    
                if ((!(x == 2 && y == 2) && !(x == 46 && y == 2) && 
                !(x == 2 && y == 30) && !(x == 46 && y == 30) && !(x == 24 && y == 11) &&

                
                    (wrld[y][x] > 0 && wrld[y][x] < 16 && x < 20 || 
                     wrld[y][x] > 0 && wrld[y][x] < 16 && x > 30 ||
                     wrld[y][x] > 0 && wrld[y][x] < 16 && y < 12 ||
                     wrld[y][x] > 0 && wrld[y][x] < 16 && y > 20) &&
                    (wrld[y][x] > 0 && wrld[y][x] < 16 && x > 5 ||
                     wrld[y][x] > 0 && wrld[y][x] < 16 && y < 12 ||
                     wrld[y][x] > 0 && wrld[y][x] < 16 && y > 16) &&
                    (wrld[y][x] > 0 && wrld[y][x] < 16 && x < 43 ||
                     wrld[y][x] > 0 && wrld[y][x] < 16 && y < 12 ||
                     wrld[y][x] > 0 && wrld[y][x] < 16 && y > 16))) {
                    addObject(new Coin(), x * worldPieceSize + worldHalfPieceSize, y * worldPieceSize + worldHalfPieceSize);
                }
            }
        }
    }

    public void spawnRed() {
        addObject(new Red_ghost(), 24 * worldPieceSize + worldHalfPieceSize, 13 * worldPieceSize + worldHalfPieceSize);
        redInWorld = true;
        redCoolDown = 0;
    }

    public void spawnBlue() {
        addObject(new Blue_ghost(), 21 * worldPieceSize + worldHalfPieceSize, 17 * worldPieceSize + worldHalfPieceSize);
        blueInWorld = true;
        blueCoolDown = 0;
    }

    public void spawnYellow() {
        addObject(new Yellow_ghost(), 27 * worldPieceSize + worldHalfPieceSize, 17 * worldPieceSize + worldHalfPieceSize);
        yellowInWorld = true;
        yellowCoolDown = 0;
    }

    public void spawnPink() {
        addObject(new Pink_ghost(), 24 * worldPieceSize + worldHalfPieceSize, 17 * worldPieceSize + worldHalfPieceSize);
        pinkInWorld = true;
        pinkCoolDown = 0;
    }

    public void spawnPacman() {
        addObject(new Pacman(), 24 * worldPieceSize + worldHalfPieceSize, 29 * worldPieceSize + worldHalfPieceSize);
    }

    public void addScore(int points) {
        scoreValue += points;
        scoreLabel.update_score(points);
    }

    private void spawnCherryTimer() {
        if (getObjects(Cherry.class).isEmpty()) {
            cherrySpawnTimer++;
        }
        if (getObjects(Cherry.class).isEmpty() && cherrySpawnTimer >= cherrySpawnCooldown) {
            spawnCherry();
            cherrySpawnTimer = 0;
            cherrySpawnCooldown = Greenfoot.getRandomNumber(1000) + 1000;
        }
    }
    
        
    private void spawnCherry() {
        if (!getObjects(Cherry.class).isEmpty()) {
            return;
        }
        
        List<Integer> preferredLocations = new ArrayList<>();
        for (int y = 0; y < wrld.length; y++) {
            for (int x = 0; x < wrld[y].length; x++) {
                if (wrld[y][x] > 0 && wrld[y][x] < 16) {
                    if (
                        (x < 20 || x > 30 || y < 12 || y > 20) &&
                        (x > 2 || y < 12 || y > 16) &&
                        (x < 43 || y < 12 || y > 16)
                    ) {
                        int centerX = x * worldPieceSize + worldHalfPieceSize;
                        int centerY = y * worldPieceSize + worldHalfPieceSize;
                        boolean hasCoin = !getObjectsAt(centerX, centerY, Coin.class).isEmpty();
                        if (!hasCoin) {
                            preferredLocations.add(x*100+y);
                            //x*100+y
                        }
                    }
                }
            }
        }
        if (!preferredLocations.isEmpty()) {
            int chosenLoc = preferredLocations.get(Greenfoot.getRandomNumber(preferredLocations.size()));
            int y= chosenLoc%100;
            int x = chosenLoc/100;
            int spawnX = x * worldPieceSize + worldHalfPieceSize;
            int spawnY = y * worldPieceSize + worldHalfPieceSize;
            addObject(new Cherry(), spawnX, spawnY);
        }
    }
    
}
