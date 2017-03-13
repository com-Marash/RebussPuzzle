package marash.com.rebuspuzzle.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Maedeh on 3/13/2017.
 */

public class PlayerProgress implements Serializable {


    private ArrayList<GameProgress> gameProgressArray = new ArrayList<>();
    private int userMoney = 1000;

    public ArrayList<GameProgress> getGameProgressArray() {
        return gameProgressArray;
    }

    public void setGameProgressArray(ArrayList<GameProgress> gameProgressArray) {
        this.gameProgressArray = gameProgressArray;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }

}
