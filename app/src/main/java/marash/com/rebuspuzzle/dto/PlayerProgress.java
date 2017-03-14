package marash.com.rebuspuzzle.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Maedeh on 3/13/2017.
 */

public class PlayerProgress implements Serializable {

    public ArrayList<GameProgress> gameProgressArray;
    public int userMoney;

    public PlayerProgress(ArrayList<GameProgress> gameProgressArray){
        this.gameProgressArray = gameProgressArray;
        userMoney = 1000;
    }

}
