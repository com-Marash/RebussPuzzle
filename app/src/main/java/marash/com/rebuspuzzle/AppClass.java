package marash.com.rebuspuzzle;

import android.app.Application;
import android.content.Intent;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameCellInfo;
import marash.com.rebuspuzzle.dto.GameProgress;
import marash.com.rebuspuzzle.pages.mainPage.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Maedeh on 3/1/2017.
 */

public class AppClass extends Application {

    public static ArrayList<GameCellInfo> gameCellArray = new ArrayList<>();
    public static ArrayList<GameProgress> gameProgresses = new ArrayList<>();

    public AppClass() {

    }

    @Override
    public void onCreate() {
        InputStream streamIn = null;
        try {
            streamIn = getAssets().open("rebussPuzzle.marash");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            gameCellArray = (ArrayList<GameCellInfo>) objectinputstream.readObject();
            objectinputstream.close();
            streamIn.close();


            streamIn = getAssets().open("gameProgress.marash");
            objectinputstream = new ObjectInputStream(streamIn);
            gameProgresses = (ArrayList<GameProgress>) objectinputstream.readObject();
            objectinputstream.close();
            streamIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate();
        Intent intent = new Intent(AppClass.this, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}