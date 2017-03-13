package marash.com.rebuspuzzle;

import android.app.Application;
import android.content.Intent;

import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameCellInfo;
import marash.com.rebuspuzzle.dto.GameProgress;
import marash.com.rebuspuzzle.pages.mainPage.MainActivity;
import marash.com.rebuspuzzle.repository.GameCellInfoRepository;
import marash.com.rebuspuzzle.repository.PlayerProgressRepository;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Maedeh on 3/1/2017.
 */

public class AppClass extends Application {

    public static ArrayList<GameCellInfo> gameCellArray = new ArrayList<>();
    public static ArrayList<GameProgress> gameProgressArray = new ArrayList<>();

    public AppClass() {

    }

    @Override
    public void onCreate() {


        gameCellArray = GameCellInfoRepository.loadGameCellArray(getAssets());

        gameProgressArray = PlayerProgressRepository.loadPlayerProgress(this);

        super.onCreate();
        Intent intent = new Intent(AppClass.this, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}