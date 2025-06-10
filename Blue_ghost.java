import greenfoot.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Collections;

public class Blue_ghost extends Actor
{
    public Blue_ghost()
    {
        setImage("BGhost.png");
    }
    private static int PREVIOUS_COUNT_OF_PILLS = MyWorld.POWER_PILL_COUNT;
    private static int[][] map = MyWorld.wrld;
    private final static int CELL_SIZE = MyWorld.worldPieceSize;
    private final static int CELL_HALF = MyWorld.worldHalfPieceSize;
    
    private int fearStatusTimer = 0;
    private boolean goHomeFlag = false;
    private int waitHomeCount = 0;
    private int speed = 1;
    
    private int[] dirs = {1, 2, 4, 8};
    public int matrixX = 20;
    public int matrixY = 17;
    private int prevMX = 20;
    private int prevMY = 17;
    private int _allowed_dir = 10;
    private int where_from_came = 4;
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
        if (waitHome(5)) return;
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
    
    private void ScatterMod(int allowed_dirs)
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
    
    private void ChaseMod()
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
    
    private void FearMod(int allowed_dirs)
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
    private int pacmanCoordsMinus4() {
        int pRot = Pacman.rotat;
        int nMX = pacmanCoords() / 100;
        int nMY = pacmanCoords() % 100;
        if (pRot >90) pRot -=180;
        else pRot+=180;
        
        for (int step = 0; step < 4; step++) {
            int tX = nMX;
            int tY = nMY;
            
            switch (pRot) {
                case 0: tX++; break;
                case 90: tY++; break;
                case 180: tX--; break;
                case 270: tY--; break;
            }
            
            if (map[tY][tX] == 16 || map[tY][tX] < 0) break;
            
            nMX = tX;
            nMY = tY;
        }
        
        return nMX * 100 + nMY;
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
                return path;
            }
    
            for (int i = 0; i < 4; i++) {
                int nx = x + dirsmatrix[i][0], ny = y + dirsmatrix[i][1];
                if (nx >= 0 && nx < cols && ny >= 0 && ny < rows
                    && map[ny][nx] > 0 
                    && (map[y][x] & dirs[i]) != 0
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
            case 0: setImage("BGhost.png"); return;
            case 90: setImage("BGhostLookDown.png"); return;
            case 180: setImage("BGhostMirror.png"); return;
            case 270: setImage("BGhostLookUp.png") ; return;
        }
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
            prevMX = matrixX-1;
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
    private boolean waitHome(int jumpuli){
        if (waitHomeCount == jumpuli +1 ) return false;
        if (waitHomeCount < jumpuli){
        if (matrixY == 17 && waitHomeCount %2 == 0) setLocation(getX(), getY()-speed);
        
        if (matrixY == 16 && waitHomeCount%2 ==0){
            if (getX() == matrixX*CELL_SIZE+CELL_HALF && getY() == matrixY*CELL_SIZE+CELL_HALF) waitHomeCount += 1;
            else setLocation(getX(), getY()-speed);
            
        }
        
        if (matrixY == 16 && waitHomeCount%2 !=0) setLocation(getX(), getY()+speed);
        
        if (matrixY == 17 && waitHomeCount%2 !=0) setLocation(getX(), getY()+speed);
        
        if (matrixY == 18 && waitHomeCount%2 !=0){
            if (getX() == matrixX*CELL_SIZE+CELL_HALF && getY() == matrixY*CELL_SIZE+CELL_HALF) waitHomeCount += 1;
            else setLocation(getX(), getY()+speed);
            
        }
        
        if (matrixY == 18 && waitHomeCount%2 ==0) setLocation(getX(), getY()-speed);
        
        }
        if (waitHomeCount == jumpuli && getX() != 24*CELL_SIZE+CELL_HALF){
            setLocation(getX()+speed, getY());
        }
        if (getX() == 24*CELL_SIZE+CELL_HALF){
            waitHomeCount+=1;
            where_from_came = 2;
            return false;
        }
        return true;
    }
}