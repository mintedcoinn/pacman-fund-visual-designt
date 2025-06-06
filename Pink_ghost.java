import greenfoot.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Pink_ghost extends Actor
{
    public Pink_ghost()
    {
        setImage("PGhost.png");
    }
    private static int PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
    private static int[][] map = MyWorld.wrld;
    private final static int CELL_SIZE = MyWorld.worldPieceSize;
    private final static int CELL_HALF = MyWorld.worldHalfPieceSize;
    
    private int fearStatusTimer = 0;
    private int speed = 2;
    
    public int matrixX = 24;
    public int matrixY = 13;
    private int prevMX = 24;
    private int prevMY = 13;
    private int _allowed_dir = 10;
    private int where_from_came = 2;
    private int prev_rotat = 0;
    private int savedPacmanCoords = 0;    
    private boolean pacmanWasHereFlag = false;
    
    private int rotat = 270;
    private boolean canChangeDirection = false;
    private boolean JoinNewCell = false;
    
    public void act()
    {
        matrixNavigation(getX(),getY());
        circlenavigation();
        changeMatrixLocatioLog();
        inCenterOfCell();
        
        if (MyWorld.POWER_PILL_COUNT < PREVIOUS_COUNT_OF_PILLS){
            fearStatusTimer += 50* PREVIOUS_COUNT_OF_PILLS;
            PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
        }   
        
        if (fearStatusTimer >0 ){
            fearStatusTimer -= 1;
            FearMod(_allowed_dir);
            return;
        }
        
        if (pacmanWasHereFlag){
            ChaseMod(_allowed_dir);
            return;
        }
        
        if (isPacmanFound()){
            ChaseMod(_allowed_dir);
            return;
        }
        
        ScatterMod(_allowed_dir);
    }
    
    private void ScatterMod(int allowed_dirs)//straight pattern. ghost will just walk. if it can turn on any side or just go straight,
    //ghost will go straight with bigger chance
    {       // if it can't go straight, ghost will chose direction with random from allowed
        int newX = getX();
        int newY = getY();
        allowed_dirs -= where_from_came;
        
        if (!canChangeDirection){ 
            switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        whereGhostLook();
        
        setLocation(newX, newY);
        return;
        }
        
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
            
        if (!_allowed_direction.contains(rotat)){
            int randIndex =  Greenfoot.getRandomNumber(_allowed_direction.size());
            rotat = _allowed_direction.get(randIndex);
            
        }
        else{
            List<Integer> _prefer_straight_but = new ArrayList<>();
            for (int g =0; g< _allowed_direction.size();g++){
                _prefer_straight_but.add(_allowed_direction.get(g));
            }
            _prefer_straight_but.add(rotat);
            _prefer_straight_but.add(rotat);
            int randIndex =  Greenfoot.getRandomNumber(_allowed_direction.size());
            rotat = _allowed_direction.get(randIndex);
            
            
        }

        switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        whereGhostLook();
        canChangeDirection = false;
        setLocation(newX, newY);
    }
    private void ChaseMod(int allowed_dirs)//hunt pattern. if pacman detected => go to him
    {
        int newX = getX();
        int newY = getY();
        allowed_dirs -= where_from_came;
        
        int pX = pacmanCoords()/100;
        int pY = pacmanCoords()%100;
        if (pX == matrixX){
                if (matrixY < pY) rotat = 90;
                else rotat = 270;
        }
        if (pY == matrixY){
                if (matrixX < pX) rotat = 0;
                else rotat = 180;
        }
        switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        whereGhostLook();
        canChangeDirection = false;
        setLocation(newX, newY);
    }
    private void FearMod(int allowed_dirs) //chaotic pattern. ghost will go to random direction
    {
        int newX = getX();
        int newY = getY();
        allowed_dirs -= where_from_came;
        
        if (!canChangeDirection){ 
            switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        whereGhostLook();
        setLocation(newX, newY);
        return;
        }
        
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
        
        switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        whereGhostLook();
        canChangeDirection = false;
        setLocation(newX, newY);
        
    }
    
    void somebodyCaptured(){
        if (fearStatusTimer > 0)
        {
            MyWorld.pinkInWorld = false;
            getWorld().removeObject(this);
        }
        else
        {
            
        }
    }
    
    boolean isPacmanFound(){// if pacman is on same line as ghost, ghost will check visibility on pacman. if there are no walls, ghost will chase pacman
        int pX = pacmanCoords()/100;
        int pY = pacmanCoords()%100;
        int lineP = -1;
        int lineG = -1;
        if (pX==matrixX && pY==matrixY){
            somebodyCaptured();
            return false;
        }
        if(pX==matrixX) {
            lineP = pY;
            lineG = matrixY;
            if(lineG > lineP){
            for (int i = lineG; i > lineP; i--){
                if (map[i][pX] <0) return false;
                }
            pacmanWasHereFlag = true;
            savedPacmanCoords = pX*100 + pY;
            return true;
            }
            else{
            for (int i = lineG; i < lineP; i++){
                if (map[i][pX] <0) return false;
                }
            pacmanWasHereFlag = true;
            savedPacmanCoords = pX*100 + pY;
            return true;
            }
        }
        
        if(pY==matrixY) {
            lineP = pX;
            lineG = matrixX;
            if(lineG > lineP){
            for (int i = lineG; i > lineP; i--){
                if (map[pY][i] <0) return false;
                }
            pacmanWasHereFlag = true;
            savedPacmanCoords = pX*100 + pY;
            return true;
            }
            else{
            for (int i = lineG; i < lineP; i++){
                if (map[pY][i] <0) return false;
                }
            pacmanWasHereFlag = true;
            savedPacmanCoords = pX*100 + pY;
            return true;
            }
        }
        return false;
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
            if (getX() == matrixX*CELL_SIZE+CELL_HALF && getY() == matrixY*CELL_SIZE+CELL_HALF) canChangeDirection = true;
        }
    }
    private void matrixNavigation(int current_x, int current_y) {
        matrixX = current_x / CELL_SIZE;
        matrixY = current_y / CELL_SIZE;
        _allowed_dir = map[matrixY][matrixX];
    }
    private void whereGhostLook(){
        switch (rotat){
            case 0: setImage("PGhost.png"); return;
            case 90: setImage("PGhostLookDown.png"); return;
            case 180: setImage("PGhostMirror.png"); return;
            case 270: setImage("PGhostLookUp.png") ; return;
        }

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
    private int pacmanCoords(){
        int PACMAN_MATRIX_X = 0;
        int PACMAN_MATRIX_Y = 0;
        return PACMAN_MATRIX_X * 100 + PACMAN_MATRIX_Y;
    }
    
}
