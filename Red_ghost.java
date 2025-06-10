import greenfoot.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Collections;

public class Red_ghost extends Actor
{
    public Red_ghost()
    {
        setImage("RedGhost.png");
    }
    private static int PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
    private static int[][] map = MyWorld.wrld;
    private final static int CELL_SIZE = MyWorld.worldPieceSize;
    private final static int CELL_HALF = MyWorld.worldHalfPieceSize;
    
    private int fearStatusTimer = 0;
    private boolean goHomeFlag = false;
    private int speed = 1;
    
    private int[] dirs = {1, 2, 4, 8};
    public int matrixX = 24;
    public int matrixY = 13;
    private int prevMX = 24;
    private int prevMY = 13;
    private int _allowed_dir = 10;
    private int where_from_came = 2;
    private int current_path_step = 0;
    private List<Integer> path = new ArrayList();
    
    private int rotat = 270;
    private boolean canChangeDirection = false;
    private boolean JoinNewCell = false;
    
    public void act()
    {
        matrixNavigation(getX(),getY());
        circlenavigation();
        changeMatrixLocatioLog();
        inCenterOfCell();
        
        if (goHomeFlag){ 
            GoHomeMod();
            return;
        }
        
        if (MyWorld.POWER_PILL_COUNT < PREVIOUS_COUNT_OF_PILLS){
            fearStatusTimer += 70* PREVIOUS_COUNT_OF_PILLS;
            PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
        }   
        
        if (fearStatusTimer >0 ){
            fearStatusTimer -= 1;
            setImage("FGhost.png");
            FearMod(_allowed_dir);
            return;
        }
        
        if (MyWorld.CHASE_TIMER != 0){
            ChaseMod();
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
        for (int i = 0; i<4; i++){
                if ((allowed_dirs & dirs[i]) != 0)_allowed_direction.add(i*90);
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
    private void ChaseMod()//hunt pattern red one will chase pacman's coord
    {
        int newX = getX();
        int newY = getY();
       
        path = pathToTarget(pacmanCoords());
        current_path_step = 0;
        
        if (!canChangeDirection){ 
            switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        return;
        }
        
        if (matrixX < path.get(current_path_step+1)/100) rotat = 0;
        if (matrixX > path.get(current_path_step+1)/100) rotat = 180;
        if (matrixY < path.get(current_path_step+1)%100) rotat = 90;
        if (matrixY > path.get(current_path_step+1)%100) rotat = 270;
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
    
        setLocation(newX, newY);
        return;
        }
        
        List<Integer> _allowed_direction = new ArrayList<>();
        for (int i = 0; i<4; i++){
                if ((allowed_dirs & dirs[i]) != 0)_allowed_direction.add(i*90);
        }
            
        int randIndex =  Greenfoot.getRandomNumber(_allowed_direction.size());
        rotat = _allowed_direction.get(randIndex);
        
        switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        canChangeDirection = false;
        setLocation(newX, newY);
        
    }
    
    private void GoHomeMod(){
        int newX = getX();
        int newY = getY();
       
        path = pathToTarget(2413);
        current_path_step = 0;
        if (path == null){
            goHomeFlag = false;
            return;
        }
        
        if (!canChangeDirection){ 
            switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        switch (rotat){
            case 0: setImage("Eyes.png"); break;
            case 90: setImage("EyesLookDown.png"); break;
            case 180: setImage("EyesMirror.png"); break;
            case 270: setImage("EyesLookUp.png") ; break;
        }
    
        canChangeDirection = false;
        setLocation(newX, newY);
        return;
        }
        
        if (matrixX < path.get(current_path_step+1)/100) rotat = 0;
        if (matrixX > path.get(current_path_step+1)/100) rotat = 180;
        if (matrixY < path.get(current_path_step+1)%100) rotat = 90;
        if (matrixY > path.get(current_path_step+1)%100) rotat = 270;
        switch (rotat) {
            case 0:   newX += speed; break;
            case 90:  newY += speed; break;
            case 180: newX -= speed; break;
            case 270: newY -= speed; break;
        }
        
        switch (rotat){
            case 0: setImage("Eyes.png"); break;
            case 90: setImage("EyesLookDown.png"); break;
            case 180: setImage("EyesMirror.png"); break;
            case 270: setImage("EyesLookUp.png") ; break;
        }
        canChangeDirection = false;
        setLocation(newX, newY);
    }
    
    void somebodyCaptured(){
        if (fearStatusTimer >0){
            MyWorld.scoreValue += 200;
            goHomeFlag = true;
            fearStatusTimer = 0;
        }
    }
    
    private List<Integer> pathToTarget(int target) {
        int start = matrixX * 100 + matrixY;
        if (start == target) {
            return null; 
        }
    
        int rows = map.length;
        int cols = map[0].length;
        int[][] dirsmatrix = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
    
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, -1);
        dist[start % 100][start / 100] = 0;
    
        int[][] prev = new int[rows][cols];
        for (int[] row : prev) Arrays.fill(row, -1);
    
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int x = curr / 100, y = curr % 100;
    
            if (curr == target) {
                List<Integer> path = new ArrayList<>();
                int step = curr;
                while (step != -1) {
                    path.add(step);
                    step = prev[step % 100][step / 100];
                }
                Collections.reverse(path);
                return path; //methode return list of directions to get target
            }
    
            for (int i = 0; i < 4; i++) {                          //  4
                int nx = x + dirsmatrix[i][0], ny = y + dirsmatrix[i][1];      //2   0
                if (nx >= 0 && nx < cols && ny >= 0 && ny < rows   //  1
                    && map[ny][nx] > 0 
                    && (map[y][x] & dirs[i]) != 0 //number 8-15 ve 4 bits, so methode use bit mask
                    && (map[ny][nx] & dirs[(i + 2) % 4]) != 0 
                    && dist[ny][nx] == -1) {
                    dist[ny][nx] = dist[y][x] + 1;
                    prev[ny][nx] = curr;
                    queue.add(nx * 100 + ny);
                }
            }
        }
        return null;
    }
    
    private void whereGhostLook(){
        switch (rotat){
            case 0: setImage("RedGhost.png"); return;
            case 90: setImage("RedGhostLookDown.png"); return;
            case 180: setImage("RedGhostMirror.png"); return;
            case 270: setImage("RedGhostLookUp.png") ; return;
        }
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
        }
    }
    
    private void matrixNavigation(int current_x, int current_y) {
        matrixX = current_x / CELL_SIZE;
        matrixY = current_y / CELL_SIZE;
        _allowed_dir = map[matrixY][matrixX];
        doNotGoHome();
        if (matrixX*100+matrixY == pacmanCoords() && goHomeFlag == false){
            somebodyCaptured();
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
        int PACMAN_MATRIX_X = Pacman.matrixX;
        int PACMAN_MATRIX_Y = Pacman.matrixY;
        return PACMAN_MATRIX_X * 100 + PACMAN_MATRIX_Y;
    }
    private void doNotGoHome(){
        if (matrixX *100 + matrixY == 2411 && where_from_came != 2){
            _allowed_dir -= 2;
        }
    }
}
