package marash.com.rebuspuzzle.selected_image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.main_page.MainActivity;

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
        int nextLevelPosition = getIntent().getIntExtra("nextLevelPosition", -1);
        if(nextLevelPosition <= MainActivity.gameCellArray.size()) {
            Intent intent = new Intent(WinTransitionActivity.this, SelectedImageActivity.class);
            intent.putExtra("gameCellInfo", MainActivity.gameCellArray.get(nextLevelPosition - 1));
            startActivity(intent);
        }else {
            Intent intent = new Intent(WinTransitionActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
