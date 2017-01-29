package marash.com.rebuspuzzle;

import java.io.Serializable;

/**
 * Created by Maedeh on 1/25/2017.
 */

public class GameCell_info implements Serializable {

    int imageID; // we use this bitmap that makes the image compressed and make every thing easier.
    String solution;
    boolean isLocked, isSolved;
    char[] alphabets;

    public GameCell_info(int im, String s, boolean isLocked, boolean isSolved, char[] ch) {
        this.imageID = im;
        this.solution = s;
        this.isLocked = isLocked;
        this.isSolved = isSolved;
        this.alphabets = ch;
    }
}
