package marash.com.rebuspuzzle.pages.mainPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import marash.com.rebuspuzzle.AppClass;
import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.dto.GameProgress;
import marash.com.rebuspuzzle.pages.gamePage.GamePageActivity;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;
    ArrayList<GameProgress> gameProgressArray;
    boolean isLoaded = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isLoaded) {
            gameGrid = (GridView) findViewById(R.id.gameGridView);
            GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), gameCellArray);
            gameGrid.setAdapter(gameAdapter);

            gameProgressArray = AppClass.playerProgress.gameProgressArray;

        }

        ImageView img = (ImageView)findViewById(R.id.mainPageAnimation);
        img.setBackgroundResource(R.drawable.garfield1);
        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        // Start the animation (looped playback by default).
        frameAnimation.start();

        TextView userMoneyText = (TextView)findViewById(R.id.userMoney);
        userMoneyText.setText("" + AppClass.playerProgress.userMoney);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (gameProgressArray.get(position).isLocked()) {
                    //TODO show message that this level is lock!
                } else {
                    Intent intent = new Intent(MainActivity.this, GamePageActivity.class);
                    intent.putExtra("gameCellLevelNumber", gameCellArray.get(position).getLevelNumber()); // put gameCellInfo in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                }).show();
    }

    public void coinBank(View view) {
        Intent intent = new Intent(MainActivity.this,CoinBankActivity.class);
        startActivity(intent);
    }
}
