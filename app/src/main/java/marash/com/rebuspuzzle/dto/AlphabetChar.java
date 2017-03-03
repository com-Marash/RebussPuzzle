package marash.com.rebuspuzzle.dto;

import java.io.Serializable;

/**
 * Created by Maedeh on 1/31/2017.
 */
public class AlphabetChar implements Serializable{

    private char character;
    private int location = -1;

    public AlphabetChar(char character, int location) {
        this.character = character;
        this.location = location;
    }

    public AlphabetChar(char character) {
        this.character = character;
    }

    public int getLocation() {
        return location;

    }

    public void setLocation(int location) {
        this.location = location;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

}
