package marash.com.rebuspuzzle.dto;

/**
 * Created by Maedeh on 3/2/2017.
 */

public class GameProgress {

    private boolean isLocked,isSolved;

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
}
