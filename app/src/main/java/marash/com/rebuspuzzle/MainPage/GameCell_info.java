package marash.com.rebuspuzzle.MainPage;

import java.io.Serializable;

/**
 * Created by Maedeh on 1/25/2017.
 */

public class GameCell_info implements Serializable {

    private int imageID;
    private String solution;
    private boolean isLocked, isSolved;
    private char[] alphabets;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public char[] getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(char[] alphabets) {
        this.alphabets = alphabets;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
