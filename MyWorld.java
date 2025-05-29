import greenfoot.*;

public class MyWorld extends World {
    public static int[][] wrld = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1,0,1},
        {1,0,1,1,0,1,1,1,1,0,1,0,0,0,1,0,1,1,1,1,0,1,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,1},
        {1,0,1,1,0,1,0,1,1,1,1,0,1,0,1,1,1,1,0,1,0,1,1,0,1},
        {1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1},
        {1,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1},
        {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
        {1,1,1,0,1,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,1,0,1,1,1},
        {1,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,1},
        {1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,1},
        {1,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,0,1,0,1},
        {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1},
        {1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public final static int worldPieceSize = 40;
    public final static int worldHalfPieceSize = worldPieceSize /2;
    public static int POWER_PILL_COUNT = 8;
    public MyWorld() {    
        super(1000, 680, 1);
        buildLab();
        spawnRed();
    }

    private void buildLab() {
        for (int y = 0; y < wrld.length; y++) {
            for (int x = 0; x < wrld[y].length; x++) {
                if (wrld[y][x] == 1) {
                    addObject(new Wall(), x * worldPieceSize + worldHalfPieceSize, y * worldPieceSize + worldHalfPieceSize);
                }
            }
        }
    }
    private void spawnRed(){
        addObject(new Red_ghost(), 10*worldPieceSize + worldHalfPieceSize,9*worldPieceSize + worldHalfPieceSize);
    }
}