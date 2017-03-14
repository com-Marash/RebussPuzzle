package marash.com.rebuspuzzle.repository;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameProgress;
import marash.com.rebuspuzzle.dto.PlayerProgress;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;

/**
 * Created by Maedeh on 3/2/2017.
 */

public class PlayerProgressRepository {



    public static PlayerProgress loadPlayerProgress(Context context) {

        FileInputStream fIn = null;
        PlayerProgress playerProgress = null;

        try {
            fIn = context.openFileInput("PlayerProgress.marash");
            ObjectInputStream ois = new ObjectInputStream(fIn);
            playerProgress = (PlayerProgress) ois.readObject();
            ois.close();
            fIn.close();
        } catch (Exception e) {       //For the first when file is not available, it creates it.
            try {
                ArrayList<GameProgress> gameProgressArray = new ArrayList<>();
                playerProgress = new PlayerProgress(gameProgressArray);

                GameProgress gp = new GameProgress();
                gp.setLocked(false);
                gp.setSolved(false);
                gameProgressArray.add(gp);

                for(int i = 1; i < gameCellArray.size(); i++){
                    gp = new GameProgress();
                    gp.setLocked(true);
                    gp.setSolved(false);
                    gameProgressArray.add(gp);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        return playerProgress;
    }


    public static void savePlayerProgress(Context context,PlayerProgress playerProgress) {
        FileOutputStream fOut = null;
        try {
            fOut = context.openFileOutput("PlayerProgress.marash", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fOut);
            oos.writeObject(playerProgress);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
