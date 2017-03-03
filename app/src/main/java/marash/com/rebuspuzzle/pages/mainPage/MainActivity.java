package marash.com.rebuspuzzle.pages.mainPage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.pages.gamePage.SelectedImageActivity;

import static marash.com.rebuspuzzle.AppClass.gameCellArray;
import static marash.com.rebuspuzzle.AppClass.gameProgresses;

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
                if (gameProgresses.get(position).isLocked()) {
                    //TODO show message that this level is lock!
                } else if (!gameProgresses.get(position).isSolved()) {
                    Intent intent = new Intent(MainActivity.this, SelectedImageActivity.class);
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
                        FileOutputStream fout = null;
                        try {
                            fout = MainActivity.this.openFileOutput("gameProgress.marash", Context.MODE_PRIVATE);
                            ObjectOutputStream oos = new ObjectOutputStream(fout);
                            oos.writeObject(gameProgresses);
                            oos.close();
                            fout.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                }).show();
    }
}
