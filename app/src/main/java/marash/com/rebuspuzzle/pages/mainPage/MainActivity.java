package marash.com.rebuspuzzle.pages.mainPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.pages.gamePage.GamePageActivity;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;
import static marash.com.rebuspuzzle.AppClass.gameProgressArray;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = (GridView) findViewById(R.id.gameGridView);

        GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), gameCellArray);
        gameGrid.setAdapter(gameAdapter);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (gameProgressArray.get(position).isLocked()) {
                    //TODO show message that this level is lock!
                } else if (!gameProgressArray.get(position).isSolved()) {
                    Intent intent = new Intent(MainActivity.this, GamePageActivity.class);
                    intent.putExtra("gameCellInfo", gameCellArray.get(position)); // put gameCellInfo in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }


    @Override
    public void onBackPressed(){

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
}