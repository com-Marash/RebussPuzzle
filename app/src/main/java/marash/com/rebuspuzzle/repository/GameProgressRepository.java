package marash.com.rebuspuzzle.repository;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameProgress;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;

/**
 * Created by Maedeh on 3/2/2017.
 */

public class GameProgressRepository {

    public static ArrayList<GameProgress> gameProgressArray = new ArrayList<>();

    public static ArrayList<GameProgress> loadGameProgressArray(Context context) {

        FileInputStream fIn = null;
        try {
            fIn = context.openFileInput("gameProgress.marash");
            ObjectInputStream ois = new ObjectInputStream(fIn);
            gameProgressArray = (ArrayList<GameProgress>) ois.readObject();
            ois.close();
            fIn.close();
        } catch (Exception e) {       //For the first when file is not available, it creates it.
            try {
                FileOutputStream fOut = context.openFileOutput("gameProgress.marash", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fOut);

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
                oos.writeObject(gameProgressArray);
                oos.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

        return gameProgressArray;
    }
}
