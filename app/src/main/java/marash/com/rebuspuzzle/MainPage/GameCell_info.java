package marash.com.rebuspuzzle.MainPage;

import java.io.Serializable;

import marash.com.rebuspuzzle.SelectedImage.AlphabetChar;

/**
 * Created by Maedeh on 1/25/2017.
 */

public class GameCell_info implements Serializable {

    private int imageID;
    private String solution;
    private boolean isLocked, isSolved;
    private AlphabetChar[] alphabets;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public AlphabetChar[] getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(AlphabetChar[] alphabets) {
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
