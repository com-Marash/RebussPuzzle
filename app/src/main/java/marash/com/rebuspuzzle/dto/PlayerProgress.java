package marash.com.rebuspuzzle.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Maedeh on 3/13/2017.
 */

public class PlayerProgress implements Serializable {

    public ArrayList<GameProgress> gameProgressArray = new ArrayList<>();
    public int userMoney = 1000;

}
