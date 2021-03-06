package marash.com.rebuspuzzle;

import android.app.Application;
import android.content.Intent;

import java.util.ArrayList;

import marash.com.rebuspuzzle.dto.GameCell_info;
import marash.com.rebuspuzzle.dto.PlayerProgress;
import marash.com.rebuspuzzle.pages.mainPage.MainActivity;
import marash.com.rebuspuzzle.repository.GameCellInfoRepository;
import marash.com.rebuspuzzle.repository.PlayerProgressRepository;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Maedeh on 3/1/2017.
 */

public class AppClass extends Application {

    public static ArrayList<GameCell_info> gameCellArray = new ArrayList<>();
    public static PlayerProgress playerProgress;

    public AppClass() {

    }

    @Override
    public void onCreate() {
        gameCellArray = GameCellInfoRepository.loadGameCellArray(getAssets());

        playerProgress = PlayerProgressRepository.loadPlayerProgress(this);

        super.onCreate();
        Intent intent = new Intent(AppClass.this, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}