package marash.com.rebuspuzzle.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import marash.com.rebuspuzzle.R;
import marash.com.rebuspuzzle.selected_image.AlphabetChar;
import marash.com.rebuspuzzle.selected_image.SelectedImageActivity;

public class MainActivity extends AppCompatActivity {

    GridView gameGrid;

    ArrayList<GameCell_info> gameImages = new ArrayList<GameCell_info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = (GridView) findViewById(R.id.gameGridView);

        gameCreator();

        GameAdapter gameAdapter = new GameAdapter(getApplicationContext(), gameImages);
        gameGrid.setAdapter(gameAdapter);

        gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (gameImages.get(position).isLocked()) {
                    //TODO show message that this level is lock!
                } else if (!gameImages.get(position).isSolved()) {
                    //TODO open the puzzle for that picture.
                    Intent intent = new Intent(MainActivity.this, SelectedImageActivity.class);
                    intent.putExtra("gameCellInfo", gameImages.get(position)); // put gameCellInfo in Intent
                    startActivity(intent); // start Intent
                }
            }
        });
    }

    public void gameCreator() {

        GameCell_info g_1 = new GameCell_info();
        g_1.setImageID(R.drawable.carrot);
        g_1.setSolution("ABCDEF");
        g_1.setLocked(false);
        g_1.setSolved(false);
        g_1.setAlphabets(new AlphabetChar[]{new AlphabetChar('A'), new AlphabetChar('B'), new AlphabetChar('C'),
                new AlphabetChar('D'), new AlphabetChar('E'), new AlphabetChar('F'), new AlphabetChar('G')});

        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
        gameImages.add(g_1);
    }
}
