package marash.com.rebuspuzzle.pages.gamePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.pages.mainPage.MainActivity;

/**
 * Created by Maedeh on 2/3/2017.
 */
public class WinTransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_transition);
    }

    public void returnButtonFunction(View view) {
        Intent intent = new Intent(WinTransitionActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public void NextLevelFunction(View view) {
        int nextLevelNumber = getIntent().getIntExtra("nextLevelNumber", -1);
        Intent intent = new Intent(WinTransitionActivity.this, GamePageActivity.class);
        intent.putExtra("gameCellLevelNumber", nextLevelNumber);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(WinTransitionActivity.this, MainActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
