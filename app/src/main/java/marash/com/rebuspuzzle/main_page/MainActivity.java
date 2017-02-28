package marash.com.rebuspuzzle.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.dto.GameCell_info;
import marash.com.rebuspuzzle.selected_image.SelectedImageActivity;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;
    public static ArrayList<GameCell_info> gameCellArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = (GridView) findViewById(R.id.gameGridView);

        gameCreator();

        GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), gameCellArray);
        gameGrid.setAdapter(gameAdapter);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (gameCellArray.get(position).isLocked()) {
                    //TODO show message that this level is lock!
                } else if (!gameCellArray.get(position).isSolved()) {
                    //TODO open the puzzle for that picture.
                    Intent intent = new Intent(MainActivity.this, SelectedImageActivity.class);
                    intent.putExtra("gameCellInfo", gameCellArray.get(position)); // put gameCellInfo in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }

    public void gameCreator() {

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

    }
}
