package marash.com.rebuspuzzle.dto;

import android.app.Application;
import android.content.Intent;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.main_page.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Maedeh on 3/1/2017.
 */

public class DataBaseCreator extends Application {

    public static ArrayList<GameCell_info> gameCellArray = new ArrayList<>();

    public DataBaseCreator() {

    }

    @Override
    public void onCreate() {
        InputStream streamIn = null;
        try {
            streamIn = getAssets().open("rebussPuzzle.marash");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            gameCellArray = (ArrayList<GameCell_info>) objectinputstream.readObject();
            objectinputstream.close();
            streamIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate();
        Intent intent = new Intent(DataBaseCreator.this, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}